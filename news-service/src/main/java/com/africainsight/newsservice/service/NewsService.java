package com.africainsight.newsservice.service;

import com.africainsight.newsservice.dto.NewsRequestDto;
import com.africainsight.newsservice.dto.NewsResponseDto;
import com.africainsight.newsservice.mapper.NewsMapper;
import com.africainsight.newsservice.model.News;
import com.africainsight.newsservice.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.List; // Add this import

@Service
public class NewsService {
  private final NewsRepository newsRepository;

  public NewsService(NewsRepository postRepository) {
    this.newsRepository = postRepository;
  }

  public List<NewsResponseDto> findAll() {
    List<News> newsList = newsRepository.findAll();
    return newsList.stream().map(NewsMapper::toDto).toList();
  }

  public NewsResponseDto addNews(NewsRequestDto postRequestDto) {
    News post = newsRepository.save(NewsMapper.toModel(postRequestDto));
    return NewsMapper.toDto(post);
  }
}
