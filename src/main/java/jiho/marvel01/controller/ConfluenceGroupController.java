package jiho.marvel01.controller;

import jiho.marvel01.dto.ConfluenceGroupDto;
import jiho.marvel01.service.ConfluenceGroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@AllArgsConstructor
public class ConfluenceGroupController {

    private ConfluenceGroupService confluenceGroupService;

    @RequestMapping("/manage_conf_group")
    public String manage_conf_group(Model model, ConfluenceGroupDto confluenceGroupDto) {
        model.addAttribute("title", "점검항목그룹 관리");
        model.addAttribute("menu-expended","collapseMeasure");
        // 빠른 디버깅을 위한 더미 insert
//        confluenceGroupService.insertTestConfluenceGroup(confluenceGroupDto);
        return "/page/Manage/manage_conf_group";
    }

    @RequestMapping(value = "/insertConfluenceGroup.do", method = RequestMethod.POST)
    public @ResponseBody Object insert(ConfluenceGroupDto confluenceGroupDto,
                                       @RequestParam("cgName") String cgName,
                                       @RequestParam("objects") String objects) {
        Map<String, String> list = new HashMap<String, String>();
        list.put("cgName", cgName);
        list.put("objects", objects);
        confluenceGroupService.insertConfluenceGroup(confluenceGroupDto, list);
        return list;
    }

    @RequestMapping("/getConfluenceGroupDatatable.do")
    public @ResponseBody Object getDatatable(HttpServletRequest request,
                                              HttpServletResponse response) {
        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("data", confluenceGroupService.getConfluenceGrouplist());
        Object result = mp;
        return result;
    }
//
//    @RequestMapping(value = "/updateConfluence.do", method = RequestMethod.POST)
//    public @ResponseBody Object update(ConfluenceGroupDto confluenceGroupDto,
//                                       @RequestParam("iid") Long iid,
//                                       @RequestParam("cgid") Integer cgid,
//                                       @RequestParam("iName") String iName,
//                                       @RequestParam("type") String type,
//                                       @RequestParam("objects") String objects,
//                                       @RequestParam("idate") Date idate,
//                                       @RequestParam("idateEx") Date idateEx,
//                                       @RequestParam("content") String content,
//                                       @RequestParam("mdate") Date mdate,
//                                       @RequestParam("mdateEx") Date mdateEx) {
//        Map<String, String> list = new HashMap<String, String>();
//        list.put("iName", iName);
//        list.put("type", type);
//        list.put("objects", objects);
//        list.put("content", content);
//        list.put("status", "점검대기");
//        confluenceGroupService.updateConfluence(iid, list, cgid, idate, idateEx, mdate, mdateEx);
//        return list;
//    }
//
//    @RequestMapping(value = "/deleteconfluence.do", method = RequestMethod.POST)
//    public String deleteconfluence(@RequestParam("iid") Long iid) {
//        confluenceGroupService.deleteConfluence(iid);
//        return "redirect:/confluence_setting";
//    }
//
//    @RequestMapping(value = "/deleteConfluences.do", method = RequestMethod.POST)
//    public String deleteConfluences(@RequestParam(value = "iid[]") List<Long> iid) {
//         confluenceGroupService.deleteConfluences(iid);
//         return "redirect:/confluence_setting";
//    }
//
    @RequestMapping(value = "/getConfluenceGroup.do", method = RequestMethod.POST)
    public @ResponseBody Object getConfluence(@RequestParam("cgid") Long cgid) {
        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("data", confluenceGroupService.getConfluenceGroup(cgid));
        Object result = mp;
        return mp;
    }

}

