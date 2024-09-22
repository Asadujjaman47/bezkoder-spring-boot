package com.bezkoder;

import com.bezkoder.model.Tutorial;
import com.bezkoder.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

		tutorialRepository.deleteAll();

		Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-11");
		Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2022-04-26");
		Date date3 = new SimpleDateFormat("yyyy-MM-dd").parse("2022-05-19");

		tutorialRepository.save(new Tutorial("Spring Data", "Spring Data Description", 3, true, date1));
		tutorialRepository.save(new Tutorial("Java Spring Boot", "Spring Framework Description", 1, false, date1));
		tutorialRepository.save(new Tutorial("Hibernate", "Hibernate ORM Description", 3, true, date2));
		tutorialRepository.save(new Tutorial("Spring Boot", "Spring Boot Description", 2, false, date2));
		tutorialRepository.save(new Tutorial("Spring JPA", "Spring Data JPA Description", 3, true, date3));
		tutorialRepository.save(new Tutorial("Spring Batch", "Spring Batch Description", 4, true, date3));
		tutorialRepository.save(new Tutorial("Spring Security", "Spring Security Description", 5, false, date3));

		List<Tutorial> tutorials = new ArrayList<>();

		tutorials = tutorialRepository.findAll();
		show(tutorials);
/*
Tutorial [id=1, title=Spring Data, description=Spring Data Description, level=3, published=true, createdAt=2022-03-11 00:00:00.0]
Tutorial [id=2, title=Java Spring Boot, description=Spring Framework Description, level=1, published=false, createdAt=2022-03-11 00:00:00.0]
Tutorial [id=3, title=Hibernate, description=Hibernate ORM Description, level=3, published=true, createdAt=2022-04-26 00:00:00.0]
Tutorial [id=4, title=Spring Boot, description=Spring Boot Description, level=2, published=false, createdAt=2022-04-26 00:00:00.0]
Tutorial [id=5, title=Spring JPA, description=Spring Data JPA Description, level=3, published=true, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=6, title=Spring Batch, description=Spring Batch Description, level=4, published=true, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=7, title=Spring Security, description=Spring Security Description, level=5, published=false, createdAt=2022-05-19 00:00:00.0]
*/

		Tutorial tutorial = tutorialRepository.findById(1).get();
		System.out.println(tutorial);
/*
Tutorial [id=1, title=Spring Data, description=Spring Data Description, level=3, published=true, createdAt=2022-03-11 00:00:00.0]
*/

		tutorials = tutorialRepository.findByPublished(true);
		show(tutorials);
/*
Tutorial [id=1, title=Spring Data, description=Spring Data Description, level=3, published=true, createdAt=2022-03-11 00:00:00.0]
Tutorial [id=3, title=Hibernate, description=Hibernate ORM Description, level=3, published=true, createdAt=2022-04-26 00:00:00.0]
Tutorial [id=5, title=Spring JPA, description=Spring Data JPA Description, level=3, published=true, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=6, title=Spring Batch, description=Spring Batch Description, level=4, published=true, createdAt=2022-05-19 00:00:00.0]
*/

		tutorials = tutorialRepository.findByLevel(3);
		show(tutorials);
// OR
		tutorials = tutorialRepository.findByLevelIs(3);
		show(tutorials);
// OR

		tutorials = tutorialRepository.findByLevelEquals(3);
		show(tutorials);
/*
Tutorial [id=1, title=Spring Data, description=Spring Data Description, level=3, published=true, createdAt=2022-03-11 00:00:00.0]
Tutorial [id=3, title=Hibernate, description=Hibernate ORM Description, level=3, published=true, createdAt=2022-04-26 00:00:00.0]
Tutorial [id=5, title=Spring JPA, description=Spring Data JPA Description, level=3, published=true, createdAt=2022-05-19 00:00:00.0]
*/

		tutorials = tutorialRepository.findByLevelNot(3);
		show(tutorials);
// OR
		tutorials = tutorialRepository.findByLevelIsNot(3);
		show(tutorials);
