package com.myblog.blogapp.controller;

import com.myblog.blogapp.payload.PostDto;
import com.myblog.blogapp.payload.PostResponse;
import com.myblog.blogapp.service.PostService;
import com.myblog.blogapp.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Object> createPost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
    //(1)create pageable object to use pagination ............
//    @GetMapping
//    public List<PostDto> getAllPosts
//            ( @RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
//              @RequestParam(value = "pageSize",defaultValue="10",required = false) int pageSize
//
//            ){
//        List<PostDto> postdto = postService.getAllPosts(pageNo,pageSize);
//        return postdto;
//    }

    //(2)take the response back to postman by creating PostResponse class object..........
//    @GetMapping
//    public PostResponse getAllPosts
//            ( @RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
//              @RequestParam(value = "pageSize",defaultValue="10",required = false) int pageSize
//
//            ){
//        PostResponse response = postService.getAllPosts(pageNo, pageSize);
//        return response;
//    }

    //(3)pagination and sorting both........
    @GetMapping
    public PostResponse getAllPosts
    ( @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
      @RequestParam(value = "pageSize",defaultValue=AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
      @RequestParam(value = "sortBy", defaultValue=AppConstants.DEFAULT_SORT_BY ,required=false) String sortBy,
      @RequestParam(value="sortDir", defaultValue=AppConstants.DEFAULT_SORT_DIR,required=false)String sortDir
    ){

        PostResponse response = postService.getAllPosts(pageNo, pageSize, sortBy,sortDir);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){
        PostDto dto = postService.getPostById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable("id") long id){
        PostDto dto = postService.updatePost(postDto, id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post Entity deleted successfully...",HttpStatus.OK);
    }
}
