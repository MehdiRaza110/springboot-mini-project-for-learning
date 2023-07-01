package com.blog28june.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDto {
    private long id;
    @NotEmpty
    @Size(min = 2, message = "name at least contains 2 charector")
    private String name;

    @NotEmpty(message = "Enter your email")
    @Email
    private String email;

    @NotEmpty
    @Size(min = 4, message = "body should be at least 4 charector")
    private String body;

}
