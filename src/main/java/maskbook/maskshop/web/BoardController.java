package maskbook.maskshop.web;

import lombok.RequiredArgsConstructor;
import maskbook.maskshop.domain.Board;
import maskbook.maskshop.domain.Reply;
import maskbook.maskshop.domain.User;
import maskbook.maskshop.repository.BoardSearch;
import maskbook.maskshop.service.BoardService;
import maskbook.maskshop.service.ReplyService;
import maskbook.maskshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final UserService userService;
    private final ReplyService replyService;

    @GetMapping(value="/board")
    public String insertForm(Model model){
        List<User> users = userService.findUsers();
        model.addAttribute("users", users);

        return "boards/board";
    }

    @PostMapping(value="/board")
    public String board(@RequestParam("userId") Long userId,
                        @RequestParam("content") String content,
                        @RequestParam("title") String title){
        boardService.saveBoard(userId,content,title);

        return "redirect:/boards";

    }
    @GetMapping(value="/boards")
    public String boardList(@ModelAttribute("boardSearch") BoardSearch boardSearch, Model model){
        List<Board> boards = boardService.findBoards(boardSearch);
        Collections.reverse(boards); // 리스트 역순 정렬

        System.out.println("boardSearch.getSearchOption() : " + boardSearch.getSearchOption());
        System.out.println("boardSearch.getSearch() : " + boardSearch.getSearch());

        model.addAttribute("boards", boards);

        return "boards/boardList";
    }

    @GetMapping(value="/boards/{id}/read")
    public String infoBoard(@PathVariable("id") Long boardId, Model model){
        Board infoBoard = boardService.findBoard(boardId);
        String[] contents;
        String findContent = infoBoard.getContent();
        contents = findContent.split("\n"); // content 개행

//        infoBoard.setContent(infoBoard.getContent().replaceAll("\\n", "<br/>"));

        List<Reply> findReplys = replyService.findReplys(boardId); // 댓글 목록 가져오기
        int count = findReplys.size(); // 댓글 개수

        model.addAttribute("findReplys", findReplys);
        model.addAttribute("count", count);
        model.addAttribute("contents", contents);
        model.addAttribute("infoBoard",infoBoard);


        return "boards/infoForm";
    }

    @GetMapping(value="/boards/{boardId}/edit")
    public String editBoardForm(@PathVariable("boardId") Long boardId, Model model){
        Board findBoard = boardService.findBoard(boardId);

        Board editBoard = new Board();

        editBoard.setBoardId(findBoard.getBoardId());
        editBoard.changeBoardUser(findBoard.getBoardUser());
        editBoard.setTitle(findBoard.getTitle());
        editBoard.setContent(findBoard.getContent());
        editBoard.setInsertDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy MM dd HH:mm:ss")));
        model.addAttribute("editBoard", editBoard);
        return "boards/updateBoardForm";
    }

    @PostMapping(value="/boards/{boardId}/edit")
    public String editBoard(@ModelAttribute("editBoard") Board editBoard){

        boardService.updateForm(editBoard.getBoardId(),editBoard.getTitle(),editBoard.getContent(), editBoard.getInsertDate());
        return "redirect:/boards";
    }

    @GetMapping(value="/boards/{boardId}/cancel")
    public String cancelBoard(@PathVariable("boardId") Long boardId){

        Board cancelBoard = boardService.findBoard(boardId);
        boardService.deleteBoard(cancelBoard);

        return "redirect:/boards";
    }

}
