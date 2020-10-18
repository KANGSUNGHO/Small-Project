package maskbook.maskshop.web;

import lombok.RequiredArgsConstructor;
import maskbook.maskshop.domain.Board;
import maskbook.maskshop.domain.Reply;
import maskbook.maskshop.service.BoardService;
import maskbook.maskshop.service.ReplyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;
    private final BoardService boardService;

    @PostMapping(value="/reply/{boardId}")
    public String writeReply(@PathVariable("boardId") Long boardId,
                             @RequestParam("replyUser") String replyUser,
                             @RequestParam("replyContent") String replyContent,
                             Model model){
        Board replyBoard =  boardService.findBoard(boardId);
        replyService.saveReply(boardId,replyUser,replyContent);
        List<Reply> findReplys = replyService.findReplys(boardId);

        int count = findReplys.size(); // 댓글 개수
        model.addAttribute("findReplys", findReplys);
        model.addAttribute("count", count);

        String[] contents;
        String findContent = replyBoard.getContent();
        contents = findContent.split("\n"); // content 개행
        model.addAttribute("contents", contents);

        model.addAttribute("infoBoard",replyBoard);

        return "/boards/infoForm";
    }

}
