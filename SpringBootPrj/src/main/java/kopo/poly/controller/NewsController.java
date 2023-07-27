package kopo.poly.controller;

import kopo.poly.dto.NewsVO;
import kopo.poly.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @GetMapping("/news")
    public String news(Model model) throws Exception{
        List<NewsVO> newsList = newsService.getNewsData();
        model.addAttribute("news", newsList);

        return "news";
    }
}
