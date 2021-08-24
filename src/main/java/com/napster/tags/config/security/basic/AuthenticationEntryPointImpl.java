package com.napster.tags.config.security.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEntryPointImpl extends BasicAuthenticationEntryPoint {

	private static final Logger logger  = LoggerFactory.getLogger(AuthenticationEntryPointImpl.class);
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
			throws IOException {
		logger.debug("Authorization=> "+request.getHeader("Authorization"));
		response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");
		response.addHeader("Content-Type", "application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		PrintWriter writer = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", "HTTP Status 401 - " +authEx.getMessage());
		jsonObject.put("code", "resource.unauthorized");
//		writer.println("HTTP Status 401 - " + authEx.getMessage());
		writer.println(jsonObject.toString());
	}

	@Override
	public void afterPropertiesSet() {
		setRealmName("myRealm");
		super.afterPropertiesSet();
	}
}
