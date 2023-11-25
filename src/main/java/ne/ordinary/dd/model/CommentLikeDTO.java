package ne.ordinary.dd.model;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class CommentLikeDTO {
    private Long userId;
    private Long type;
}
