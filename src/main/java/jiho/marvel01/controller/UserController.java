package jiho.marvel01.controller;

import jiho.marvel01.dto.UserDto;
import jiho.marvel01.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/register_user")
    public String register(UserDto userDto) {
        userService.saveUser(userDto);
        return "redirect:/";
    }

    @GetMapping("/user_setting")
    public String userList(Model model) {
        List<UserDto> userList = userService.getUserlist();
        model.addAttribute("UserList",userList);
        return "/page/Sys_setting/user_setting";
    }

    @DeleteMapping("/user_setting/{uid}")
    public String deleteUser(@PathVariable("uid") Long uid) {
        userService.deleteUser(uid);
        return "/page/Sys_setting/user_setting";
    }

    @RequestMapping("test.do")
    public @ResponseBody Object testDatatable(HttpServletRequest request,
                                              HttpServletResponse response) {
//        List<UserDto> userList = userService.getUserlist();
        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("data", userService.getUserlist());
        Object result = mp;
        return result;
    }

}
