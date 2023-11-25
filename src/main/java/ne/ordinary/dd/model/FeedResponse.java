package ne.ordinary.dd.model;

import lombok.Getter;
import lombok.Setter;
import ne.ordinary.dd.domain.Comment;

import java.util.List;

public class FeedResponse {

    @Getter
    @Setter
    public static class FeedDTO {

        private Long id;
        private String title;
        private Integer sympathy1;
        private Integer sympathy2;
        private Integer sympathy3;
        private Integer sympathy4;
        private Integer sympathy5;
        private Integer sympathyCount;
        private Integer commentCount;
        private AuthorDTO authorDTO;
        private List<Comment> replies;

        public FeedDTO(Long id, String title, Integer sympathy1, Integer sympathy2, Integer sympathy3, Integer sympathy4, Integer sympathy5, Integer sympathyCount, Integer commentCount, AuthorDTO authorDTO, List<Comment> replies) {
            this.id = id;
            this.title = title;
            this.sympathy1 = sympathy1;
            this.sympathy2 = sympathy2;
            this.sympathy3 = sympathy3;
            this.sympathy4 = sympathy4;
            this.sympathy5 = sympathy5;
            this.sympathyCount = sympathyCount;
            this.commentCount = commentCount;
            this.authorDTO = authorDTO;
            this.replies = replies;
        }
    }

    @Getter
    @Setter
    public static class AuthorDTO {

        private Long id;
        private String username;
        private String heartLevel;

        public AuthorDTO(Long id, String username, String heartLevel) {
            this.id = id;
            this.username = username;
            this.heartLevel = heartLevel;
        }
    }

}
