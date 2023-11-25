package ne.ordinary.dd.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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

}
