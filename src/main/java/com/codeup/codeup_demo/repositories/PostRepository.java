package com.codeup.codeup_demo.repositories;

import com.codeup.codeup_demo.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitle(String title);
//    List<Post> findPostByTitleIsContainingOrBodyContaining(String term1, String term2);
//    List<Post> findPostByTitleIsContaining(String term);
//    List<Post> findPostsByBodyContainingOrTitleContaining(String searchParam);
    List<Post> findByBodyIsLikeOrTitleIsLike(String term, String term2);

    @Query("from Post post where post.body like %:term%")
    List<Post> searchByBodyLike(@Param("term") String term);

    @Query("from Post post where post.body like %:term%")
    List<Post> searchByBodyLikeOrSearchTitleLike(@Param("term") String term, @Param("term")String term1);
}
