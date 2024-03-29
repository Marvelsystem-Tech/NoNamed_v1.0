package jiho.marvel01.controller;

import jiho.marvel01.dto.UserDto;
import jiho.marvel01.service.UserService;
import jiho.marvel01.util.ExcelRead;
import jiho.marvel01.util.ExcelReadOption;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

@Controller
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping(value = "/register_user.do")
    public String register(UserDto userDto) {
        userService.saveUser(userDto);
        return "redirect:/user_setting";
    }

    @PostMapping(value = "/registerUser.do")
    public String register(UserDto userDto,
                           @RequestParam("email") String email,
                           @RequestParam("name") String name,
                           @RequestParam("pwd") String pwd,
                           @RequestParam("corp") String corp,
                           @RequestParam("dept") String dept,
                           @RequestParam("ranks") String ranks,
                           @RequestParam("codes") String codes) {
        Map<String, String> list = new HashMap<String, String>();
        list.put("email", email);
        list.put("name", name);
        list.put("pwd", pwd);
        list.put("corp", corp);
        list.put("dept", dept);
        list.put("ranks", ranks);
        list.put("codes", codes);
        userService.registerUser(list);
        return "redirect:/register?done=1";

    }

    @RequestMapping(value = "/loginSuccess.do")
    public String loginSuccess(Model model) {

        model.addAttribute("user_name","test");

        return "redirect:/loginDone";
    }

    @RequestMapping(value = "/updateUser.do", method = RequestMethod.POST)
    public @ResponseBody Object update(UserDto userDto,
                                       @RequestParam("uid") Long uid,
                                       @RequestParam("email") String email,
                                       @RequestParam("name") String name,
                                       @RequestParam("corp") String corp,
                                       @RequestParam("dept") String dept,
                                       @RequestParam("ranks") String ranks,
                                       @RequestParam("codes") String codes,
                                       @RequestParam("status") String status,
                                       @RequestParam("i_group") Integer i_group) {
        Map<String, String> list = new HashMap<String, String>();
        list.put("email", email);
        list.put("name", name);
        list.put("corp", corp);
        list.put("dept", dept);
        list.put("ranks", ranks);
        list.put("codes", codes);
        list.put("status", status);
        userService.updateUser(uid, list, i_group);
        return list;
    }

    @GetMapping("/user_setting")
    public String userList(Model model) {
        List<UserDto> userList = userService.getUserlist();
        model.addAttribute("UserList",userList);
        return "page/Sys_setting/user_setting";
    }

    @RequestMapping(value = "/getUserDatatable.do", method = RequestMethod.GET)
    public @ResponseBody Object getDatatable(HttpServletRequest request,
                                              HttpServletResponse response) {
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

    @RequestMapping(value = "/excelUpload.do", method = RequestMethod.POST)
    public @ResponseBody Object excelUploadAjax(MultipartHttpServletRequest request)  throws Exception{
        MultipartFile excelFile =request.getFile("excelFile");
        System.out.println("엑셀 파일 업로드 컨트롤러");
        if(excelFile==null || excelFile.isEmpty()){
            throw new RuntimeException("엑셀파일을 선택 해 주세요.");
        }

        File destFile = new File("D:\\"+excelFile.getOriginalFilename());
        try{
            excelFile.transferTo(destFile);
        }catch(IllegalStateException | IOException e){
            throw new RuntimeException(e.getMessage(),e);
        }

        ExcelReadOption excelReadOption = new ExcelReadOption();
        excelReadOption.setFilePath(destFile.getAbsolutePath());
        excelReadOption.setOutputColumns("B","C","D","E","F","G","H","I");
        excelReadOption.setStartRow(2);

        List<Map<String, String>>excelContent = ExcelRead.read(excelReadOption);
        List<Map<String, String>> totallist = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;

        for(int rpt = 0; rpt < excelContent.size(); rpt++) {
            map = new HashMap<String, String>();
            map.put("email",excelContent.get(rpt).get("B"));
            map.put("name",excelContent.get(rpt).get("C"));
            map.put("corp",excelContent.get(rpt).get("D"));
            map.put("dept",excelContent.get(rpt).get("E"));
            map.put("ranks",excelContent.get(rpt).get("F"));
            map.put("codes",excelContent.get(rpt).get("G"));
            map.put("status",excelContent.get(rpt).get("H"));
            map.put("i_group",excelContent.get(rpt).get("I"));
            totallist.add(map);
        }
        userService.insertMultiUsers(totallist);

        Object result = totallist;
        return result;
    }

}

