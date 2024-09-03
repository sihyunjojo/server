package com.d108.project.domain.board.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
public class BoardResponseDto {
    private Long id;
    private String title;
    private String description;
    private int commentsCount;
    private Long view;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
