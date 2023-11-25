package ne.ordinary.dd.model;

import lombok.Getter;
import lombok.Setter;
import ne.ordinary.dd.domain.Comment;
import ne.ordinary.dd.domain.User;

import java.util.List;

public class CommentResponse {

    @Getter
    @Setter
    public static class CommentDTO {

        private Long commentId;
        private String content;
        private Integer fCount;
        private Integer tCount;
        private AuthorDTO authorDTO;
        private Boolean isLiked;
        private List<ReCommentDTO> comments;

        public CommentDTO(Comment comment, Integer fCount, Integer tCount, Boolean isLiked, AuthorDTO authorDTO) {
            this.commentId = comment.getCommentId();
            this.content = comment.getContent();
            this.fCount = fCount;
            this.tCount = tCount;
            this.isLiked = isLiked;
            this.authorDTO = authorDTO;
        }
    }

    @Getter
    @Setter
    public static class ReCommentDTO {

        private Long commentId;
        private String content;
        private Integer fCount;
        private Integer tCount;
        private AuthorDTO authorDTO;
        private Boolean isLiked;

        public ReCommentDTO(Comment comment, Integer fCount, Integer tCount, Boolean isLiked, AuthorDTO authorDTO) {
            this.commentId = comment.getCommentId();
            this.content = comment.getContent();
            this.fCount = fCount;
            this.tCount = tCount;
            this.isLiked = isLiked;
            this.authorDTO = authorDTO;
        }
    }

    @Getter
    @Setter
    public static class AuthorDTO {

        private Long id;
        private String username;
        private String heartLevel;

        public AuthorDTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.heartLevel = user.getHearLevel() + "%";
        }
    }
}