/*
Tutorial [id=2, title=Java Spring Boot, description=Spring Framework Description, level=1, published=false, createdAt=2022-03-11 00:00:00.0]
Tutorial [id=4, title=Spring Boot, description=Spring Boot Description, level=2, published=false, createdAt=2022-04-26 00:00:00.0]
Tutorial [id=6, title=Spring Batch, description=Spring Batch Description, level=4, published=true, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=7, title=Spring Security, description=Spring Security Description, level=5, published=false, createdAt=2022-05-19 00:00:00.0]
*/

		tutorials = tutorialRepository.findByLevelAndPublished(3, true);
		show(tutorials);
/*
Tutorial [id=1, title=Spring Data, description=Spring Data Description, level=3, published=true, createdAt=2022-03-11 00:00:00.0]
Tutorial [id=3, title=Hibernate, description=Hibernate ORM Description, level=3, published=true, createdAt=2022-04-26 00:00:00.0]
Tutorial [id=5, title=Spring JPA, description=Spring Data JPA Description, level=3, published=true, createdAt=2022-05-19 00:00:00.0]
*/

		tutorials = tutorialRepository.findByTitleOrDescription("Hibernate", "Spring Data Description");
		show(tutorials);
/*
Tutorial [id=1, title=Spring Data, description=Spring Data Description, level=3, published=true, createdAt=2022-03-11 00:00:00.0]
Tutorial [id=3, title=Hibernate, description=Hibernate ORM Description, level=3, published=true, createdAt=2022-04-26 00:00:00.0]
*/

		tutorials = tutorialRepository.findByTitleStartingWith("Spring");
		show(tutorials);
/*
Tutorial [id=1, title=Spring Data, description=Spring Data Description, level=3, published=true, createdAt=2022-03-11 00:00:00.0]
Tutorial [id=4, title=Spring Boot, description=Spring Boot Description, level=2, published=false, createdAt=2022-04-26 00:00:00.0]
Tutorial [id=5, title=Spring JPA, description=Spring Data JPA Description, level=3, published=true, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=6, title=Spring Batch, description=Spring Batch Description, level=4, published=true, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=7, title=Spring Security, description=Spring Security Description, level=5, published=false, createdAt=2022-05-19 00:00:00.0]
*/

		tutorials = tutorialRepository.findByTitleEndingWith("ot");
		show(tutorials);
/*
Tutorial [id=2, title=Java Spring Boot, description=Spring Framework Description, level=1, published=false, createdAt=2022-03-11 00:00:00.0]
Tutorial [id=4, title=Spring Boot, description=Spring Boot Description, level=2, published=false, createdAt=2022-04-26 00:00:00.0]
*/

		tutorials = tutorialRepository.findByTitleContaining("at");
		show(tutorials);
/*
Tutorial [id=1, title=Spring Data, description=Spring Data Description, level=3, published=true, createdAt=2022-03-11 00:00:00.0]
Tutorial [id=3, title=Hibernate, description=Hibernate ORM Description, level=3, published=true, createdAt=2022-04-26 00:00:00.0]
Tutorial [id=6, title=Spring Batch, description=Spring Batch Description, level=4, published=true, createdAt=2022-05-19 00:00:00.0]
*/

		tutorials = tutorialRepository.findByTitleContainingIgnoreCase("dat");
		show(tutorials);
/*
Tutorial [id=1, title=Spring Data, description=Spring Data Description, level=3, published=true, createdAt=2022-03-11 00:00:00.0]
*/

		String text = "ot";
		tutorials = tutorialRepository.findByTitleContainingOrDescriptionContaining(text, text);
		show(tutorials);
/*
Tutorial [id=2, title=Java Spring Boot, description=Spring Framework Description, level=1, published=false, createdAt=2022-03-11 00:00:00.0]
Tutorial [id=4, title=Spring Boot, description=Spring Boot Description, level=2, published=false, createdAt=2022-04-26 00:00:00.0]
*/

		tutorials = tutorialRepository.findByTitleContainingAndPublished("ring", true);
		show(tutorials);
