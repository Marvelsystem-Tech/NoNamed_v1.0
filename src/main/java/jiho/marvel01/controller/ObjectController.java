package jiho.marvel01.controller;

import jiho.marvel01.dto.ObjectDto;
import jiho.marvel01.service.ObjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@AllArgsConstructor
public class ObjectController {

    private ObjectService objectService;

    @PostMapping(value = "/register_object")
    public String register(ObjectDto objectDto) {
        objectService.saveObject(objectDto);
        return "redirect:/manage_obj";
    }

    @RequestMapping(value = "/updateObject.do", method = RequestMethod.POST)
    public @ResponseBody Object update(ObjectDto objectDto,
                                       @RequestParam("oid") Long oid,
                                       @RequestParam("type") String type,
                                       @RequestParam("oName") String oName,
                                       @RequestParam("purpose") String purpose,
                                       @RequestParam("oGroup") String oGroup,
                                       @RequestParam("part1") String part1,
                                       @RequestParam("part2") String part2,
                                       @RequestParam("IP") String IP,
                                       @RequestParam("location") String location) {
        Map<String, String> list = new HashMap<String, String>();
        list.put("type", type);
        list.put("oName", oName);
        list.put("purpose", purpose);
        list.put("oGroup", oGroup);
        list.put("part1", part1);
        list.put("part2", part2);
        list.put("IP", IP);
        list.put("location", location);
        objectService.updateObject(oid, list);
        return list;
    }


    @GetMapping("/object_setting")
    public String objectList(Model model) {
        List<ObjectDto> objectList = objectService.getObjectlist();
        model.addAttribute("ObjectList",objectList);
        return "/page/Sys_setting/object_setting";
    }

    @RequestMapping("/getObjectDatatable.do")
    public @ResponseBody Object getDatatable(HttpServletRequest request,
                                              HttpServletResponse response) {
        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("data", objectService.getObjectlist());
        Object result = mp;
        return result;
    }

    @RequestMapping(value = "/deleteobject.do", method = RequestMethod.POST)
    public String deleteobject(@RequestParam("oid") Long oid) {
        objectService.deleteObject(oid);
        return "redirect:/object_setting";
    }

    @RequestMapping(value = "/deleteObjects.do", method = RequestMethod.POST)
    public String deleteObjects(@RequestParam(value = "oid[]") List<Long> oid) {
         objectService.deleteObjects(oid);
         return "redirect:/object_setting";
    }

    @RequestMapping(value = "/getObject.do", method = RequestMethod.POST)
    public @ResponseBody Object getObject(@RequestParam("oid") Long oid) {
        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("data", objectService.getObject(oid));
        Object result = mp;
        return mp;
    }

}
