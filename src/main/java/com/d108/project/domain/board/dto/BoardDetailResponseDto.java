package com.d108.project.domain.board.dto;


import com.d108.project.domain.comment.dto.CommentResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class BoardDetailResponseDto extends BoardResponseDto {
    private List<CommentResponseDto> comments;
}
