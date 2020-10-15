package maskbook.maskshop.web;

import lombok.RequiredArgsConstructor;
import maskbook.maskshop.domain.item.Item;
import maskbook.maskshop.domain.item.Mask;
import maskbook.maskshop.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping(value = "/items")
    public String list(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items",items);

        return "items/itemList";
    }

    @GetMapping(value = "/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model){
        Mask item = (Mask) itemService.findOne(itemId);

        MaskForm form = new MaskForm();
        form.setId(item.getItemId());
        form.setName(item.getItemName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setKinds(item.getKinds());
        form.setArea(item.getArea());

        model.addAttribute("form", form);

        return "items/updateItemForm";
    }

    @PostMapping(value = "/items/{itemId}/edit")
    public String updateItem(@ModelAttribute("form") MaskForm form){

//        Mask mask = new Mask();
//        mask.setItemId(form.getId());
//        mask.setItemName(form.getName());
//        mask.setPrice(form.getPrice());
//        mask.setStockQuantity(form.getStockQuantity());
//        mask.setKinds(form.getKinds());
//        mask.setArea(form.getArea());


//        itemService.saveItem(mask);

        itemService.updateItem(form.getId(), form.getName(), form.getPrice(),
                                form.getStockQuantity(), form.getKinds(), form.getArea());

        return "redirect:/items";
    }
}
