package ne.ordinary.dd.model;

import lombok.Builder;
import lombok.Data;
import ne.ordinary.dd.domain.Notice;

import java.time.LocalDateTime;

@Data
public class NoticeDTO {
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public NoticeDTO(Notice notice) {
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.createdAt = notice.getCreatedAt();
        this.updatedAt = notice.getLastModifiedAt();
    }
}
