package com.bruno.crud_spring.dto.mapper;

import org.springframework.stereotype.Component;

import com.bruno.crud_spring.dto.CourseDTO;
import com.bruno.crud_spring.model.Course;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        return new CourseDTO(course.getId(), course.getName(), course.getCategory());
    }

    public Course toEntity(CourseDTO courseDTO) {

        if (courseDTO == null) {
            return null;
        }

        Course course = new Course();
        if (courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }

        course.setName(courseDTO.name());
        course.setCategory(courseDTO.category());
        course.setStatus("Ativo");

        return course;

        // Olhar Builder pattern - PENDENTE
    }
    
}
