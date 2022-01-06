package com.example.java_project.src.user;

import com.example.java_project.utils.BaseException;
import com.example.java_project.utils.BaseResponse;
import com.example.java_project.jwt.JwtService;
import com.example.java_project.src.user.model.UserDTO;
import com.example.java_project.src.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserProvider userProvider;
    private final JwtService jwtService;

    @Autowired
    public UserController(UserService userService, UserProvider userProvider, JwtService jwtService) {
        this.userService = userService;
        this.userProvider = userProvider;
        this.jwtService = jwtService;
    }

    // 회원 가입
    @ResponseBody
    @PostMapping("/sign-up")
    public BaseResponse<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        try {
            UserEntity user = UserEntity.builder()
                    .userId(userDTO.getUserId())
                    .userPassword(userDTO.getUserPassword())
                    .userName(userDTO.getUserName())
                    .build();

            UserEntity createUser = userService.createUser(user);
            UserDTO postUserRes = UserDTO.builder()
                            .idx(createUser.getIdx())
                            .userId(createUser.getUserId())
                            .userPassword(createUser.getUserPassword())
                            .userName(createUser.getUserName())
                            .build();
            return new BaseResponse<>(postUserRes);
        } catch (BaseException baseException) {
            return new BaseResponse<>(baseException.getStatus());
        }
    }

    // 로그인
    @PostMapping("/sign-in")
    public BaseResponse<UserDTO> signIn(@RequestBody UserDTO userDTO) {
        try {
            UserEntity user = userProvider.getByUserIdAndUserPassword(
                    userDTO.getUserId(),
                    userDTO.getUserPassword());
            // jwt 토큰 생성
            final String token = jwtService.createJWT(user);

            UserDTO loginUserRes = UserDTO.builder()
                    .idx(user.getIdx())
                    .userId(user.getUserId())
                    .userName(user.getUserName())
                    .jwt(token)
                    .build();
            return new BaseResponse<>(loginUserRes);

        } catch (BaseException baseException) {
            return new BaseResponse<>(baseException.getStatus());
        }
    }
}
