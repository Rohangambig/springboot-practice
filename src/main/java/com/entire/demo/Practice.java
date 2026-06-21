package com.entire.demo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Practice {
}

@Entity
@Table(name="user")
class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long id;

    private String username;
    private String password;


    @OneToMany(mappedBy="user",cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Post> posts;
}

@Entity
@Table(name="posts")
class Post {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date uploadedDate;

    private String postURI;

    private String description;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


    @OneToMany(mappedBy="post",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy="post",cascade=CascadeType.ALL, orphanRemoval = true)
    List<Like> like = new ArrayList<>();

}

@Entity
@Table
class Comment {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String message;

    private Date commentedDate;

    @ManyToOne
    @JoinColumn(name="post")
    private Post post;

    @OneToMany(mappedBy="comment",cascade = CascadeType.ALL)
    List<Like> like = new ArrayList<>();
}

@Entity
@Table
class Like {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="comment_id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;
}