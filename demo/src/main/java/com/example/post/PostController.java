package com.example.post;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.exception.ResourceNotFoundException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
    PostService service;
 
   /*@GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> list = service.getAllPosts();
         
        return new ResponseEntity<List<Post>>(list, new HttpHeaders(), HttpStatus.OK);
    }*/
    
    /*dohladanie aj v externej api*/
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Get Post By Id", notes = "Finds post by id in my db")
    public ResponseEntity<Post> getPostById(@ApiParam(value = "Id of post which u want to get") @PathVariable("id") Long id) throws ResourceNotFoundException {

    	try{
    		Post entity = service.getPostById(id);
            return new ResponseEntity<Post>(entity, new HttpHeaders(), HttpStatus.OK);

    	}
    	catch ( ResourceNotFoundException e) {
    		Post entity = service.getPostbyIdFromExApi(id);
            return new ResponseEntity<Post>(entity, new HttpHeaders(), HttpStatus.OK);

    	}
     }
    
    @GetMapping("users/{user_id}")
    @ApiOperation(value = "Get Post By User Id", notes = "Finds post by user id using external API")
    public ResponseEntity<Post> getPostByUserId(@ApiParam(value = "Id of user whose post u want to get") @PathVariable("user_id") Long user_id) throws ResourceNotFoundException {
        Post entity = service.getPostByUserId(user_id);
 
        return new ResponseEntity<Post>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    
    @PostMapping("/")
    @ApiOperation(value = "Create & Update Post", notes = "Create or update post + validation of user id")
    public ResponseEntity<Post> createOrUpdatePost(@RequestBody Post post)
                                                    throws ResourceNotFoundException {
    	try {
			service.getUserById(post.getUserId());
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
	        throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}

    	
    	Post updated = service.createOrUpdatePost(post);
        return new ResponseEntity<Post>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Post By Id", notes = "Delete post by id")
    public HttpStatus deletePostById(@ApiParam(value = "Id of post which u want to delete") @PathVariable("id") Long id) 
                                                    throws ResourceNotFoundException {
        service.deletePostById(id);
        return HttpStatus.OK;
    }
   
   
    
    
}
