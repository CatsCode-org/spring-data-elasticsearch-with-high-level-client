package org.catscode.elastic.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PostCreateRequest {

    @NotBlank
    private String author;

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
