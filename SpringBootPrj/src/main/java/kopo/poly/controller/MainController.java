package kopo.poly.controller;

import kopo.poly.dto.CovidDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {
    private final CovidRestController covidRestController;

    @GetMapping("/main")
    public String main(ModelMap model) throws Exception{

        List<CovidDTO> covidDTOList = covidRestController.main();
        model.addAttribute(covidDTOList);
        return "/main";
    }

    @GetMapping("/test")
    public String test() throws Exception{
        return "/test";
    }

}
