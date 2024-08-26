package com.example.sonicboard.model.request;

import lombok.Data;

@Data
public class CommentPostRequest {
  private Long boardNo;
  private String commentBody;
}
