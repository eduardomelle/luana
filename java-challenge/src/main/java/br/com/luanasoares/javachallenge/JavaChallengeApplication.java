package br.com.luanasoares.javachallenge;

import br.com.luanasoares.javachallenge.client.MovieClient;
import br.com.luanasoares.javachallenge.dto.NowPlayingResponseDto;
import br.com.luanasoares.javachallenge.model.Movie;
import br.com.luanasoares.javachallenge.model.Role;
import br.com.luanasoares.javachallenge.model.User;
import br.com.luanasoares.javachallenge.repository.MovieRepository;
import br.com.luanasoares.javachallenge.repository.RoleRepository;
import br.com.luanasoares.javachallenge.repository.UserRepository;
import br.com.luanasoares.javachallenge.util.PasswordUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class JavaChallengeApplication {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Value("${token}")
	private String token;

	@Autowired
	private MovieClient movieClient;

	@Autowired
	private MovieRepository movieRepository;

	public static void main(String[] args) {
		SpringApplication.run(JavaChallengeApplication.class, args);
	}

	@PostConstruct
	public void postConstruct() {
		Set<Role> roles = new HashSet<>();

		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		Role roleAdminSaved =  this.roleRepository.save(roleAdmin);
		roles.add(roleAdminSaved);

		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		Role roleUserSaved = this.roleRepository.save(roleUser);
		roles.add(roleUserSaved);

		User userAdmin = new User();
		userAdmin.setUsername("admin");
		userAdmin.setPassword(PasswordUtils.generatebCrypt("123456"));
		userAdmin.setRoles(roles);

		User userAdminSaved = this.userRepository.save(userAdmin);
		System.err.println(userAdminSaved);
	}

}
