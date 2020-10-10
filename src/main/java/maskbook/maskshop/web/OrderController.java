package maskbook.maskshop.web;

import lombok.RequiredArgsConstructor;
import maskbook.maskshop.domain.Order;
import maskbook.maskshop.domain.User;
import maskbook.maskshop.domain.item.Item;
import maskbook.maskshop.repository.OrderSearch;
import maskbook.maskshop.service.ItemService;
import maskbook.maskshop.service.OrderService;
import maskbook.maskshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final ItemService itemService;

    @GetMapping(value = "/order")
    public String createForm(Model model){

        List<User> users = userService.findUsers();
        List<Item> items = itemService.findItems();

        model.addAttribute("users", users);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping(value = "/order")
    public String order(@RequestParam("userId") Long userId, @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count){

        orderService.order(userId, itemId, count);

        return "redirect:/orders";
    }

    @GetMapping(value = "/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model){

        List<Order> orders = orderService.findOrders(orderSearch);

        model.addAttribute("orders", orders);

        return "order/orderList";
    }

    @PostMapping(value = "/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId){

        orderService.cancelOrder(orderId);

        return "redirect:/orders";
    }


}
