package maskbook.maskshop.web;

import lombok.RequiredArgsConstructor;
import maskbook.maskshop.domain.Address;
import maskbook.maskshop.domain.User;
import maskbook.maskshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping(value = "/users/new")
    public String createForm(Model model){
        System.out.println("createForm 접속");
        model.addAttribute("userForm", new UserForm());
        return "users/createUserForm";
    }

    @PostMapping(value = "/users/new")
    public String create(@Valid UserForm form, BindingResult result){

        if(result.hasErrors()){
            return "users/createUserForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        User user = new User();
        user.setUserName(form.getName());
        user.setAddress(address);

        userService.join(user);

        return "redirect:/";
    }

    @GetMapping(value = "/users")
    public String list(Model model){
        List<User> users = userService.findUsers();
        model.addAttribute("users", users);
        return "users/userList";
    }
}
