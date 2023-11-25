package ne.ordinary.dd.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private Long postId; //게시글 식별자
    private Long writerId; //유저 식별자
    private Long parentId; //부모 댓글 식별자
    private String content; //댓글 내용
    private Date createdAt; //생성일
    private Date updatedAt; //수정일

    @Builder
    public Comment(String content){this.content = content;}
}
