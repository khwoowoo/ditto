package ne.ordinary.dd.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Note extends BaseTime{
    @Id @GeneratedValue
    @Column(name = "note_id")
    private Long id;    //쪽지 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Long senderId;  //보낸 사람 아이디
    private Long receiverId;    //받는 사람 아이디
    private String content;     //내용

    public Note(Long senderId, Long receiverId, String content) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
    }

    //=======연관 관계 편의 메서드=======
    public void setUser(User user){
        this.user = user;
        user.getNotes().add(this);
    }

    //=======생성 메서드=======
    public static Note createNote(User user, String content, Long senderId, Long receiverId){
        Note note = new Note(senderId, receiverId, content);
        note.setUser(user);
        return note;
    }

}
