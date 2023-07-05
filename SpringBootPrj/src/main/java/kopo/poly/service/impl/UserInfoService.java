package kopo.poly.service.impl;

import kopo.poly.dto.UserInfoDTO;
import kopo.poly.service.IUserInfoService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import kopo.poly.persistance.mapper.IUserInfoMapper;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserInfoService implements IUserInfoService {
    private final IUserInfoMapper userInfoMapper;
    @Override
    public UserInfoDTO getUserIdExists(UserInfoDTO pDTO) throws Exception {
        return null;
    }

    @Override
    public UserInfoDTO getUserEmailExists(UserInfoDTO pDTO) throws Exception {
        return null;
    }

    // 회원가입
    @Override
    public int insertUserInfo(UserInfoDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".insertUserInfo Start!");

        // 회원가입 성공: 1, 기타 에러 발생 : 0
        int res = 0;

        res = userInfoMapper.insertUserInfo(pDTO);
        log.info(this.getClass().getName() + ".insertUserInfo End!");
        return res;
    }

    // 로그인
    @Override
    public UserInfoDTO getLogin(UserInfoDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".getLogin Start!");

//        UserInfoDTO rDTO = userInfoMapper.getLogin(pDTO);
//        if(rDTO == null){
//            rDTO = new UserInfoDTO();
//        }
        // 위 if문 코드와 동일

        log.info(pDTO.getUser_id());
        log.info(pDTO.getPassword());
        // 로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기 위한 mapper 호출하기
        // userInfoMapper.getUserLoginCheck(pDTO) 함수 실행결과가 Null 이라면, UserInfoDTO 메모리에 올리기

        UserInfoDTO rDTO = Optional.ofNullable(userInfoMapper.getLogin(pDTO)).orElseGet(UserInfoDTO::new);
        /*
        * userInfoMapper로 부터 SELECT 쿼리의 결과로 회원아이디를 받아왔다면, 로그인 성공!
        * 
        * DTO의 변수에 같이 있는지 확인하기 처리속도 측면에서 가장 좋은 방법은 변수의 길이를 가져오는 것.
        * 따라서, .length() 함수를 통해 회원아이디의 글자수를 가져와 0보다 큰지 비교
        * 0보다 크다면, 글자가 존재하는 것이기 때문에 값이 존재
        * */

        if(CmmUtil.nvl(rDTO.getUser_id()).length()> 0){
            log.info("로그인 성공");
        }else{
            log.info("rDTO : "+rDTO);
            log.info(CmmUtil.nvl("user_id : "+rDTO.getUser_id()));
            log.info("로그인 실패");
        }

        log.info(this.getClass().getName() + ".getLogin End!");
        return rDTO;
    }

    @Override
    public UserInfoDTO searchUserInfoPasswordProc(UserInfoDTO pDTO) throws Exception {
        return null;
    }

    @Override
    public int newPasswordProc(UserInfoDTO pDTO) throws Exception {
        return 0;
    }
}
