package it.epicode.progettoSpring.appConfig;

import it.epicode.progettoSpring.bean.Aula;
import it.epicode.progettoSpring.bean.Computer;
import it.epicode.progettoSpring.bean.Smartphone;
import it.epicode.progettoSpring.bean.Studente;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import java.util.List;

@Configuration
public class AppConfig {

   // @Bean("Carla")
    @Bean
    @Primary
    @Scope("prototype")
    public Studente getStudente(){
        Studente studente = new Studente();
        studente.setNome("Carla");
        studente.setCognome("Sordi");
        studente.setIndirizzo("Via Roma");
        studente.setDispositivi(List.of(getComputer(), getSmartphone()));
        return studente;
    }
    @Bean("Francesca")
    public Studente getStudente2(){
        Studente studente2 = new Studente();
        studente2.setNome("Francesca");
        studente2.setCognome("Polli");
        studente2.setIndirizzo("Via Roma");
        return studente2;
    }

    @Bean
    public Computer getComputer(){
        Computer computer = new Computer();
        computer.setNome("X1");
        computer.setMarca("HP");
        computer.setCpu("Intel");
        computer.setRam(16);
        computer.setMonitor(27);
        return computer;
    }

    @Bean
    public Smartphone getSmartphone(){
        Smartphone smartphone = new Smartphone();
        smartphone.setNome("S23");
        smartphone.setMarca("Samsung");
        smartphone.setSchermo(6);
        return smartphone;
    }

    @Bean
    public Aula getAula(){
        Aula aula = new Aula();
        aula.setNome("A1");
        aula.setStudenti(List.of(getStudente(), getStudente2()));
        return aula;
    }
}
