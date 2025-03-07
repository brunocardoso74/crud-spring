package com.bruno.crud_spring.model;

import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.bruno.crud_spring.enums.Category;
import com.bruno.crud_spring.enums.Status;
import com.bruno.crud_spring.enums.converters.CategoryConverter;
import com.bruno.crud_spring.enums.converters.StatusConverter;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
//@Table(name = "cursos")
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' WHERE id = ?")
@SQLRestriction("status <> 'Inativo'")
public class Course {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    //@Column(name = "nome")
    @NotBlank
    @NotNull
    @Length(min = 5, max = 100)
    @Column(length = 100, nullable = false)
    private String name;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = CategoryConverter.class)
    private Category category;
    
    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ACTIVE;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Lesson.class, mappedBy = "course") 
    private List<Lesson> lessons = new ArrayList<>();

    
    
}
