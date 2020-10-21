package maskbook.maskshop.web;

import lombok.RequiredArgsConstructor;
import maskbook.maskshop.domain.BookMark;
import maskbook.maskshop.domain.BookMarkMeta;
import maskbook.maskshop.service.BookMarkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookMarkController {

    private final BookMarkService bookMarkService;


    @PostMapping("/bookmark/bookmarklist")
    public String saveUrlData(@RequestParam("postUrl") String postUrl,
                               @RequestParam("postComment") String postComment) throws IOException {
        System.out.println("postUrl : " + postUrl);
        System.out.println("postComment : " + postComment);

        BookMark bookMark = new BookMark();
        bookMark.setPostUrl(postUrl);
        bookMark.setPostComment(postComment);
        bookMarkService.saveUrlData(bookMark);

        return "redirect:/bookmark";
    }

    @GetMapping("/bookmark")
    public String findUrldata(Model model){
        List<BookMarkMeta> bmMeta = new ArrayList<>();
        bmMeta = bookMarkService.findUrlData();

        System.out.println("bmMeta : " + bmMeta);
        model.addAttribute("bmMeta", bmMeta);
        return "/bookmark";

    }

    @GetMapping("/bookmark/{bmtId}/cancel")
    public String bookMarkCancel(@PathVariable("bmtId") Long bmtId){
        BookMarkMeta bmt = bookMarkService.findUrl(bmtId);
        System.out.println("bmt : " + bmt.getBmtId());
        bookMarkService.cancelUrl(bmt);
        System.out.println("삭제 요청됨");

        return "redirect:/bookmark";
    }
}
