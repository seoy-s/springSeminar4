package com.spring.seminar4.controller;

import com.spring.seminar4.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class RestBoardController {

    @Autowired
    BoardRepository boardRepository;

    @RequestMapping(value = "/main")
    public String restBoard() {
        return "THIS IS RESTful";
    }
}
