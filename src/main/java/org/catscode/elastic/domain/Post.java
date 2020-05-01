package org.catscode.elastic.domain;

import org.catscode.elastic.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "posts")
public class Post {

    @Id
    private String id;
    private String author;
    private String title;
    private String content;
    private String createdDate;

    public PostDto toDto() {
        final PostDto dto = new PostDto();
        dto.setId(this.id);
        dto.setAuthor(this.author);
        dto.setCreatedTime(LocalDateTime.parse(this.createdDate));
        dto.setContent(this.content);
        dto.setTitle(this.content);

        return dto;
    }
}
