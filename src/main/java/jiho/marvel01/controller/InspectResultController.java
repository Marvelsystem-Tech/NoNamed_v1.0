package jiho.marvel01.controller;

import jiho.marvel01.service.InspectResultService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class InspectResultController {

    private InspectResultService inspectResultService;


}
