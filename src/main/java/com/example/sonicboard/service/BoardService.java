package com.example.sonicboard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.sonicboard.model.DeleteStatus;
import com.example.sonicboard.model.entity.Board;
import com.example.sonicboard.model.request.BoardDeleteRequest;
import com.example.sonicboard.model.request.BoardPostRequest;
import com.example.sonicboard.model.response.BoardListResponse;
import com.example.sonicboard.model.response.BoardResponse;
import com.example.sonicboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

  private final BoardRepository boardRepository;

  public BoardResponse writeBoard(BoardPostRequest request) {
    Board board = new Board();
    board.setTitle(request.getTitle());
    board.setBody(request.getBody());
    board.setDeleteStatus(DeleteStatus.ACTIVE);//처음 생성되는거니까 무조건 ACTIVE
    return BoardResponse.from(boardRepository.save(board));
  }

  public List<BoardListResponse> searchBoardList(int page, int pageSize) {
    return boardRepository.findAllByDeleteStatus(
      DeleteStatus.ACTIVE,
      PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "boardNo"))
    ).map(BoardListResponse::from).toList();
  }

  public BoardResponse searchBoard(Long boardNo) {
    return boardRepository.findBoardWithCommentsByBoardNo(boardNo)
      .map(BoardResponse::from).orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));
  }

  public String deleteBoard(BoardDeleteRequest boardDeleteRequest) {

    Optional<Board> boardOptional = boardRepository.findById(boardDeleteRequest.getBoardNo());
    Board board = boardOptional.orElseThrow(() -> new RuntimeException("존재하지 않는 게시물 입니다."));

    boardRepository.delete(board);

    return "OK";
  }
}
