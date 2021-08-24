package com.napster.tags.config.security.basic;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class CBInMemoryAuthenticationProvider implements AuthenticationProvider {
	private final String defaultUser;
	private final String defaultPassword;

	public CBInMemoryAuthenticationProvider(String userName, String password) {
		this.defaultUser = userName;
		this.defaultPassword = password;
	}

	protected Authentication createSuccessAuthentication(Object principal, Authentication authentication,
			UserDetails user) {
		// Ensure we return the original credentials the user supplied,
		// so subsequent attempts are successful even with encoded passwords.
		// Also ensure we return the original getDetails(), so that future
		// authentication events after cache expiry contain the details
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(principal,
				authentication.getCredentials(), user.getAuthorities());
		result.setDetails(authentication.getDetails());

		return result;
	}

	@Override
	public Authentication authenticate(Authentication authRequest) throws AuthenticationException {

		String userName = authRequest.getName();
		String password = (String) authRequest.getCredentials();

		if (userName.equals(defaultUser) && password.equals(defaultPassword)) {
			return createSuccessAuthentication(userName, authRequest,

					new User(userName, password, AuthorityUtils.createAuthorityList("ROLE_USER")));
		}
		throw new BadCredentialsException("Invalid credentials");
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(arg0);
	}

}
