package jiho.marvel01.controller;

import jiho.marvel01.domain.Entity.UserEntity;
import jiho.marvel01.dto.UserDto;
import jiho.marvel01.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static org.springframework.util.ObjectUtils.isEmpty;


@Controller
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @RequestMapping(value = "/registerUser.do", method = RequestMethod.POST)
    public String register(UserDto userDto,
                           @RequestParam("uid") Long uid,
                           @RequestParam("contents[]") String[] contents) {
        UserDto userinfo = new UserDto();
        userinfo.setUid(uid);
        userinfo.setName("test");
        userinfo.setEmail("test");
        userinfo.setCorp("test");
        userinfo.setDepartment("test");
        userinfo.setI_group(2);
        userService.saveUser(userinfo);
        return "redirect:/user_setting";
    }


    @GetMapping("/user_setting")
    public String userList(Model model) {
        List<UserDto> userList = userService.getUserlist();
        model.addAttribute("UserList",userList);
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

    @RequestMapping(value = "/deleteuser.do", method = RequestMethod.POST)
    public String deleteuser(@RequestParam("uid") Long uid) {
        userService.deleteUser(uid);
        return "redirect:/user_setting";
    }

    @RequestMapping(value = "/deleteUsers.do", method = RequestMethod.POST)
    public String deleteUsers(@RequestParam(value = "uid[]") List<Long> uid) {
         userService.deleteUsers(uid);
         return "redirect:/user_setting";
    }

    @RequestMapping(value = "/getUser.do", method = RequestMethod.POST)
    public @ResponseBody Object getUser(@RequestParam("uid") Long uid) {
        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("data", userService.getUser(uid));
        Object result = mp;
        return mp;
    }

}

