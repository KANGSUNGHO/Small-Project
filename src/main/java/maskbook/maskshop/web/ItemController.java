package maskbook.maskshop.web;

import lombok.RequiredArgsConstructor;
import maskbook.maskshop.domain.item.Mask;
import maskbook.maskshop.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping(value = "/items/new")
    public String createForm(Model model){

        model.addAttribute("form", new MaskForm());

        return "items/createItemForm";
    }

    @PostMapping(value = "/items/new")
    public String create(MaskForm form){

        Mask mask = new Mask();

        mask.setItemName(form.getName());
        mask.setPrice(form.getPrice());
        mask.setStockQuantity(form.getStockQuantity());
        mask.setKinds(form.getKinds());
        mask.setArea(form.getArea());

        itemService.saveItem(mask);

        return "redirect:/items";
    }

}
