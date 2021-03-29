package pirates.berry.bet.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import pirates.berry.bet.security.CustomDaoAuthenticationProvider;
import pirates.berry.bet.security.CustomUserDetailsService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		final DaoAuthenticationProvider bean = new CustomDaoAuthenticationProvider();
		bean.setUserDetailsService(customUserDetailsService);
		bean.setPasswordEncoder(encoder());
		return bean;
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login", "/registry", "/doRegistry")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/doLogin")
                .successForwardUrl("/postLogin")
                .failureUrl("/loginFailed")
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .logoutUrl("/doLogout").logoutSuccessUrl("/logout").permitAll()
                .and()
                /**
                 * Applies to User Roles - not to login failures or unauthenticated access attempts.
                 */
                .exceptionHandling()
                .and()
                .authenticationProvider(authenticationProvider());

        /** Disabled for local testing */
        http
                .csrf().disable();

        /** This is solely required to support H2 console viewing in Spring MVC with Spring Security */
        http
                .headers()
                .frameOptions()
                .disable();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    	//auth.authenticationProvider(authenticationProvider());
    	auth
    	.inMemoryAuthentication()
    		.withUser("user").password("{noop}password").roles("USER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

}
