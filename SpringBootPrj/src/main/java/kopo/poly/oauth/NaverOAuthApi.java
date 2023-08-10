package kopo.poly.oauth;

import com.github.scribejava.core.builder.api.DefaultApi20;

public class NaverOAuthApi extends DefaultApi20 {

    // 기본 생성자 (private으로 선언하여 외부에서 인스턴스 생성 불가)
    protected NaverOAuthApi(){
    }

    // NaverOAuthApi 클래스의 인스턴스를 저장하는 내부 클래스
    private static class InstanceHolder{
        private static final NaverOAuthApi INSTANCE = new NaverOAuthApi();
    }

    // NaverOAuthApi 클래스의 인스턴스를 반환하는 정적 메서드
    public static NaverOAuthApi instance(){
        return InstanceHolder.INSTANCE;
    }

    // 엑세스 토큰 요청을 위한 엔드포인트 반환
    @Override
    public String getAccessTokenEndpoint() {
        return "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code";
    }

    // 사용자 인증을 위한 베이스 URL 반환
    @Override
    protected String getAuthorizationBaseUrl() {
        return "https://nid.naver.com/oauth2.0/authorize";
    }
}
