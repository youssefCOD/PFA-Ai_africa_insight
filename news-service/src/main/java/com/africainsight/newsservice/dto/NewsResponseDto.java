package com.africainsight.newsservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class NewsResponseDto {
  private String id;
  private String title;
  private String body;
  private String url;
  private String imageUrl;
  private String author;
  private Instant createdAt;
  private Instant postDate;
}
