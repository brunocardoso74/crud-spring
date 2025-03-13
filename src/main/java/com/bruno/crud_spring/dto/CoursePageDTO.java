package com.bruno.crud_spring.dto;

import java.util.List;

public record CoursePageDTO(List<CourseDTO> courses, long totalElements, long totalPages) {
    /* 
    public CoursePageDTO {
        if (totalPages < 0) {
            throw new IllegalArgumentException("Total pages must be greater than zero");
        }
        if (totalElements < 0) {
            throw new IllegalArgumentException("Total elements must be greater than zero");
        }
    }
    */
}
