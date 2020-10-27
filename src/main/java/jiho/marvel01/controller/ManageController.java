package jiho.marvel01.controller;

import jiho.marvel01.dto.InspectDetailDto;
import jiho.marvel01.dto.InspectResultDto;
import jiho.marvel01.dto.ManageDto;
import jiho.marvel01.service.InspectDetailService;
import jiho.marvel01.service.InspectResultService;
import jiho.marvel01.service.ManageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
public class ManageController {
    private ManageService manageService;
    private InspectResultService inspectResultService;
    private InspectDetailService inspectDetailService;

    @RequestMapping("/m_list")
    public String m_list(Model model) {
        model.addAttribute("title","조치계획 수립");
        model.addAttribute("menu-expended","collapseMeasure");
        return "/page/Measure/m_list";
    }

    @RequestMapping("/m_detail")
    public String m_detail(Model model, InspectResultDto inspectResultDto, InspectDetailDto inspectDetailDto) {
        model.addAttribute("title","조치계획 등록");
        model.addAttribute("menu-expended","collapseMeasure");
        inspectResultService.insertTestInspectResult(inspectResultDto);
        inspectDetailService.insertTestInspect(inspectDetailDto);
        return "/page/Measure/m_detail";
    }

    @RequestMapping("/m_livelist")
    public String m_livelist(Model model) {
        model.addAttribute("title","조치처리");
        model.addAttribute("menu-expended","collapseMeasure");
        return "/page/Measure/m_livelist";
    }

    @RequestMapping("/m_live_detail")
    public String m_live_detail(Model model) {
        model.addAttribute("title","조치처리 상세");
        model.addAttribute("menu-expended","collapseMeasure");
        return "/page/Measure/m_live_detail";
    }

    @RequestMapping("/m_live_status")
    public String m_live_status(Model model) {
        model.addAttribute("title","조치처리 초상세");
        model.addAttribute("menu-expended","collapseMeasure");
        return "/page/Measure/m_live_status";
    }

    @RequestMapping("/m_ticket_list")
    public String m_ticket_list(Model model) {
        model.addAttribute("title","티켓");
        model.addAttribute("menu-expended","collapseMeasure");
        return "/page/Measure/m_ticket_list";
    }

    @RequestMapping("/m_ticket_detail")
    public String m_ticket_detail(Model model) {
        model.addAttribute("title","티켓상세");
        model.addAttribute("menu-expended","collapseMeasure");
        return "/page/Measure/m_ticket_detail";
    }

    @PostMapping(value = "/register_measure")
    public String register(ManageDto manageDto) {
        manageService.saveManage(manageDto);
        return "redirect:/i_dashboard";
    }

    @RequestMapping(value = "/insertManage.do", method = RequestMethod.POST)
    public @ResponseBody
    Object insert(ManageDto manageDto,
                  @RequestParam("iid") Long iid,
                  @RequestParam("rid") Long rid,
                  @RequestParam("type") String type,
                  @RequestParam("date") Timestamp date,
                  @RequestParam("igid") Long igid,
                  @RequestParam("comment") String comment,
                  @RequestParam("status") String status,
                  @RequestParam("aid") Long aid) {
        Map<String, String> list = new HashMap<String, String>();
        list.put("type", type);
        list.put("comment", comment);
        list.put("status", status);
        manageService.insertManage(manageDto, list, iid, rid, igid, date, aid);
        return list;
    }
}
