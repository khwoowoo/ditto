package ne.ordinary.dd.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class CommentLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //댓글 공감 식별자

    private Long commentId; //댓글 식별자
    private Long type; //공감 유형 1이면 F, 2면 T
    private Long userId; //공감한 유저 식별자

}
