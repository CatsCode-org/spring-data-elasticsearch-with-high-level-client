package org.catscode.elastic.dto;

import org.catscode.elastic.domain.Post;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDto {
    private String id;
    private String author;
    private String title;
    private String content;
    private LocalDateTime createdTime;

    public PostDto(Post post) {
        this.id = post.getId();
        this.author = post.getAuthor();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdTime = LocalDateTime.parse(post.getCreatedDate());
    }
}
