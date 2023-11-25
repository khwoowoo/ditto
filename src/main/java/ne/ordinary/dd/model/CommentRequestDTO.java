package ne.ordinary.dd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ne.ordinary.dd.domain.Comment;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDTO {
    private String content;

    public Comment toEntity() {return Comment.builder().content(content).build();}
}