/*
Tutorial [id=1, title=Spring Data, description=Spring Data Description, level=3, published=true, createdAt=2022-03-11 00:00:00.0]
Tutorial [id=5, title=Spring JPA, description=Spring Data JPA Description, level=3, published=true, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=6, title=Spring Batch, description=Spring Batch Description, level=4, published=true, createdAt=2022-05-19 00:00:00.0]
*/

		tutorials = tutorialRepository.findByPublishedTrue();
		show(tutorials);
/*
Tutorial [id=1, title=Spring Data, description=Spring Data Description, level=3, published=true, createdAt=2022-03-11 00:00:00.0]
Tutorial [id=3, title=Hibernate, description=Hibernate ORM Description, level=3, published=true, createdAt=2022-04-26 00:00:00.0]
Tutorial [id=5, title=Spring JPA, description=Spring Data JPA Description, level=3, published=true, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=6, title=Spring Batch, description=Spring Batch Description, level=4, published=true, createdAt=2022-05-19 00:00:00.0]
*/

		tutorials = tutorialRepository.findByPublishedFalse();
		show(tutorials);
/*
Tutorial [id=2, title=Java Spring Boot, description=Spring Framework Description, level=1, published=false, createdAt=2022-03-11 00:00:00.0]
Tutorial [id=4, title=Spring Boot, description=Spring Boot Description, level=2, published=false, createdAt=2022-04-26 00:00:00.0]
Tutorial [id=7, title=Spring Security, description=Spring Security Description, level=5, published=false, createdAt=2022-05-19 00:00:00.0]
*/

		tutorials = tutorialRepository.findByLevelGreaterThan(3);
		show(tutorials);
/*
Tutorial [id=6, title=Spring Batch, description=Spring Batch Description, level=4, published=true, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=7, title=Spring Security, description=Spring Security Description, level=5, published=false, createdAt=2022-05-19 00:00:00.0]
*/

		Date myDate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-05-11");

		tutorials = tutorialRepository.findByCreatedAtGreaterThanEqual(myDate);
		show(tutorials);
/*
Tutorial [id=5, title=Spring JPA, description=Spring Data JPA Description, level=3, published=true, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=6, title=Spring Batch, description=Spring Batch Description, level=4, published=true, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=7, title=Spring Security, description=Spring Security Description, level=5, published=false, createdAt=2022-05-19 00:00:00.0]
*/

		tutorials = tutorialRepository.findByCreatedAtAfter(myDate);
		show(tutorials);
/*
Tutorial [id=5, title=Spring JPA, description=Spring Data JPA Description, level=3, published=true, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=6, title=Spring Batch, description=Spring Batch Description, level=4, published=true, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=7, title=Spring Security, description=Spring Security Description, level=5, published=false, createdAt=2022-05-19 00:00:00.0]
*/

		tutorials = tutorialRepository.findByLevelBetween(3, 5);
		show(tutorials);
/*
Tutorial [id=1, title=Spring Data, description=Spring Data Description, level=3, published=true, createdAt=2022-03-11 00:00:00.0]
Tutorial [id=3, title=Hibernate, description=Hibernate ORM Description, level=3, published=true, createdAt=2022-04-26 00:00:00.0]
Tutorial [id=5, title=Spring JPA, description=Spring Data JPA Description, level=3, published=true, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=6, title=Spring Batch, description=Spring Batch Description, level=4, published=true, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=7, title=Spring Security, description=Spring Security Description, level=5, published=false, createdAt=2022-05-19 00:00:00.0]
*/

		tutorials = tutorialRepository.findByLevelBetweenAndPublished(3, 5, true);
		show(tutorials);
/*
Tutorial [id=1, title=Spring Data, description=Spring Data Description, level=3, published=true, createdAt=2022-03-11 00:00:00.0]
Tutorial [id=3, title=Hibernate, description=Hibernate ORM Description, level=3, published=true, createdAt=2022-04-26 00:00:00.0]
Tutorial [id=5, title=Spring JPA, description=Spring Data JPA Description, level=3, published=true, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=6, title=Spring Batch, description=Spring Batch Description, level=4, published=true, createdAt=2022-05-19 00:00:00.0]
*/

		Date myDate1 = new SimpleDateFormat("yyyy-MM-dd").parse("2022-04-11");
		Date myDate2 = new SimpleDateFormat("yyyy-MM-dd").parse("2022-05-11");

		tutorials = tutorialRepository.findByCreatedAtBetween(myDate1, myDate2);
		show(tutorials);
