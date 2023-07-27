package kopo.poly.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class NewsVO {
    private String subject;
    private String date;
    private String desc;
    private String url;
}
