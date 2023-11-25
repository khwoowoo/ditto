package ne.ordinary.dd.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ne.ordinary.dd.domain.Feed;
import ne.ordinary.dd.domain.User;

import java.util.List;

public class FeedResponse {

    @Getter
    @Setter
    public static class FeedDTO {

        private Long id;
        private String category;
        private String title;
        private Integer sympathy1;
        private Integer sympathy2;
        private Integer sympathy3;
        private Integer sympathy4;
        private Integer sympathy5;
        private Integer sympathyCount;
        private Boolean isLiked;
        private AuthorDTO authorDTO;
        private Integer commentCount;
        private List<CommentResponse.CommentDTO> comments;

        public FeedDTO(Feed feed, AuthorDTO authorDTO, Boolean isLiked, Integer commentCount, List<CommentResponse.CommentDTO> comments) {
            this.id = feed.getId();
            this.category = feed.getCategory();
            this.title = feed.getTitle();
            this.isLiked = isLiked;
            this.authorDTO = authorDTO;
            this.commentCount = commentCount;
            this.comments = comments;
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
