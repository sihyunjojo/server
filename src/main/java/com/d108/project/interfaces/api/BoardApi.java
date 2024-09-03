package com.d108.project.interfaces.api;


import com.d108.project.domain.board.dto.BoardCreateDto;
import com.d108.project.domain.board.dto.BoardResponseDto;
import com.d108.project.domain.board.dto.BoardUpdateDto;
import com.d108.project.domain.comment.dto.CommentCreateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BoardApi {
    @GetMapping
    ResponseEntity<List<BoardResponseDto>> getBoards();
    @GetMapping("/{boardId}")
    ResponseEntity<BoardResponseDto> getBoardById(@PathVariable Long boardId);
    @PostMapping
    ResponseEntity<String> createBoard(@RequestBody BoardCreateDto boardCreateDto);
    @PutMapping("/{boardId}")
    ResponseEntity<String> updateBoard(@PathVariable Long boardId, @RequestBody BoardUpdateDto boardUpdateDto);
    @DeleteMapping("/{boardId}")
    ResponseEntity<Void> deleteBoard(@PathVariable Long boardId);
    @PostMapping("/{boardId}/comment")
    ResponseEntity<String> createComment(@PathVariable Long boardId, @RequestBody CommentCreateDto commentCreateDto);
    @DeleteMapping("/{boardId}/comment/{commentId}")
    ResponseEntity<Void> deleteComment(@PathVariable Long boardId, @PathVariable Long commentId);
}
