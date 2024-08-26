package com.example.sonicboard.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sonicboard.model.request.BoardDeleteRequest;
import com.example.sonicboard.model.request.BoardPostRequest;
import com.example.sonicboard.model.response.BoardListResponse;
import com.example.sonicboard.model.response.BoardResponse;
import com.example.sonicboard.service.BoardService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  //게시물 등록
  @PostMapping("board")
  public BoardResponse writeBoard(
    @RequestBody BoardPostRequest boardPostRequest
  ){
    return boardService.writeBoard(boardPostRequest);
  }

  //조회
  //페이징 조회. 다건조회. 댓글은 가져오지X, 게시물 목록
  @GetMapping("boards")
  public List<BoardListResponse> searchBoardList(
    @RequestParam("page") int page,
    @RequestParam("pageSize") int pageSize
  ) {
    return boardService.searchBoardList(page, pageSize);
  }

  //단건 조회
  @GetMapping
  public BoardResponse searchBoard(
    @RequestParam("boardNo") Long boardNo
  ) {
    return boardService.searchBoard(boardNo);
  }

  //게시물 삭제
  @DeleteMapping("board")
  public String deleteBoard(
    @RequestBody BoardDeleteRequest boardDeleteRequest
  ){
    return boardService.deleteBoard(boardDeleteRequest);
  }


}
