package ne.ordinary.dd.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comment extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id")
    private Feed feed; //게시글 식별자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User writer; //작성 유저 식별자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent; //부모 댓글 식별자

    @Column(nullable = false, length = 255)
    private String content; //댓글 내용


    private boolean isRemoved = false;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<Comment> childList = new ArrayList<>();



    public void confirmParent(Comment parent){
        this.parent = parent;
        parent.addChild(this);
    }

    public void addChild(Comment child){
        childList.add(child);
    }

    //== 삭제 ==//
    public void remove() {
        this.isRemoved = true;
    }
    @Builder
    public Comment(String content){this.content = content;}

    //== 비즈니스 로직 ==//
    public List<Comment> findRemovableList() {

        List<Comment> result = new ArrayList<>();

        Optional.ofNullable(this.parent).ifPresentOrElse(

                parentComment ->{//대댓글인 경우 (부모가 존재하는 경우)
                    if( parentComment.isRemoved()&& parentComment.isAllChildRemoved()){
                        result.addAll(parentComment.getChildList());
                        result.add(parentComment);
                    }
                },

                () -> {//댓글인 경우
                    if (isAllChildRemoved()) {
                        result.add(this);
                        result.addAll(this.getChildList());
                    }
                }
        );

        return result;
    }


    //모든 자식 댓글이 삭제되었는지 판단
    private boolean isAllChildRemoved() {
        return getChildList().stream()
                .map(Comment::isRemoved)//지워졌는지 여부로 바꾼다
                .filter(isRemove -> !isRemove)//지워졌으면 true, 안지워졌으면 false이다. 따라서 filter에 걸러지는 것은 false인 녀석들이고, 있다면 false를 없다면 orElse를 통해 true를 반환한다.
                .findAny()//지워지지 않은게 하나라도 있다면 false를 반환
                .orElse(true);//모두 지워졌다면 true를 반환

    }
}
