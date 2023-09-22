package com.spring.seminar4.controller;

import com.spring.seminar4.entity.Board;
import com.spring.seminar4.entity.PageResponse;
import com.spring.seminar4.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    /**
     * 메인화면 get
     * @param
     * @return main
     */
//    @GetMapping("/main")
//    public String boardMain(Model model,
//                            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
//
//        Page<Board> list = boardService.boardList(pageable);
//
//        int nowPage = list.getPageable().getPageNumber() + 1;   // 현재 페이지 반환, 0으로 시작해서 +1 함.
//        int startPage = Math.max(nowPage - 2, 1);   // nowPage - 4를 하면 nowPage가 1인 경우 -3도 가능하므로 이를 방지하기 위해 Math.max(a, b)로 a와 b 중 큰 값 반환
//        int endPage = Math.min(nowPage +1, list.getTotalPages());  // totalPage보다 클 수 없으므로 둘 중 최솟값을 반환하는 Math.min() 사용
//        int totalPage = list.getTotalPages();
//
//        System.out.println("### nowPage : " + nowPage);
//        System.out.println("### startPage : " + startPage);
//        System.out.println("### endPage : " + endPage);
//        System.out.println("### totalPage : " + totalPage);
//
//        // **확인 필요** thymeleaf를 사용하므로 model로 넘김.
//        model.addAttribute("list", list); // boardService에서 생성한 boardList메소드를 통해 list가 반환되는데, 해당 list를 "list"라는 이름으로 넘김.
//        model.addAttribute("nowPage", nowPage);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//        model.addAttribute("totalPage", totalPage);
//
//        return "main";
//    }

    @GetMapping
    public PageResponse readAllPaging(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy
    ) {
        return boardService.searchAllPaging(pageNo, pageSize, sortBy);
    }

    /**
     * 글 작성화면 get
     * @param
     * @return form
     */
    @GetMapping("/form") // localhost:8080/board/write
    public String boardForm() {
        return "form";
    }

    /**
     * 글 작성 post
     * @param board
     * @return
     */
    @PostMapping("/write")
    public String boardWrite(@ModelAttribute("board") Board board) {
        System.out.println("### 제목 : " + board.getTitle());
        boardService.write(board);

        return "detail";
    }

    /**
     * 글 조회 get
     * @param
     * @return detail
     */
    @GetMapping("/detail")
    public String boardDetail(Model model, Integer id) {
        model.addAttribute("board", boardService.boardDetail(id));
        System.out.println("### datail : " + model.getAttribute("board"));

        return "detail";
    }


}
