package com.github.union.blog.controller;

import com.github.union.blog.domain.Post;
import com.github.union.blog.dto.PostModel;
import com.github.union.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by union on 7/05/16.
 */

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping("/")
    public ResponseEntity<?> getAllPosts() {
        return ResponseEntity.ok(postService.findAll());
    }

    @RequestMapping("/{id}")
    public ResponseEntity<?> getOnePostById(@PathVariable Integer id) {
        return ResponseEntity.ok(postService.findOnePostById(id));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> addNewPost(@RequestBody PostModel model) {
        Post post = new Post();
        post.setName(model.getName());
        post.setDescription(model.getDescription());
        post.setText(model.getText());
        post.setDate(LocalDate.now());
        post.setAuthor(model.getAuthor());
        postService.save(post);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePostById(@PathVariable Integer id) {
        postService.deletePostById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}