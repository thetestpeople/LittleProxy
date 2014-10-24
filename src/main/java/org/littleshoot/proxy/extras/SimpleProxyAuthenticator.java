package org.littleshoot.proxy.extras;

import java.util.Map;

import org.littleshoot.proxy.ProxyAuthenticator;

import com.google.common.base.Optional;
import static com.google.common.base.Preconditions.checkNotNull;


public class SimpleProxyAuthenticator implements ProxyAuthenticator {

	private final Map<String, Optional<String>> credentialsMap;

	public SimpleProxyAuthenticator(final Map<String, Optional<String>> credentialsMap) {
		this.credentialsMap = checkNotNull(credentialsMap);
	}
	
	@Override
	public boolean authenticate(String userName, String password) {
		final Optional<String> expectedPassword = 
				credentialsMap.get(checkNotNull(userName));

		if (null == expectedPassword) { return false; }
		if (!expectedPassword.isPresent()) { return true; }
		return (password.equals(expectedPassword.get()));
	}

}
