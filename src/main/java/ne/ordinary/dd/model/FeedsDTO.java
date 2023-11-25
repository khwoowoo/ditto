package ne.ordinary.dd.model;

import lombok.Data;
import lombok.Getter;
import ne.ordinary.dd.domain.Feed;
import ne.ordinary.dd.repository.FeedsRepository;

import java.time.LocalDateTime;

@Getter
public class FeedsDTO {
    @Data
    public static class Request {
        private Long userId;    // 공감 누른 userId
        private Long feedId;
        private int sympathy1;  // 누르면 1, 안누르면 0
        private int sympathy2;
        private int sympathy3;
        private int sympathy4;
        private int sympathy5;
    }

    @Data
    public static class Response {
        private Long writerId;
        private String title;
        private String content;
        private String category;
        private Long commentCount;
        private Long likeCount;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Response(Feed feed, Long likeCount, Long commentCount) {
            this.writerId = feed.getUser().getId();
            this.title = feed.getTitle();
            this.content = feed.getContent();
            this.category = feed.getCategory();
            this.likeCount = likeCount;
            this.commentCount = commentCount;
            this.createdAt = feed.getCreatedAt();
            this.updatedAt = feed.getUpdatedAt();
        }

        public Response(FeedsRepository.FeedResult feed, Long likeCount, Long commentCount) {
            this.writerId = feed.getUserId();
            this.title = feed.getTitle();
            this.content = feed.getContent();
            this.category = feed.getCategory();
            this.likeCount = likeCount;
            this.commentCount = commentCount;
            this.createdAt = feed.getCreatedAt();
            this.updatedAt = feed.getUpdatedAt();
        }
    }
}
