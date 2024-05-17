package it.epicode.progettoSpring;

import it.epicode.progettoSpring.appConfig.AppConfig;
import it.epicode.progettoSpring.bean.Aula;
import it.epicode.progettoSpring.bean.Studente;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ProgettoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgettoSpringApplication.class, args);

		//accediamo al contesto di spring
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

		//recuperiamo dal contesto il bean creato da spring
		//Studente studente = ctx.getBean("Carla", Studente.class);
		//Studente studente2 = ctx.getBean("Francesca", Studente.class);

		Studente studente = ctx.getBean(Studente.class);
		Studente studente2 = ctx.getBean(Studente.class);

		studente.setCognome("Rossi");

		System.out.println(studente == studente2);
		System.out.println(studente);
		System.out.println(studente2);

		Aula aula = ctx.getBean(Aula.class);
		System.out.println(aula);
	}

}
