package com.africainsight.newsservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "news")
@Getter
@Setter
public class News {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String title;

  @NotNull
  private String body;

  @NotNull
  private String url;

  private String imageUrl;
  private Long sourceId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "authorId")
  private Author author;

  private Instant createdAt;
  private Instant postDate;
}
