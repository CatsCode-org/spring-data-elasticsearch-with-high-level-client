package org.catscode.elastic.web.controllers;

import org.catscode.elastic.dto.PostCreateRequest;
import org.catscode.elastic.dto.PostDto;
import org.catscode.elastic.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody @Valid PostCreateRequest createRequest, HttpServletRequest request) {
        final PostDto dto = PostDto.builder()
                .author(createRequest.getAuthor())
                .content(createRequest.getContent())
                .title(createRequest.getTitle())
                .build();

        final PostDto newPost = postService.createNewPost(dto);
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", request.getRequestURL() + "/" + newPost.getId());

        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping(value = "/search", params = "author")
    public ResponseEntity<List<PostDto>> findAllByAuthor(@RequestParam("author") String author) {
        return ResponseEntity.ok(postService.findAllByAuthor(author));
    }

    @GetMapping(value = "/search", params = "title")
    public ResponseEntity<PostDto> findPostByTitle(@RequestParam("title") String title) {
        return ResponseEntity.ok(postService.findByTitle(title));
    }
}
