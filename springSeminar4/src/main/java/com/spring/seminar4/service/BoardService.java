package com.spring.seminar4.service;

import com.spring.seminar4.entity.Board;
import com.spring.seminar4.entity.PageResponse;
import com.spring.seminar4.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {

    // 객체 생성 - 스프링 부트에서 제공하는 Autowired를 사용하면 스프링이 알아서 읽어와서 자동으로 주입
    @Autowired
    BoardRepository boardRepository;

    /**
     * 글 목록
     * @return
     */
//    public Page<Board> boardList(Pageable pageable) {
//        return boardRepository.findAll(pageable); // Board라는 클래스가 담긴 list를 찾아 반환
//    }

    private Board mapToDto(Board board) {

        return Board.builder()
                .id(board.getId())
                .title(board.getTitle())
                .writer(board.getWriter())
                .content(board.getContent())
                .build();
    }


    public PageResponse searchAllPaging(int pageNo, int pageSize, String sortBy) {

        // Pageable 객체 생성
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<Board> boardPage = boardRepository.findAll(pageable);

        // .map()을 더 추가해서 바로 Page<PageResponse> 값으로 시작할 수 있어!
        // Page<TodoResponse> todoDtoPage = todoRepository.findAll(pageable).map(this::mapToDto);

        List<Board> listTodos = Board.getContent();

        List<PageResponse> content = listTodos.stream().map(Board -> mapToDto(Board)).collect(Collectors.toList());

        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalElements(todoPage.getTotalElements())
                .totalPages(todoPage.getTotalPages())
                .last(todoPage.isLast())
                .build();

    }

    /**
     * 글 작성
     * @param board
     */
    public void write(Board board) {
        boardRepository.save(board);    // 이렇게 생성한 서비스는 다시 컨트롤러에서 사용
    }

    /**
     * 글 조회
     * @return
     */
    public Board boardDetail(Integer id) {
        return boardRepository.findById(id).get();  // Integer타입의 변수를 통해 불러오므로 위의 인자로 Integer타입의 id를 줌.
    }

}
