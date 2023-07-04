package kopo.poly.controller;

import kopo.poly.dto.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import kopo.poly.service.IUserInfoService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.EncryptUtil;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserInfoController {
    private final IUserInfoService userInfoService;

    // 회원가입 화면으로 이동
    @GetMapping(value = "/user/userRegForm")
    public String userRegForm(){
        log.info(this.getClass().getName() + ".user/userRegForm");
        return "/user/userRegForm";
    }
    
    // 회원가입 로직 처리
    /* ServletRequest -> 클라이언트의 요청 정보를 서블릿으로 넘겨주기 위한 객체이다.
        즉, 요청에 대한 정보를 가진 객체
        ModelMap -> @mvc를 통해 만들어진 결과값을 view페이지에 전달하기 위한 Map
     */
    @PostMapping(value = "/user/insertUserInfo")
    public String insertUserInfo(HttpServletRequest request, ModelMap modelMap) throws Exception{
        log.info(this.getClass().getName() + ".insertUserInfo start!");
        int res;
        String msg = ""; // 회원가입 결과에 대한 메시지를 전달할 변수
        String url = ""; // 회원가입 결과에 대한 URL을 전달할 변수
        
        
        // 웹(회원정보 입력화면)에서 받는 정보를 저장할 변수
        UserInfoDTO pDTO = null;

        try{
            /*
            * #######################################################
            *   웹(회원정보 입력화면)에서 받는 정보를 String 변수에 저장 시작!
            * 
            * 무조건 웹으로 받은 정보는 DTO에 저장하기 임시로 String 변수에 저장
            * #######################################################
            * */

            String user_id = CmmUtil.nvl(request.getParameter("user_id")); // 아이디
            String user_name = CmmUtil.nvl(request.getParameter("user_name")); // 이름
            String password = CmmUtil.nvl(request.getParameter("password")); // 비밀번호
            String email = CmmUtil.nvl(request.getParameter("email")); // 이메일
            String addr1 = CmmUtil.nvl(request.getParameter("addr1")); // 주소
            String addr2 = CmmUtil.nvl(request.getParameter("addr2")); // 상세주소

            /*
            * ######################################################
            * 반드시, 값을 받았으면 꼭 로그를 찍어서 값이 제대로 들어오는지 파악해야함
            *               반드시 작성할 것
            * ######################################################
            * */
            log.info("user_id : " + user_id);
            log.info("user_name : " + user_name);
            log.info("password : " + password);
            log.info("addr1 : " + addr1);
            log.info("addr2 : " + addr2);
            
            /*
            * #######################################################
            * 웹(회원정보 입력화면)에서 받는 정보를 DTO에 저장하기 시작!
            * 
            * 무조건 웹으로 받은 정보는 DTO에 저장해야 한다고 이해
            * #######################################################
            * */
            
            // 웹(회원정보 입력화면)에서 받는 정보를 저장할 변수를 메모리에 올리기
            pDTO = new UserInfoDTO();

            pDTO.setUser_id(user_id);
            pDTO.setUser_name(user_name);

            // 비밀번호는 절대로 복호화되지 않도록 해서 알고리즘으로 암호화함
            pDTO.setPassword(EncryptUtil.encHashSHA256(password));

            // 민감 정보인 이메일은 AES128-CBC로 암호화
            pDTO.setEmail(EncryptUtil.encAES128CBC(email));
            pDTO.setAddr1(addr1);
            pDTO.setAddr2(addr2);

            /*
            * #####################################################
            * 웹(회원정보 입력화면)에서 받는 정보를 DTO에 저장하기 끝
            * 무조건 웹으로 받은 정보는 DTO에 저장해야 한다.
            * #####################################################
            * */

            // 회원가입
            res = userInfoService.insertUserInfo(pDTO);

            log.info("회원가입 결과(res) : " + res);

            if(res == 1){
                msg = "회원가입되었습니다.";

                // 추후 회원가입 입력화면에서 ajax를 활용해서 아이디 중복, 이메일 중복 체크
            }else if(res == 2){
                msg = "이미 가입된 아이디입니다.";
            }else{
                msg = "오류로 인해 회원가입이 실해하였습니다.";
            }
        }catch (Exception e){
            msg = "실패하였습니다. : " + e;
            log.info(e.toString());
            e.printStackTrace();
        }finally {
            modelMap.addAttribute("msg",msg);
            modelMap.addAttribute("url",url);

            log.info(this.getClass().getName() + ".insertUserInfo End!");
        }

        return "/redirect";
    }
}


