package cz.czechitas.java2webapps.ukol8.service;

import cz.czechitas.java2webapps.ukol8.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import cz.czechitas.java2webapps.ukol8.repository.PostRepository;

/**
 * Service for working with posts
 */
@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * Returns all posts (without sorting)
     */
    public Page<Post> list(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    /**
     * Returns all posts sorted by published date in descending order
     */
    public Page<Post> listByPublished() {
        final Pageable pageable = PageRequest.of(0, 20, Sort.by("published").descending());
        return postRepository.findAllByPublished(pageable);
    }

    /**
     * Returns a post found by slug
     */
    public Post singlePost(String slug) {
        return postRepository.findBySlug(slug);
    }
}
