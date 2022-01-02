package com;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.demyx.user.service.UserService;


@SpringBootApplication
@ComponentScan(basePackages = { "com.demyx.user", "com.demyx.config"} )
public class Application extends AppConfiguration  implements ApplicationRunner{

	@Autowired
	public UserService userService;
	


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	@Override
	public void run(ApplicationArguments args) throws Exception {

		userService.fillInitialUsers();
		
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:3000");
			}
		};
	}
        


}
