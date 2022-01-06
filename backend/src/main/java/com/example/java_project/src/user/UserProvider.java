package com.example.java_project.src.user;

import com.example.java_project.secret.Secret;
import com.example.java_project.utils.AES128;
import com.example.java_project.utils.BaseException;
import com.example.java_project.src.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.java_project.utils.BaseResponseStatus.*;

@Service
public class UserProvider {

    UserDao userDao;

    @Autowired
    public UserProvider(UserDao userDao) {
        this.userDao = userDao;
    }

    // 로그인
    // 최소한의 권한 final, private 많이 쓰자
    public UserEntity getByUserIdAndUserPassword(final String userId, final String userPassword) throws BaseException {

        UserEntity postLoginUserReq;
        String encryptedPassword;

        // 비밀번호 암호화
        try{
            encryptedPassword = new AES128(Secret.USER_INFO_PASSWORD_KEY).encrypt(userPassword);
        } catch (Exception exception) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }

       //  데이터 베이스에 암호화하여 저장했기 때문에 암호화된 비밀번호와 비교
       try {
            postLoginUserReq = userDao.findByUserIdAndUserPassword(userId, encryptedPassword);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }

        // 아이디 또는 비밀번호가 잘못 되었을 경우
        if (postLoginUserReq == null) {
            throw new BaseException(USER_LOGIN_FAIL);
        }

        return postLoginUserReq;
    }

}
