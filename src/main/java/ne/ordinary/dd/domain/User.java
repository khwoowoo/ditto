package ne.ordinary.dd.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import ne.ordinary.dd.model.UserRequestDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseTime{
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;    // 사용자 아이디

    private String uuid;    //사용자 uuid
    private String username;    //사용자 이름
    private boolean isCheck;    // 테스트 여부
    private int hearLevel;      // 공감 지수

    public void rename(String newUsername) {
        this.username = newUsername;
    }
    public void editHearLevel(int edit) {this.hearLevel = hearLevel;}

    //=======생성 메서드=======

    public static User createUser(UserRequestDto userRequestDto){
        return User.builder()
                .uuid(userRequestDto.getUuid())
                .username(userRequestDto.getUuid())
                .isCheck(userRequestDto.isCheck())
                .hearLevel(userRequestDto.getHearLevel())
                .build();
    }

    //====연관 관계 주인=====

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Note> notes = new ArrayList<>();
}
