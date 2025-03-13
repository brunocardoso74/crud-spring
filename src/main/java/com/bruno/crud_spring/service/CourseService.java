package com.bruno.crud_spring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.bruno.crud_spring.dto.CourseDTO;
import com.bruno.crud_spring.dto.CoursePageDTO;
import com.bruno.crud_spring.dto.mapper.CourseMapper;
import com.bruno.crud_spring.exception.RecordNotFoundException;
import com.bruno.crud_spring.model.Course;
import com.bruno.crud_spring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;


@Validated
@Service
public class CourseService {
    
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public CoursePageDTO list(@PositiveOrZero int pageNumber, @Positive @Max(100) int pageSize) {
        Page<Course> pageCourse = courseRepository.findAll(PageRequest.of(pageNumber, pageSize));
        List<CourseDTO> courses = pageCourse.get().map(courseMapper::toDTO).collect(Collectors.toList());
        return new CoursePageDTO(courses, pageCourse.getTotalElements(), pageCourse.getTotalPages());
        
        /*return new CoursePageDTO(
            page.getContent()
                .stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList()),
            page.getTotalElements(),
            page.getTotalPages()
        );*/
    }

    /* 
    public List<CourseDTO> list() {
        return courseRepository.findAll()
            .stream()
            .map(courseMapper::toDTO)
            .collect(Collectors.toList()); 
    }
    */

    public CourseDTO findById(@NotNull @Positive Long id) {
        return courseRepository.findById(id)
            .map(courseMapper::toDTO)
            .orElseThrow( () -> new RecordNotFoundException(id) );
    }

    public CourseDTO create(@Valid @NotNull CourseDTO courseDTO) {
        return courseMapper.toDTO(courseRepository.save(courseMapper.toModel(courseDTO)));
    }

    public CourseDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseDTO courseDTO) {
        return courseRepository.findById(id)
            .map(recordFound -> {
                Course course = courseMapper.toModel(courseDTO);

                recordFound.setName(courseDTO.name());
                recordFound.setCategory(this.courseMapper.toCategory(courseDTO.category()));
                //recordFound.setLessons(course.getLessons());

                recordFound.getLessons().clear();
                course.getLessons().forEach(recordFound.getLessons()::add);

                return courseMapper.toDTO(courseRepository.save(recordFound));
            }).orElseThrow( () -> new RecordNotFoundException(id) );
    }

    public void delete(@NotNull @Positive Long id) {
        //courseRepository.delete(courseRepository.findById(id).orElseThrow( () -> new RecordNotFoundException(id) ));
        
        courseRepository.findById(id)
            .map(recordFound -> {
                courseRepository.deleteById(id);
                return true;
            })
            .orElseThrow( () -> new RecordNotFoundException(id) );
    }
}

