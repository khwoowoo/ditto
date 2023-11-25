package ne.ordinary.dd.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ne.ordinary.dd.domain.Feed;
import ne.ordinary.dd.domain.User;

public class FeedRequest {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ModifyDTO {

        private String uuid;
        private String title;
        private String category;
        private String content;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class UuidDTO {

        private String uuid;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class AddDTO {

        private String uuid;
        private String title;
        private String category;
        private String content;

        public Feed toEntity(User user) {
            return Feed.builder()
                    .user(user)
                    .title(title)
                    .category(category)
                    .content(content)
                    .build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class UpdateDTO {

        private String uuid;
        private String title;
        private String category;
        private String content;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class DeleteDTO {

        private String uuid;
    }
}
