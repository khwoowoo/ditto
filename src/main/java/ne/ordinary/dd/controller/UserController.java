package ne.ordinary.dd.controller;


import lombok.RequiredArgsConstructor;
import ne.ordinary.dd.domain.User;
import ne.ordinary.dd.model.ResponseDTO;
import ne.ordinary.dd.model.UserRequestDto;
import ne.ordinary.dd.model.UserResponseDto;
import ne.ordinary.dd.repository.UserRepository;
import ne.ordinary.dd.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;
    public final UserRepository userRepository;
    @PostMapping("/user/register")
    public ResponseDTO<Long> register(@RequestBody UserRequestDto userRequestDto){
        User user = User.createUser(userRequestDto);
        Long userId = userService.register(user);
        return new ResponseDTO<Long>(userId);
    }

    @PostMapping("/user/login")
    public ResponseDTO<UserResponseDto> login(@RequestBody UserRequestDto userRequestDto){
        UserResponseDto login = userService.login(userRequestDto.getUuid());
        return new ResponseDTO<UserResponseDto>(login);
    }

    @PatchMapping("/user/profile/{userId}")
    public ResponseDTO<Long> rename(@PathVariable Long userId, @RequestBody UserRequestDto userRequestDto){
        userService.rename(userId, userRequestDto.getUsername());
        return new ResponseDTO<Long>(userId);
    }

}
