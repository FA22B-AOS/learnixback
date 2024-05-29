package de.szut.learnixback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class LearnixbackApplication {

	public static void main(String[] args) {
		if(args.length > 0){
			if(args[0].equals("-manualdocker"))
				DockerComposeRunner.setManual(true);
		}
		try {
			DockerComposeRunner.startDockerCompose();
			SpringApplication.run(LearnixbackApplication.class, args);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}