/*
Tutorial [id=3, title=Hibernate, description=Hibernate ORM Description, level=3, published=true, createdAt=2022-04-26 00:00:00.0]
Tutorial [id=4, title=Spring Boot, description=Spring Boot Description, level=2, published=false, createdAt=2022-04-26 00:00:00.0]
*/

		tutorials = tutorialRepository.findByOrderByLevel();
		show(tutorials);
/*
Tutorial [id=2, title=Java Spring Boot, description=Spring Framework Description, level=1, published=false, createdAt=2022-03-11 00:00:00.0]
Tutorial [id=4, title=Spring Boot, description=Spring Boot Description, level=2, published=false, createdAt=2022-04-26 00:00:00.0]
Tutorial [id=1, title=Spring Data, description=Spring Data Description, level=3, published=true, createdAt=2022-03-11 00:00:00.0]
Tutorial [id=3, title=Hibernate, description=Hibernate ORM Description, level=3, published=true, createdAt=2022-04-26 00:00:00.0]
Tutorial [id=5, title=Spring JPA, description=Spring Data JPA Description, level=3, published=true, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=6, title=Spring Batch, description=Spring Batch Description, level=4, published=true, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=7, title=Spring Security, description=Spring Security Description, level=5, published=false, createdAt=2022-05-19 00:00:00.0]
*/

		tutorials = tutorialRepository.findByOrderByLevelDesc();
		show(tutorials);
/*
Tutorial [id=7, title=Spring Security, description=Spring Security Description, level=5, published=false, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=6, title=Spring Batch, description=Spring Batch Description, level=4, published=true, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=1, title=Spring Data, description=Spring Data Description, level=3, published=true, createdAt=2022-03-11 00:00:00.0]
Tutorial [id=3, title=Hibernate, description=Hibernate ORM Description, level=3, published=true, createdAt=2022-04-26 00:00:00.0]
Tutorial [id=5, title=Spring JPA, description=Spring Data JPA Description, level=3, published=true, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=4, title=Spring Boot, description=Spring Boot Description, level=2, published=false, createdAt=2022-04-26 00:00:00.0]
Tutorial [id=2, title=Java Spring Boot, description=Spring Framework Description, level=1, published=false, createdAt=2022-03-11 00:00:00.0]
*/

		tutorials = tutorialRepository.findByTitleContainingOrderByLevelDesc("at");
		show(tutorials);
/*
Tutorial [id=6, title=Spring Batch, description=Spring Batch Description, level=4, published=true, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=1, title=Spring Data, description=Spring Data Description, level=3, published=true, createdAt=2022-03-11 00:00:00.0]
Tutorial [id=3, title=Hibernate, description=Hibernate ORM Description, level=3, published=true, createdAt=2022-04-26 00:00:00.0]
*/

		tutorials = tutorialRepository.findByPublishedOrderByCreatedAtDesc(true);
		show(tutorials);
/*
Tutorial [id=5, title=Spring JPA, description=Spring Data JPA Description, level=3, published=true, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=6, title=Spring Batch, description=Spring Batch Description, level=4, published=true, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=3, title=Hibernate, description=Hibernate ORM Description, level=3, published=true, createdAt=2022-04-26 00:00:00.0]
Tutorial [id=1, title=Spring Data, description=Spring Data Description, level=3, published=true, createdAt=2022-03-11 00:00:00.0]
*/

		tutorials = tutorialRepository.findByTitleContaining("at", Sort.by("level").descending());
		show(tutorials);
/*
Tutorial [id=6, title=Spring Batch, description=Spring Batch Description, level=4, published=true, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=1, title=Spring Data, description=Spring Data Description, level=3, published=true, createdAt=2022-03-11 00:00:00.0]
Tutorial [id=3, title=Hibernate, description=Hibernate ORM Description, level=3, published=true, createdAt=2022-04-26 00:00:00.0]
*/

		tutorials = tutorialRepository.findByPublished(false, Sort.by("level").descending());
		show(tutorials);
