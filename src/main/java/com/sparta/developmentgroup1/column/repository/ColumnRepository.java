package com.sparta.developmentgroup1.column.repository;

import com.sparta.developmentgroup1.column.entity.ColumnEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColumnRepository extends JpaRepository<ColumnEntity, Long> {
}
