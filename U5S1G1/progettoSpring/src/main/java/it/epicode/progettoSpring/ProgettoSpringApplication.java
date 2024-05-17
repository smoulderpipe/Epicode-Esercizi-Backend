package it.epicode.progettoSpring;

import it.epicode.progettoSpring.appConfig.AppConfig;
import it.epicode.progettoSpring.bean.Menu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ProgettoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgettoSpringApplication.class, args);

		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

		Menu menu = ctx.getBean(Menu.class);
		menu.stampaMenu();


	}

}
