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

    // 크롤링할 사이트의 URL
    private static String url = "https://www.ytn.co.kr/issue/corona.php";

    // Spring 빈이 초기화된 후 PostConstruct 어노테이션이 붙은 메서드 실행
    @PostConstruct
    public List<NewsVO> getNewsData() throws IOException{
        // 뉴스 데이터를 저장할 리스트 생성
        List<NewsVO> newsList = new ArrayList<>();

        // Jsoup을 사용하여 웹 페이지의 HTML 데이터 가져오기
        Document document = Jsoup.connect(url).get();

        // 뉴스 목록을 감싸는 요소 선택
        Element newsListWrap = document.select(".newslist_wrap").first();

        // 개별 뉴스 아이템 요소들 선택
        Elements contents = newsListWrap.select("li.txt");

        // 개별 뉴스 아이템 정보를 추출하여 NewsVO 객체에 저장하고 리스트에 추가
        for(Element content : contents){
            NewsVO news = NewsVO.builder()
                    .subject(content.select(".til").text())  // 뉴스 제목
                    .date(content.select(".date").text())   // 뉴스 날짜
                    .desc(content.select(".desc").text())   // 뉴스 요약
                    .url(content.select("a").attr("abs:href")) // 뉴스 URL
                    .build();
            newsList.add(news);
        }
        return newsList; // 추출된 뉴스 목록 반환
    }
}
