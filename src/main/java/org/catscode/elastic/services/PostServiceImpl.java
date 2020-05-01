package org.catscode.elastic.services;

import org.catscode.elastic.domain.Post;
import org.catscode.elastic.dto.PostDto;
import org.catscode.elastic.exceptions.EntityCreationException;
import org.catscode.elastic.exceptions.EntityNotFoundException;
import org.catscode.elastic.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public PostDto createNewPost(PostDto postDto) {
        final Post post = Post.builder()
                .content(postDto.getContent())
                .author(postDto.getAuthor())
                .title(postDto.getTitle())
                .createdDate(dateFormat.format(new Date()))
                .build();

        final Post newPost = postRepository.index(post);
        if (newPost != null) {
            return new PostDto(post);
        } else {
            throw new EntityCreationException(String.format("Cannot create new Post: %s", postDto));
        }
    }

    @Override
    public PostDto findByTitle(String title) {
        final Optional<Post> postOpt = postRepository.findByTitle(title);
        if (postOpt.isPresent()) {
            return postOpt.get().toDto();
        } else {
            throw new EntityNotFoundException(String.format("Post with title: %s not found", title));
        }
    }

    @Override
    public List<PostDto> findAllByAuthor(String author) {
        return postRepository.findAllByAuthor(author)
                .stream()
                .map(Post::toDto)
                .collect(Collectors.toList());
    }
}
