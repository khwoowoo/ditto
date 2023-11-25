package ne.ordinary.dd.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class CommentLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //댓글 공감 식별자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commentId")
    private Comment comment; //댓글 식별자

    @Column(nullable = false)
    private Long type; //공감 유형 1이면 F, 2면 T

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user; //공감한 유저 식별자

    @Builder
    public CommentLike(Comment comment, Long type, User user){
        this.comment = comment;
        this.type = type;
        this.user = user;
    }

}
