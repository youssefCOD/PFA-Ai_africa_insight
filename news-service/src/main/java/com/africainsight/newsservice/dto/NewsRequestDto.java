package com.africainsight.newsservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsRequestDto {
  @NotBlank(message = "Title is required")
  @Size(max = 1000, message = "Title shouldn't pass 1000 characters")
  private String title;

  @NotBlank(message = "Body is required")
  private String body;

  @NotBlank(message = "URL is required")
  private String url;

  private String imageUrl;
  private Long sourceId;
  private Long authorId;
}