/*
Tutorial [id=7, title=Spring Security, description=Spring Security Description, level=5, published=false, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=4, title=Spring Boot, description=Spring Boot Description, level=2, published=false, createdAt=2022-04-26 00:00:00.0]
Tutorial [id=2, title=Java Spring Boot, description=Spring Framework Description, level=1, published=false, createdAt=2022-03-11 00:00:00.0]
*/

		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Sort.Direction.DESC, "level"));
		orders.add(new Order(Sort.Direction.ASC, "title"));

		tutorials = tutorialRepository.findByPublished(true, Sort.by(orders));
		show(tutorials);
/*
Tutorial [id=6, title=Spring Batch, description=Spring Batch Description, level=4, published=true, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=3, title=Hibernate, description=Hibernate ORM Description, level=3, published=true, createdAt=2022-04-26 00:00:00.0]
Tutorial [id=1, title=Spring Data, description=Spring Data Description, level=3, published=true, createdAt=2022-03-11 00:00:00.0]
Tutorial [id=5, title=Spring JPA, description=Spring Data JPA Description, level=3, published=true, createdAt=2022-05-19 00:00:00.0]
*/

		int page = 0;
		int size = 3;

		Pageable pageable = PageRequest.of(page, size);

		tutorials = tutorialRepository.findAll(pageable).getContent();
		show(tutorials);
/*
Tutorial [id=1, title=Spring Data, description=Spring Data Description, level=3, published=true, createdAt=2022-03-11 00:00:00.0]
Tutorial [id=2, title=Java Spring Boot, description=Spring Framework Description, level=1, published=false, createdAt=2022-03-11 00:00:00.0]
Tutorial [id=3, title=Hibernate, description=Hibernate ORM Description, level=3, published=true, createdAt=2022-04-26 00:00:00.0]
*/

		tutorials = tutorialRepository.findByTitleContaining("ring", pageable).getContent();
		show(tutorials);
/*
Tutorial [id=1, title=Spring Data, description=Spring Data Description, level=3, published=true, createdAt=2022-03-11 00:00:00.0]
Tutorial [id=2, title=Java Spring Boot, description=Spring Framework Description, level=1, published=false, createdAt=2022-03-11 00:00:00.0]
Tutorial [id=4, title=Spring Boot, description=Spring Boot Description, level=2, published=false, createdAt=2022-04-26 00:00:00.0]
*/

		pageable = PageRequest.of(page, size, Sort.by("level").descending());

		tutorials = tutorialRepository.findAll(pageable).getContent();
		show(tutorials);
/*
Tutorial [id=7, title=Spring Security, description=Spring Security Description, level=5, published=false, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=6, title=Spring Batch, description=Spring Batch Description, level=4, published=true, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=5, title=Spring JPA, description=Spring Data JPA Description, level=3, published=true, createdAt=2022-05-19 00:00:00.0]
*/

		pageable = PageRequest.of(page, size, Sort.by("level").descending());

		tutorials = tutorialRepository.findByTitleContaining("at", pageable).getContent();
		show(tutorials);
/*
Tutorial [id=6, title=Spring Batch, description=Spring Batch Description, level=4, published=true, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=3, title=Hibernate, description=Hibernate ORM Description, level=3, published=true, createdAt=2022-04-26 00:00:00.0]
Tutorial [id=1, title=Spring Data, description=Spring Data Description, level=3, published=true, createdAt=2022-03-11 00:00:00.0]
*/

		Date outdate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-05-1");

		tutorialRepository.deleteAllByCreatedAtBefore(outdate);
		tutorials = tutorialRepository.findAll();
		show(tutorials);
/*
Tutorial [id=5, title=Spring JPA, description=Spring Data JPA Description, level=3, published=true, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=6, title=Spring Batch, description=Spring Batch Description, level=4, published=true, createdAt=2022-05-19 00:00:00.0]
Tutorial [id=7, title=Spring Security, description=Spring Security Description, level=5, published=false, createdAt=2022-05-19 00:00:00.0]
*/
	}

	private void show(List<Tutorial> tutorials) {
		tutorials.forEach(System.out::println);
	}
}
