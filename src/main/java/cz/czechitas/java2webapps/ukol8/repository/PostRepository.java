package cz.czechitas.java2webapps.ukol8.repository;

import cz.czechitas.java2webapps.ukol8.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * Finds post by slug
     */
    Post findBySlug(String slug);

    /**
     * Finds all post containing published date (not in future)
     */
    @Query("SELECT p FROM Post p WHERE p.published <= NOW() AND p.published IS NOT NULL")
    Page<Post> findAllByPublished(Pageable pageable);
}
