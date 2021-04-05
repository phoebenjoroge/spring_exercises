package com.codeup.codeup_demo.controllers;

import com.codeup.codeup_demo.models.Post;
import com.codeup.codeup_demo.models.User;
import com.codeup.codeup_demo.repositories.PostRepository;
import com.codeup.codeup_demo.repositories.UserRepository;
import com.codeup.codeup_demo.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {


    @Autowired
    private EmailService emailService;

    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao =userDao;
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
    public String onePost(@PathVariable Long id, Model viewModel) {
//        viewModel.addAttribute("post", new Post("Trial", "This is a test"));
        viewModel.addAttribute("post", postDao.getOne(id));
        return "posts/show";
    }
    @GetMapping(path = "/posts/create")
    public String createPost(Model viewModel) {
        viewModel.addAttribute("createPost", new Post());
        return "posts/create";
    }
    @PostMapping(path = "/posts/create")
    public String viewPost(@ModelAttribute Post newPost) {
        User userForPost = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newPost.setOwner(userForPost);
        Post savedPost = postDao.save(newPost);
        emailService.prepareAndSend(savedPost, "You have created a new post", "Your post has been successfully!");
        return "redirect:/posts";
    }
    @GetMapping(path = "/posts/{id}/update")
    public String updatePost(@PathVariable long id, Model viewModel) {
        Post onePost = postDao.getOne(id);
        viewModel.addAttribute("editPost",onePost);
        return "posts/edit";
    }
    @PostMapping(path = "/posts/{id}/update")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post newPost) {
        User userForPost = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newPost.setOwner(userForPost);
        postDao.save(newPost);
        return "redirect:/posts" ;
    }
    @PostMapping(path = "/posts/{id}/delete")
    @ResponseBody
    public String deletePost(@PathVariable Long id){
        postDao.deleteById(id);
        return "Post has been deleted" ;
    }

    @PostMapping(path = "/posts/search")
    public String searchPost(Model viewModel, @RequestParam(name = "search") String term) {
        term = "%"+term+"%";
        viewModel.addAttribute("post", postDao.searchByBodyLike(term));
        return "posts/results";
    }

}
