package com.africainsight.newsservice.controller;


import com.africainsight.newsservice.dto.NewsRequestDto;
import com.africainsight.newsservice.dto.NewsResponseDto;
import com.africainsight.newsservice.service.NewsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
  private final NewsService newsService;

  public NewsController(NewsService postService) {
    this.newsService = postService;
  }

  @GetMapping
  public ResponseEntity<List<NewsResponseDto>> getAllNews() {
    List<NewsResponseDto> posts = newsService.findAll();
    return ResponseEntity.ok().body(posts);
  }

  @PostMapping
  public ResponseEntity<NewsResponseDto> AddNews(@Valid @RequestBody NewsRequestDto postRequestDto) {
    NewsResponseDto postResponseDto = newsService.addNews(postRequestDto);
    return ResponseEntity.ok().body(postResponseDto);
  }

//  @GetMapping("/{id}")
//  public ResponseEntity<NewsResponseDto> getNewsById(@PathVariable Long id) {
//    return newsService
//        .findById(id) != null ?
//        ResponseEntity.ok().body(newsService.findById(id))
//        : ResponseEntity.notFound().build();
//  }
}
