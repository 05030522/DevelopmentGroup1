package com.sparta.developmentgroup1.column.service;

import com.sparta.developmentgroup1.column.dto.ColumnRequestDto;
import com.sparta.developmentgroup1.column.dto.ColumnUpdateRequestDto;
import com.sparta.developmentgroup1.column.entity.ColumnEntity;
import com.sparta.developmentgroup1.column.repository.ColumnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ColumnService {

    private final BoardRepository boardRepository;
    private final ColumnRepository columnRepository;

    public void createColumn(ColumnRequestDto requestDto) {
        Board board = boardRepository.findById(requestDto.getBoardId()).orElseThrow(
                () -> new IllegalArgumentException("요청한 보드가 존재하지 않습니다.")
        );

        ColumnEntity column = new ColumnEntity(requestDto.getName(), board);
        columnRepository.save(column);
    }

    @Transactional
    public void updateColumn(ColumnUpdateRequestDto requestDto, Long id) {
        ColumnEntity column = columnRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("요청한 컬럼이 존재하지 않습니다."));
        column.update(requestDto.getName());
    }

    @Transactional
    public void deleteColumn(Long id) {
        ColumnEntity column = columnRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("요청한 컬럼이 존재하지 않습니다."));
        columnRepository.delete(column);
    }
}