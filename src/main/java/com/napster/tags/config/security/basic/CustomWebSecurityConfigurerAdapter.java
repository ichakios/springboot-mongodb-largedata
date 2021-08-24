package com.napster.tags.config.security.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;

@Configuration
@EnableWebSecurity
@EnableCaching
@PropertySource(ignoreResourceNotFound = true,value="classpath:security.properties")
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter
		implements WebSecurityConfigurer<WebSecurity>
{

	private final Logger logger = LoggerFactory.getLogger(CustomWebSecurityConfigurerAdapter.class);

	@Autowired
	private AuthenticationEntryPointImpl authenticationEntryPoint;

	@Autowired
	private Environment env;

	private static final String[] PUBLIC_MATCHERS =
	{ "/", "/assets/**" };

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		logger.debug("configure the web security");
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "*/").permitAll()
				.antMatchers(HttpMethod.GET, "/*").permitAll().antMatchers("/assets/**").permitAll();
		http.authorizeRequests().antMatchers("/api/**").authenticated().and().httpBasic()
				.authenticationEntryPoint(authenticationEntryPoint);
		http.cors().and().csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
	{


		String username = "nap";//env.getProperty("auth.username");
		String password = "nap@123";//env.getProperty("auth.password");
		if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password))
		{
			auth.authenticationProvider(new CBInMemoryAuthenticationProvider(username, password));
		}

		manageEnvironment();
	}

	private void manageEnvironment() throws Exception
	{
		String path = env.getProperty("javax.net.ssl.trustStore");
		String pass_enc = env.getProperty("javax.net.ssl.trustStorePassword");
		if (!StringUtils.isEmpty(path) && !StringUtils.isEmpty(pass_enc))
		{
			String pass = "";//CryptionUtil.decrypt(Constants.KEY, pass_enc);
			System.out
					.println("\n\nTrustStore path is " + path + " and the password encrypted is " + pass_enc + "\n\n");
			System.setProperty("https.protocols", "TLSv1.1");
			System.setProperty("javax.net.ssl.trustStore", path);
			System.setProperty("javax.net.ssl.trustStorePassword", pass);
		}
	}

	@Override
	public void configure(WebSecurity web)
	{
		web.ignoring().antMatchers("/", "/console", "/webjars/**", "/*.html", "/*.jsp", "/favicon.ico", "/**/*.css",
				"/**/*.png", "/**/*.js", "/**/*.json", "/assets/**", "/**/*.ttf", "/**/*.woff", "/**/*.woff2");
	}
}
