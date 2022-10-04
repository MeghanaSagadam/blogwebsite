package com.meghana.blogproject.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class PostDTO {
    private long id;
    @NotEmpty
    @Size(min = 2, message = "post title should at least 2 character")
    private String title;
    @NotEmpty
    @Size(min = 10, message = "post title should at least 10 character")
    private String description;
    @NotEmpty
    private String content;
    private Set<CommentDTO> comments;
}
