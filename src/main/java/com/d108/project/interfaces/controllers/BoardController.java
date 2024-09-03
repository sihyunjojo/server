package com.d108.project.interfaces.controllers;

import com.d108.project.domain.board.Board;
import com.d108.project.domain.board.BoardService;
import com.d108.project.domain.board.dto.BoardCreateDto;
import com.d108.project.domain.board.dto.BoardResponseDto;
import com.d108.project.domain.board.dto.BoardUpdateDto;
import com.d108.project.domain.comment.CommentService;
import com.d108.project.domain.comment.dto.CommentCreateDto;
import com.d108.project.interfaces.api.BoardApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/board")
public class BoardController implements BoardApi {

    private final BoardService boardService;
    private final CommentService commentService;

    @Autowired
    public BoardController(BoardService boardService, CommentService commentService) {
        this.boardService = boardService;
        this.commentService = commentService;
    }

    @Override
    public ResponseEntity<List<BoardResponseDto>> getBoards() {
        return ResponseEntity.ok(boardService.getBoards());
    }

    @Override
    public ResponseEntity<BoardResponseDto> getBoardById(@PathVariable Long boardId) {
        BoardResponseDto boardResponseDto = boardService.getBoardById(boardId);
        return ResponseEntity.ok(boardResponseDto);
    }

    @Override
    public ResponseEntity<String> createBoard(@RequestBody BoardCreateDto boardCreateDto) {
        boardService.createBoard(boardCreateDto);
        return ResponseEntity.ok("게시글 작성 완료");
    }

    @Override
    public ResponseEntity<String> updateBoard(@PathVariable Long boardId, @RequestBody BoardUpdateDto boardUpdateDto) {
        Board board = boardService.updateBoardById(boardId, boardUpdateDto);
        return ResponseEntity.ok("게시글 수정 완료");
    }

    @Override
    public ResponseEntity<Void> deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoardById(boardId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<String> createComment(@PathVariable Long boardId, @RequestBody CommentCreateDto commentCreateDto) {
        commentService.createComment(boardId, commentCreateDto);
        return ResponseEntity.ok("댓글 작성 완료");
    }

    @Override
    public ResponseEntity<Void> deleteComment(@PathVariable Long boardId, @PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
