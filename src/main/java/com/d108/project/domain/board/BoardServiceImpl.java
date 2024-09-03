package com.d108.project.domain.board;


import com.d108.project.domain.board.dto.BoardCreateDto;
import com.d108.project.domain.board.dto.BoardDetailResponseDto;
import com.d108.project.domain.board.dto.BoardResponseDto;
import com.d108.project.domain.board.dto.BoardUpdateDto;
import com.d108.project.domain.comment.Comment;
import com.d108.project.domain.comment.dto.CommentResponseDto;
import com.d108.project.domain.user.User;
import com.d108.project.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    // 글 작성
    @Override
    public void createBoard(BoardCreateDto boardCreateDto) {
        User user = userRepository.findById(boardCreateDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("아이디 오류"));

        Board board = Board.builder()
                .title(boardCreateDto.getTitle())
                .description(boardCreateDto.getDescription())
                .user(user)
                .view(0L)
                .build();

        boardRepository.save(board);
    }

    // 전체 글 조회
    @Override
    public List<BoardResponseDto> getBoards() {
        List<Board> boards = boardRepository.findAll();

        return boards.stream()
                .map(this::convertBoardToDto)
                .collect(Collectors.toList());
    }

    // 특정 글 조회
    @Override
    public BoardDetailResponseDto getBoardById(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("게시글 번호를 찾을 수 없음"));

        List<CommentResponseDto> commentDtos = board.getComments().stream()
                .map(this::convertCommentToDto)
                .collect(Collectors.toList());

        return BoardDetailResponseDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .description(board.getDescription())
                .view(board.getView())
                .comments(commentDtos)
                .userId(board.getUser().getUserId())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }

    // 글 수정
    @Override
    public Board updateBoardById(Long boardId, BoardUpdateDto boardUpdateDto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("게시글 번호를 찾을 수 없음"));
        
        // 두개만 수정
        board.setTitle(boardUpdateDto.getTitle());
        board.setDescription(boardUpdateDto.getDescription());
        
        return boardRepository.save(board);
    }

    // 글 삭제
    @Override
    public void deleteBoardById(Long boardId) {
        boardRepository.deleteById(boardId);
    }


    // Entity -> DTO 변환
    private BoardResponseDto convertBoardToDto(Board board) {
        return BoardResponseDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .description(board.getDescription())
                .commentsCount(board.getComments().size())
                .view(board.getView())
                .userId(board.getUser().getUserId())        // 여기서 유저의 정보를 같이 가져오면 되지 않나? (여기서 getUser().getUsername()을 하면 유저 네임이 DTO에 담김)
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }

    private CommentResponseDto convertCommentToDto(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .userId(comment.getUser().getUserId())
                .build();
    }
}
