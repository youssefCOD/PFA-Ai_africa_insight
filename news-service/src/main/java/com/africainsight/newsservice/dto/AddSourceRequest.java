package com.africainsight.newsservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddSourceRequest {
  private String url;
  private boolean containsAiContent;
  private boolean containsAfricaContent;
}
