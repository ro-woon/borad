package com.example.sonicboard.model.response;

import com.example.sonicboard.model.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentResponse {
  private Long commentNo;
  private String body;

  public static CommentResponse from(Comment comment) {
    return new CommentResponse(comment.getCommentNo(), comment.getBody());
  }

}
