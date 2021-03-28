package pirates.berry.bet.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pirates.berry.bet.model.Role;
import pirates.berry.bet.model.User;
import pirates.berry.bet.repository.RoleRepository;
import pirates.berry.bet.repository.UserRepository;

@Controller
public class RegistryController {
	
	@Bean
	public PasswordEncoder Bencoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@RequestMapping(value = "/registry", method = RequestMethod.GET)
	public String registry() {
		return "registry";
	}

	@RequestMapping(value = "/doRegistry", method = RequestMethod.POST)
	public String postRegistry(User user) {
		
		Set<Role> roles = new HashSet<>();
		Role role = new Role();
		role.setRole("USER");
		roleRepository.save(role);
		roles.add(roleRepository.findByRole("USER"));
//		user.setPassword(Bencoder().encode(user.getPassword()));
		user.setRoles(roles);
		User u = userRepository.save(user);
		System.out.println("username"+u.getUsername());
		System.out.println("password"+u.getPassword());
		return "/login";
	}
}
