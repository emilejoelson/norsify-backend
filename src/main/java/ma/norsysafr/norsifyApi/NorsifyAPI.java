package ma.norsysafr.norsifyApi;


import ma.norsysafr.norsifyApi.config.RsakeyConfig;
import ma.norsysafr.norsifyApi.service.interfaces.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
@EnableConfigurationProperties(RsakeyConfig.class)
public class NorsifyAPI {
	public static void main(String[] args) {
		SpringApplication.run(NorsifyAPI.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner start(AccountService accountService){
		return args -> {
			/*accountService.addNewRole(new AppRoleDtoRequest( "USER"));
			accountService.addNewRole(new AppRoleDtoRequest("ADMIN"));

			accountService.addNewUser(new AppUserDtoRequest( "user1", "1234"));
			accountService.addNewUser(new AppUserDtoRequest( "admin", "1234"));

			accountService.addRoleToUser("user1", "USER");
			accountService.addRoleToUser("admin", "USER");
			accountService.addRoleToUser("admin", "ADMIN");*/
		};
	}

}
