package maskbook.maskshop.web;

import lombok.RequiredArgsConstructor;
import maskbook.maskshop.domain.User;
import maskbook.maskshop.service.BoardService;
import maskbook.maskshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final UserService userService;

    @GetMapping(value = "/board")
    public String insertForm(Model model){
        List<User> users = userService.findUsers();
        model.addAttribute("users", users);

        return "boards/board";
    }


}
