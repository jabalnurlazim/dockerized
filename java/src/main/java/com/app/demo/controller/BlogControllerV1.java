package com.app.demo.controller;

import com.app.demo.model.Blog;
import com.app.demo.params.BlogParams;
import com.app.demo.repository.BlogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(path="/blog/v1/")  // module-based API with versioning
@CrossOrigin(origins = "http://localhost:3000")
public class BlogControllerV1 {
    @Autowired
    private BlogRepository blogRepository;

    @GetMapping
    public @ResponseBody Iterable<Blog> getAllBlogs(){
        return blogRepository.findAll();
    }

    @PostMapping
    public @ResponseBody
    Blog addNewBlog (@RequestBody BlogParams blog) throws JsonProcessingException {
        Blog n = new Blog();
        n.setTitle(blog.getTitle());
        n.setContent(blog.getContent());
        return blogRepository.save(n);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addNewBlogJson")
    Blog addNewBlogJson (@RequestBody String blogJsonParams) throws JsonProcessingException {
        try{
            Map<String,Object> blogJsonMap =
                    new ObjectMapper().readValue(blogJsonParams, HashMap.class);

            Blog n = new Blog();
            n.setTitle((String)blogJsonMap.get("title"));
            n.setContent((String)blogJsonMap.get("content"));
            System.out.println(n);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return new Blog();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getBlogJson")
    Blog getBlogJson (@RequestBody String blogJsonParams) throws JsonProcessingException {
        try{
            Map<String,Object> blogJsonMap =
                    new ObjectMapper().readValue(blogJsonParams, HashMap.class);

            Blog n = new Blog();
            n.setTitle((String)blogJsonMap.get("title"));
            n.setContent((String)blogJsonMap.get("content"));
            System.out.println(n);
            return n;
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return new Blog();
    }

    @GetMapping(path="{id}/")
    public @ResponseBody
    Optional<Blog> getBlog (@PathVariable int id) {
        return blogRepository.findById(id);
    }

    @DeleteMapping(path="{id}/")
    public ResponseEntity<String> deleteBlog(@PathVariable int id){
        blogRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path="{id}/")
    public @ResponseBody
    Blog updateBlog (@RequestBody BlogParams blog,
                     @PathVariable int id) throws JsonProcessingException {
        Blog n = blogRepository.findById(id).get();
        n.setTitle(blog.getTitle());
        n.setContent(blog.getContent());
        return blogRepository.save(n);
    }
}
