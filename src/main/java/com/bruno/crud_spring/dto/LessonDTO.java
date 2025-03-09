package com.bruno.crud_spring.dto;


public record LessonDTO(
    Long _id,
    String name,
    String youtubeUrl) {
    //private Long courseId;
}