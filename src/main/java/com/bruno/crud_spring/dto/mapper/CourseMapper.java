package com.bruno.crud_spring.dto.mapper;

import org.springframework.stereotype.Component;

import com.bruno.crud_spring.dto.CourseDTO;
import com.bruno.crud_spring.enums.Category;
import com.bruno.crud_spring.model.Course;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue(),
                course.getLessons()
        );
    }

    public Course toModel(CourseDTO courseDTO) {

        if (courseDTO == null) {
            return null;
        }

        Course course = new Course();
        if (courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }

        course.setName(courseDTO.name());
        course.setCategory(toCategory(courseDTO.category()));
        // AQUI
        
        return course;

        // Olhar Builder pattern - PENDENTE
    }

    public Category toCategory(String category) {
        if (category == null) {
            return null;
        }
        //return Category.valueOf(category);

        return switch (category) {
            case "Back-end" -> Category.BACKEND;
            case "Front-end" -> Category.FRONTEND;
            default -> throw new IllegalArgumentException("Categoria inválida: " + category);
        };

        
    }
    
}
