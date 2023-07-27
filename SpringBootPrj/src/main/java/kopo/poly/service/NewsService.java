package kopo.poly.service;

import kopo.poly.dto.NewsVO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService {
    private static String url = "https://www.ytn.co.kr/issue/corona.php";

    @PostConstruct
    public List<NewsVO> getNewsData() throws IOException{
        List<NewsVO> newsList = new ArrayList<>();
        Document document = Jsoup.connect(url).get();

        Element newsListWrap = document.select(".newslist_wrap").first();
        Elements contents = newsListWrap.select("li.txt");

        for(Element content : contents){
            NewsVO news = NewsVO.builder()
                    .subject(content.select(".til").text())
                    .date(content.select(".date").text())
                    .desc(content.select(".desc").text())
                    .url(content.select("a").attr("abs:href"))
                    .build();
            newsList.add(news);
        }
        return newsList;
    }
}