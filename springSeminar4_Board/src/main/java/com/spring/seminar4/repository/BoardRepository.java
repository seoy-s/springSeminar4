package com.spring.seminar4.repository;

import com.spring.seminar4.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
}