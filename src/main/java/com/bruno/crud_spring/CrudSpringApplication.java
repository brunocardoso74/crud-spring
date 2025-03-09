package com.bruno.crud_spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bruno.crud_spring.enums.Category;
import com.bruno.crud_spring.enums.Status;
import com.bruno.crud_spring.model.Course;
import com.bruno.crud_spring.model.Lesson;
import com.bruno.crud_spring.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();

			Course c = new Course();
			c.setName("Angular com Spring");
			c.setCategory(Category.FRONTEND);
			c.setStatus(Status.ACTIVE);

			Lesson l = new Lesson();
			l.setName("Introdução");
			l.setYoutubeUrl("https://www.youtube.com/watch?v=3q4m4iZMm1Y");
			l.setCourse(c);
			c.getLessons().add(l);

			Lesson l1 = new Lesson();
			l1.setName("Angular");
			l1.setYoutubeUrl("https://www.youtube.com/watch?v=3q4m4iZMm1Y");
			l1.setCourse(c);
			c.getLessons().add(l1);
			
			courseRepository.save(c);
		};
	}

}
