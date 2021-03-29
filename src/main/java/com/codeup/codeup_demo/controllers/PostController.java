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
//        List<Post> posts = postDao.findByTitle("test1");
        List<Post> allPosts = postDao.findAll();
        viewModel.addAttribute("posts", allPosts);
        return "posts/index";
    }
    @GetMapping(path = "/posts/{id}")
    public String onePost(@PathVariable long id, Model viewModel) {
//        viewModel.addAttribute("post", new Post("Trial", "This is a test"));
        viewModel.addAttribute("post", postDao.getOne(id));
        return "posts/show";
    }
    @GetMapping(path = "/posts/create")
    public String createPost() {
        return "posts/create";
    }
    @PostMapping(path = "/posts/create")
    @ResponseBody
    public String viewPost(@RequestParam("title") String title, @RequestParam("body") String body) {
        Post newPost = new Post(title, body);
        postDao.save(newPost);
        return "Display created post" ;
    }
    @GetMapping(path = "/posts/{id}/update")
    public String updatePost(@PathVariable long id, Model viewModel) {

        Post onePost = postDao.getOne(id);

        viewModel.addAttribute("editPost",onePost);

        return "posts/edit";
    }
    @PostMapping(path = "/posts/{id}/update")
    @ResponseBody
    public String updatePost(@PathVariable Long id, @RequestParam("title") String title, @RequestParam("body") String body) {
        Post updatePost = new Post(id, title, body);
        postDao.save(updatePost);
        return "Past has been updated" ;
    }
    @PostMapping(path = "/posts/{id}/delete")
    @ResponseBody
    public String deletePost(@PathVariable Long id){
        postDao.deleteById(id);
        return "Post has been deleted" ;
    }
}
