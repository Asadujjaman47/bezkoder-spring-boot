package com.bezkoder;

import com.bezkoder.model.Tutorial;
import com.bezkoder.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {

    @Autowired
    TutorialRepository tutorialRepository;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // clear all data:
//		tutorialRepository.deleteAll();

        // CRATE SOME Tutorial:
        tutorialRepository.save(new Tutorial("Spring Data", "Tut#1 Description", false));
        tutorialRepository.save(new Tutorial("Java Spring", "Tut#2 Description", false));
        tutorialRepository.save(new Tutorial("Hibernate", "Tut#3 Description", false));
        tutorialRepository.save(new Tutorial("Spring Boot", "Tut#4 Description", false));
        tutorialRepository.save(new Tutorial("Spring Data JPA", "Tut#5 Description", false));
        tutorialRepository.save(new Tutorial("JPA EntityManager", "Tut#6 Description", false));
        tutorialRepository.save(new Tutorial("Spring Security", "Tut#7 Description", false));

        List<Tutorial> tutorials = new ArrayList<>();

        // SHOW ALL Tutorials:
        tutorials = tutorialRepository.findAll();
        show(tutorials);
//Output:
/*
Number of Items: 7
Tutorial [id=1, title=Spring Data, desc=Tut#1 Description, published=false]
Tutorial [id=2, title=Java Spring, desc=Tut#2 Description, published=false]
Tutorial [id=3, title=Hibernate, desc=Tut#3 Description, published=false]
Tutorial [id=4, title=Spring Boot, desc=Tut#4 Description, published=false]
Tutorial [id=5, title=Spring Data JPA, desc=Tut#5 Description, published=false]
Tutorial [id=6, title=JPA EntityManager, desc=Tut#6 Description, published=false]
Tutorial [id=7, title=Spring Security, desc=Tut#7 Description, published=false]
*/

        // FIND Tutorial BY ID:
        Tutorial tutorial = tutorialRepository.findById(6);
        System.out.println(tutorial);
//Output:
/*
Tutorial [id=6, title=JPA EntityManager, desc=Tut#6 Description, published=false]
*/

        // UPDATE ANY Tutorial BY ID:
        Tutorial tut1 = tutorials.get(0);
        tut1.setPublished(true);

        Tutorial tut3 = tutorials.get(2);
        tut3.setPublished(true);

        Tutorial tut5 = tutorials.get(4);
        tut5.setPublished(true);

        tutorialRepository.update(tut1);
        tutorialRepository.update(tut3);
        tutorialRepository.update(tut5);

        // FIND Tutorial BY Title:
        tutorials = tutorialRepository.findByTitleContaining("jpa");
        show(tutorials);
//Output:
/*
Number of Items: 2
Tutorial [id=5, title=Spring Data JPA, desc=Tut#5 Description, published=true]
Tutorial [id=6, title=JPA EntityManager, desc=Tut#6 Description, published=false]
*/

        // FIND Tutorial BY Published:
        tutorials = tutorialRepository.findByPublished(true);
        show(tutorials);
//Output:
/*
Number of Items: 3
Tutorial [id=1, title=Spring Data, desc=Tut#1 Description, published=true]
Tutorial [id=3, title=Hibernate, desc=Tut#3 Description, published=true]
Tutorial [id=5, title=Spring Data JPA, desc=Tut#5 Description, published=true]
*/

        // FIND Tutorial BY Title And Published:
        tutorials = tutorialRepository.findByTitleAndPublished("data", true);
        show(tutorials);
//Output:
/*
Number of Items: 2
Tutorial [id=1, title=Spring Data, desc=Tut#1 Description, published=true]
Tutorial [id=5, title=Spring Data JPA, desc=Tut#5 Description, published=true]
*/

        // DELETE Tutorial BY ID:
        Tutorial deletedTutorial = tutorialRepository.deleteById(4);
        System.out.println(deletedTutorial);
//Output:
/*
Tutorial [id=4, title=Spring Boot, desc=Tut#4 Description, published=false]
*/

        // SHOW ALL Tutorials:
        tutorials = tutorialRepository.findAll();
        show(tutorials);

//Output:
/*
Number of Items: 6
Tutorial [id=1, title=Spring Data, desc=Tut#1 Description, published=true]
Tutorial [id=2, title=Java Spring, desc=Tut#2 Description, published=false]
Tutorial [id=3, title=Hibernate, desc=Tut#3 Description, published=true]
Tutorial [id=5, title=Spring Data JPA, desc=Tut#5 Description, published=true]
Tutorial [id=6, title=JPA EntityManager, desc=Tut#6 Description, published=false]
Tutorial [id=7, title=Spring Security, desc=Tut#7 Description, published=false]
*/

        // DELETE ALL Tutorial:
        int numberOfDeletedRows = tutorialRepository.deleteAll();
        System.out.println(numberOfDeletedRows);
//Output:
/*
6
*/

        // SHOW ALL Tutorials:
        tutorials = tutorialRepository.findAll();
        show(tutorials);
//Output:
/*
Number of Items: 0
*/
    }

    private void show(List<Tutorial> tutorials) {
        System.out.println("Number of Items: " + tutorials.size());
        tutorials.forEach(System.out::println);
    }
}
