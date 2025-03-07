package com.bruno.crud_spring.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.bruno.crud_spring.model.Lesson;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseDTO(
    @JsonProperty("_id") Long id,
    @NotBlank @NotNull @Length(min = 5, max = 100) String name, 
    @NotNull @Length(max = 10) String category,
    List<Lesson> lessons ) {
}
