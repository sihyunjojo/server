package com.d108.project.domain.board.dto;


import com.d108.project.domain.comment.dto.CommentResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class BoardDetailResponseDto {
    private Long id;
    private String title;
    private String description;
    private List<CommentResponseDto> comments;
    private Long view;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
