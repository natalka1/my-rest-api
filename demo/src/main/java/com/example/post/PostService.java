package com.example.post;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.exception.ResourceNotFoundException;


@Service  
public class PostService  
{  
	@Autowired
    PostRepository repository;
	
 
    public List<Post> getAllPosts()
    {
        List<Post> postList = repository.findAll();
         
        if(postList.size() > 0) {
            return postList;
        } else {
            return new ArrayList<Post>();
        }
    }
    public User[] getUsers()
    {
        final String uri = "https://jsonplaceholder.typicode.com/users";
        RestTemplate restTemplate = new RestTemplate();
        
        User[] response = restTemplate.getForObject(
  			  uri,  User[].class);

    	/*List<User> listusers = Arrays.asList(response); 			
    			for (int i = 0; i < listusers.size(); i++) {
    	    	    System.out.println(listusers.get(i).getName());
    	    	}*/
        
        return response;
    }
    public Post getPostbyIdFromExApi(long id) //get posts from external api
    {
        final String uri = "https://jsonplaceholder.typicode.com/posts";
        RestTemplate restTemplate = new RestTemplate();
        
        Post[] response = restTemplate.getForObject(
  			  uri,  Post[].class);

    	List<Post> postlist = Arrays.asList(response); 			
    			for (int i = 0; i < postlist.size(); i++) {
    				if(id == postlist.get(i).getId()) {
    	    	    System.out.println(postlist.get(i).getTitle());
    	    	    return postlist.get(i);
    	    	}
        
    			}

    	throw new ResourceNotFoundException("post", "id", Long.toString(id));
    }
    public User getUserById(Long id) throws ResourceNotFoundException
    {
        User[] users = getUsers();
        List<User> listusers = Arrays.asList(users); 			
		for (int i = 0; i < listusers.size(); i++) {
			if(listusers.get(i).getId() == id) {
				
	    	  System.out.println("User "+ listusers.get(i).getName() + "ma id " + listusers.get(i).getId() + "zhoduje sa s pridavajucim id: " + id );
	    	  return listusers.get(i);
	    	
			}
    	}
    
    	throw new ResourceNotFoundException("user", "id", Long.toString(id));
  
           
    }
    
    public Post getPostById(Long id) throws ResourceNotFoundException 
    {
    	
        Optional<Post> post = repository.findById(id);
         
        if(post.isPresent()) {
            return post.get();
        } else {
            throw new ResourceNotFoundException("post","id",Long.toString(id));
        }
    }
    public Post getPostByUserId(Long user_id) throws ResourceNotFoundException 
    {
    	
        List<Post> postlist = getAllPosts();
        
        for (int i = 0; i < postlist.size(); i++) {
			if(postlist.get(i).getUserId() == user_id) {
				
	    	  System.out.println("Post s id: "+ postlist.get(i).getId() + "ma user id " + postlist.get(i).getUserId() + user_id );
	    	  return postlist.get(i);
	    	
			}
    	}
        throw new ResourceNotFoundException("user","user_id",Long.toString(user_id));
    
  
    }
     
    public Post createOrUpdatePost(Post entity) throws ResourceNotFoundException 
    {
    	//- Pridanie prispevku - potrebné validovať userID pomocou externej API

        Optional<Post> post = repository.findById(entity.getId());
         
        //toto je update
        if(post.isPresent()) 
        {
            Post newEntity = post.get();
            newEntity.setTitle(entity.getTitle()); //vramci update sa da zmenit title
            newEntity.setBody(entity.getBody()); //a taktiez body
 
            newEntity = repository.save(newEntity);
             
            return newEntity;
        } else {
        	
            entity = repository.save(entity);
             
            return entity;
        }
    } 
    
    public void deletePostById(Long id) throws ResourceNotFoundException 
    {
        Optional<Post> post = repository.findById(id);


        if(post.isPresent()) 
        {
            repository.deleteById(id);
        } else {
        	throw new ResourceNotFoundException("post","id",Long.toString(id));
        }
    } 
     
}