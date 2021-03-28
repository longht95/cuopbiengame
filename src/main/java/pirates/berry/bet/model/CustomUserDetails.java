package pirates.berry.bet.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class CustomUserDetails extends org.springframework.security.core.userdetails.User{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private pirates.berry.bet.model.User user;
	
	public CustomUserDetails(pirates.berry.bet.model.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.user = user;
    }
	
	public CustomUserDetails(pirates.berry.bet.model.User user, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.user = user;
    }

    public pirates.berry.bet.model.User getUser() {
        return user;
    }
}
