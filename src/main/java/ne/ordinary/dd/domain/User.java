package ne.ordinary.dd.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class User extends BaseTime{
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;    // 사용자 아이디

    private String uuid;    //사용자 uuid
    private String username;    //사용자 이름
    private boolean isCheck;    // 테스트 여부
    private int hearLevel;      // 공감 지수


    //====연관 관계 주인=====
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Note> notes = new ArrayList<>();
}
