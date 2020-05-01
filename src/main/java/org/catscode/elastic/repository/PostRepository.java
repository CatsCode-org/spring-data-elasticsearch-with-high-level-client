package org.catscode.elastic.repository;

import org.catscode.elastic.domain.Post;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends ElasticsearchRepository<Post, String> {

    Optional<Post> findByTitle(String title);

    List<Post> findAllByAuthor(String author);
}
