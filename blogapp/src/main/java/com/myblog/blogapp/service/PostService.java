package com.myblog.blogapp.service;

import com.myblog.blogapp.payload.PostDto;
import com.myblog.blogapp.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);
      //(1)create pageable object to use pagination ............
      //List<PostDto> getAllPosts(int postNo,int postSize);


     //(2)take the response back to postman by creating PostResponse class object..........
     //PostResponse getAllPosts(int postNo, int postSize);

     //(3)pagination and sorting both.....
     PostResponse getAllPosts(int postNo,int postSize,String sortBy,String sortDir);

     PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePost(long id);
}
