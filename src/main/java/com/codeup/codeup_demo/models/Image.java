package com.codeup.codeup_demo.models;

import javax.persistence.*;

@Entity
@Table(name="blog_images")
public class Image {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "TEXT")
    private String path;

    @ManyToOne
    @JoinColumn (name = "post_id")
    private Post post;

    public Image() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
