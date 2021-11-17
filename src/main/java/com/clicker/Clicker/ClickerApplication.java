package com.clicker.Clicker;

import com.clicker.Clicker.service.DatabaseInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ClickerApplication {

	@Autowired
	private DatabaseInit dbInit;

	private static String[] args;

	public static void main(String[] args) {
		ClickerApplication.args = args;
		SpringApplication.run(ClickerApplication.class, args);
	}

	@PostConstruct
	private void Init(){
		if (args.length > 0 && "-init".equals(args[0])){
			dbInit.Init();
		}
	}
}
