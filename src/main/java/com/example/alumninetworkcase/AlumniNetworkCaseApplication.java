package com.example.alumninetworkcase;

import com.example.alumninetworkcase.utils.MyConfigs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class AlumniNetworkCaseApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(MyConfigs.class);
		ctx.refresh();
		ctx.close();

		SpringApplication.run(AlumniNetworkCaseApplication.class, args);

	}

}
