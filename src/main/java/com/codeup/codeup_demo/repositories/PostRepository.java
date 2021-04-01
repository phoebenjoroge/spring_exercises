package com.codeup.codeup_demo.repositories;

import com.codeup.codeup_demo.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

//    List<Post> findByTitle(String title);
//    List<Post> findPostByTitleIsContainingOrBodyContaining(String term1, String term2);
//    List<Post> findPostByTitleIsContaining(String term);
//    List<Post> findPostsByBodyContainingOrTitleContaining(String searchParam);
    List<Post> findByBodyIsLikeOrTitleIsLike(String term, String term2);
}
