package org.catscode.elastic.services;

import org.catscode.elastic.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createNewPost(PostDto postDto);

    PostDto findByTitle(String title);

    List<PostDto> findAllByAuthor(String author);
}
