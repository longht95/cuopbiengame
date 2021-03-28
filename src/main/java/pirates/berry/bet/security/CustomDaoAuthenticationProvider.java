package pirates.berry.bet.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider {
	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		UserDetails u = null;
		u = getUserDetailsService().loadUserByUsername(name);
		if (u != null) {
			if (u.getPassword().equals(password)) {
				return new UsernamePasswordAuthenticationToken(u, password, u.getAuthorities());
			}
		}
		throw new BadCredentialsException("");
		
	}
}
