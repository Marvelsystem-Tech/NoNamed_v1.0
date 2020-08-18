package jiho.marvel01.controller;

import jiho.marvel01.dto.UserDto;
import jiho.marvel01.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping("/register_user")
    public String register(UserDto userDto) {
        userService.saveUser(userDto);
        return "/page/Sys_setting/user_setting";
    }

    @GetMapping("/user_setting")
    public String list(Model model) {
        List<UserDto> userTotalList = userService.getUserlist();
        model.addAttribute("UserList", userTotalList);
        return "/page/Sys_setting/user_setting";
    }
}
