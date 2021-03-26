package com.codeup.codeup_demo.controllers;

import com.codeup.codeup_demo.models.Post;
import com.codeup.codeup_demo.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    List<Post> posts = new ArrayList<>();

    @GetMapping(path = "/posts")
    public String allPosts(Model viewModel) {
        List<Post> posts = postDao.findByTitle("test1");
        viewModel.addAttribute("posts", posts);
        return "posts/index";
    }
    @GetMapping(path = "/posts/{id}")
    public String onePost(@PathVariable int id, Model viewModel) {
        viewModel.addAttribute("post", new Post("Trial", "This is a test"));
        return "posts/show";
    }
    @GetMapping(path = "/posts/create")
    @ResponseBody
    public String createPost() {
        return "Form for creating post goes here";
    }
    @PostMapping(path = "/posts/create")
    @ResponseBody
    public String sendPost() {
        return "Display created post" ;
    }
}
