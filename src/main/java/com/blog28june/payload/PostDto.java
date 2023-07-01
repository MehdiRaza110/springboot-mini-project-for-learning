package com.blog28june.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PostDto {

    private long id;

    @NotEmpty
    @Size(min = 2, message = "title should be at least 2 charector")
    private String title;

    @NotEmpty
    @Size(min=4, message = "description should beat least 4 charector")
    private String description;

    @NotEmpty(message = "content should not be empty")
    private String content;
}
