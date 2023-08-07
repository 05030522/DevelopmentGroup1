package com.sparta.developmentgroup1.column.service;

import com.sparta.developmentgroup1.column.dto.ColumnRequestDto;
import com.sparta.developmentgroup1.column.dto.ColumnUpdateRequestDto;
import com.sparta.developmentgroup1.column.entity.Column;
import com.sparta.developmentgroup1.column.repository.ColumnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ColumnService {

    private final BoardRepository boardRepository;
    private final ColumnRepository columnRepository;

    public void createColumn(ColumnRequestDto requestDto, UserDetailsImpl userDetails) {
        Board board = boardRepository.findById(requestDto.getBoardId()).orElseThrow(
                () -> new IllegalArgumentException("요청한 보드가 존재하지 않습니다.")
        );

        Column column = new Column(requestDto.getName(), board, userDetails.getUser());
        columnRepository.save(column);
    }

    @Transactional
    public void updateColumn(ColumnUpdateRequestDto requestDto, Long id, UserDetailsImpl userDetails) {
        Column column = columnRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("요청한 컬럼이 존재하지 않습니다."));

        if (userDetails.getUser().getId().equals(column.getUser().getId())) {
            // 돌려보고 DB에 잘 안들어가면 메서드로 뽑기
            columnRepository.save(new Column(requestDto.getName(), column.getBoard(), column.getUser()));
        } else throw new IllegalArgumentException("본인의 컬럼만 수정할 수 있습니다.");
    }

    @Transactional
    public void deleteColumn(Long id, UserDetailsImpl userDetails) {
        Column column = columnRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("요청한 컬럼이 존재하지 않습니다."));

        if (userDetails.getUser().getId().equals(column.getUser().getId())) {
            columnRepository.delete(column);
        } else throw new IllegalArgumentException("본인의 컬럼만 삭제할 수 있습니다.");
    }
}