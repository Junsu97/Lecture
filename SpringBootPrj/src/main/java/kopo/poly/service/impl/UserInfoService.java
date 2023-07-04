package kopo.poly.service.impl;

import kopo.poly.dto.UserInfoDTO;
import kopo.poly.service.IUserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import kopo.poly.persistance.mapper.IUserInfoMapper;

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

    @Override
    public int insertUserInfo(UserInfoDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".insertUserInfo Start!");

        // 회원가입 성공: 1, 기타 에러 발생 : 0
        int res = 0;

        res = userInfoMapper.insertUserInfo(pDTO);
        log.info(this.getClass().getName() + ".insertUserInfo End!");
        return res;
    }

    @Override
    public UserInfoDTO getLogin(UserInfoDTO pDTO) throws Exception {
        return null;
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