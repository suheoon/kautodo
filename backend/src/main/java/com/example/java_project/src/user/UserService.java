package com.example.java_project.src.user;

import com.example.java_project.secret.Secret;
import com.example.java_project.utils.AES128;
import com.example.java_project.utils.BaseException;
import com.example.java_project.src.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.java_project.utils.BaseResponseStatus.*;

@Service
public class UserService {

    private UserDao userDao;
    private UserProvider userProvider;

    @Autowired
    public UserService(UserDao userDao, UserProvider userProvider) {
        this.userDao = userDao;
        this.userProvider = userProvider;
    }

    // 회원가입
    public UserEntity createUser(final UserEntity userEntity) throws BaseException {
        String userId = userEntity.getUserId();

        // 아이디가 중복 될 경우
        if (userDao.existsByUserId(userId)) {
            throw new BaseException(USER_EXIST_ID);
        }
        String encryptedPassword;
        // 비밀번호 암호화
        try {
            encryptedPassword = new AES128(Secret.USER_INFO_PASSWORD_KEY).encrypt(userEntity.getUserPassword());
            userEntity.setUserPassword(encryptedPassword);
        } catch (Exception exception) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }

        try {
            return userDao.save(userEntity);
        } catch (Exception exception) {
            throw  new BaseException(DATABASE_ERROR);
        }
    }
}
