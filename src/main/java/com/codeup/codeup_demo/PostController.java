package com.codeup.codeup_demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
class PostController {

    @GetMapping(path = "/posts")
    @ResponseBody
    public String allPosts() {
        return "All blog posts go here!";
    }
    @GetMapping(path = "/posts/{id}")
    @ResponseBody
    public String onePost(@PathVariable int id) {
        return "Return post " +id;
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
