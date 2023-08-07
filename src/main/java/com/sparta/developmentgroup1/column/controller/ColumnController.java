package com.sparta.developmentgroup1.column.controller;

import com.sparta.developmentgroup1.column.dto.ColumnRequestDto;
import com.sparta.developmentgroup1.column.dto.ColumnUpdateRequestDto;
import com.sparta.developmentgroup1.column.service.ColumnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ColumnController {

    private final ColumnService columnService;

    @PostMapping("/columns")
    public ResponseEntity<String> createColumn(@RequestBody ColumnRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        columnService.createColumn(requestDto, userDetails);
        return ResponseEntity.ok("컬럼 생성 완료");
    }

    @PutMapping("/columns/{id}")
    public ResponseEntity<String> updateColumn(@RequestBody ColumnUpdateRequestDto requestDto, @PathVariable Long id,
                                               @AuthenticationPrincipal UserDetailsImpl userDetails) {
        columnService.updateColumn(requestDto, id, userDetails);
        return ResponseEntity.ok("컬럼명 수정 완료");
    }

    @DeleteMapping("/columns/{id}")
    public ResponseEntity<String> deleteColumn(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        columnService.deleteColumn(id, userDetails);
        return ResponseEntity.ok("컬럼 삭제 완료");
    }
}
