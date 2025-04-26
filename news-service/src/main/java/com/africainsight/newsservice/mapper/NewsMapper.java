package com.africainsight.newsservice.mapper;

import com.africainsight.newsservice.dto.NewsRequestDto;
import com.africainsight.newsservice.dto.NewsResponseDto;
import com.africainsight.newsservice.model.News;

public class NewsMapper {
  public static NewsResponseDto toDto(News news) {
    NewsResponseDto dto = new NewsResponseDto();

    dto.setId(news.getId().toString());
    dto.setTitle(news.getTitle());
    dto.setBody(news.getBody());
    dto.setUrl(news.getUrl());
    dto.setImageUrl(news.getImageUrl());
    dto.setAuthor(news.getAuthor() != null ? news.getAuthor().getName() : null);
    dto.setCreatedAt(news.getCreatedAt());
    dto.setPostDate(news.getPostDate());

    return dto;
  }

  public static News toModel(NewsRequestDto newsRequestDto) {
    News news = new News();
    news.setTitle(newsRequestDto.getTitle());
    news.setBody(newsRequestDto.getBody());
    news.setUrl(newsRequestDto.getUrl());
    news.setImageUrl(newsRequestDto.getImageUrl());
    news.setSourceId(newsRequestDto.getSourceId());
    return news;
  }
}
