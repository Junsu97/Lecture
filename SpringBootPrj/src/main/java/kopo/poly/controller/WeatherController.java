package kopo.poly.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@RequiredArgsConstructor
@Controller
public class WeatherController {

    @RequestMapping(value = "weather", method = { RequestMethod.GET, RequestMethod.POST })
    public String weahter(){
        log.info(this.getClass().getName() + ".weahter Start!");



        log.info(this.getClass().getName() + ".weahter End!");
        return "weather";
    }
}
