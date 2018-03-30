package com.deep;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;


//@EnableWebMvc
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.deep.*"})
@PropertySources(value={@PropertySource("classpath:config/application.properties"),@PropertySource("classpath:config/activeMQ.properties"),@PropertySource("classpath:config/jdbc.properties")})
//@SpringBootApplication(exclude)
public class Application{

	public static void main(String[] args) throws IOException {
		
		/*Properties properties = new Properties();
		InputStream in = Application.class.getClassLoader().getResourceAsStream("config/application.properties");
		properties.load(in);
		SpringApplication app = new SpringApplication(Application.class);
		app.setDefaultProperties(properties);
		app.run(args);*/
		
		SpringApplication.run(Application.class, args);
	}
}
