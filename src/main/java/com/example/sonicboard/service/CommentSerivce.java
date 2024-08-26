package com.example.sonicboard.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.sonicboard.model.entity.Board;
import com.example.sonicboard.model.request.CommentPostRequest;
import com.example.sonicboard.model.response.BoardResponse;
import com.example.sonicboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentSerivce {
  private final BoardRepository boardRepository;


  public BoardResponse writeComment(CommentPostRequest commentPostRequest){
    Optional<Board> boardOptional = boardRepository.findBoardWithCommentsByBoardNo(commentPostRequest.getBoardNo());
    Board board = boardOptional.orElseThrow(() -> new RuntimeException("존재하지 않는 게시물 입니다."));

    board.addComment(commentPostRequest.getCommentBody());
    boardRepository.save(board);

    return BoardResponse.from(board);
  }


}
