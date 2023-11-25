package ne.ordinary.dd.controller;


import lombok.RequiredArgsConstructor;
import ne.ordinary.dd.core.exception.Exception500;
import ne.ordinary.dd.domain.User;
import ne.ordinary.dd.model.ResponseDTO;
import ne.ordinary.dd.model.UserRequestDto;
import ne.ordinary.dd.model.UserResponseDto;
import ne.ordinary.dd.repository.UserRepository;
import ne.ordinary.dd.service.ChatGptService;
import ne.ordinary.dd.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;
    public final UserRepository userRepository;
    public final ChatGptService chatGptService;
    @PostMapping("/user/register")
    public ResponseDTO<Long> register(@RequestBody UserRequestDto userRequestDto){

        int score = chatGptService.createMBTITest("Q: 나 오늘 택배 뜯다가 칼에 손 베였어\n" +
                "A: 괜찮아? 어떡해? 아팠겠다. 병원은 갔어?\n" +
                "\n" +
                "Q: 너 오늘 힘들어 보이길래 이거 보낸다. 힘내!\n" +
                "A: 고마워 잘 먹을게!(내가 힘들어보였나?)\n" +
                "\n" +
                "Q: 하..나 오늘 헤어졌어..위로 좀 해줘\n" +
                "A: 괜찮아?? 너무 힘들겠다 기다려봐 내가 전화할게\n" +
                "\n" +
                "Q: 나 늦을 것 같아. 퇴근 시간이라서 막히네\n" +
                "A: 음 알겠어 기다리고 있을게!(근데 사과는 없네)");
        System.out.println(score);

        if(userRequestDto.getUuid() == null){
            throw new Exception500("user register error");
        }

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
