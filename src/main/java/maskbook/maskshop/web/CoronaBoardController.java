package maskbook.maskshop.web;

import lombok.RequiredArgsConstructor;
import maskbook.maskshop.domain.CoronaBoard;
import maskbook.maskshop.service.CoronaBoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CoronaBoardController {

    private final CoronaBoardService coronaBoardService;

    @GetMapping("/coronaboard")
    public String viewBoard(Model model) throws IOException {

        List<CoronaBoard> coronas = coronaBoardService.getCoronaData();
        model.addAttribute("coronas", coronas);

        return "/coronaBoardForm";
    }
    @GetMapping("/")
    public String mainBoard(Model model) throws IOException {

        CoronaBoard corona = coronaBoardService.getCoronaData().get(0);
        model.addAttribute("corona", corona);

        return "/index";
    }

}
