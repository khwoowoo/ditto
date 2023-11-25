package ne.ordinary.dd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoteRequestDto {
    Long senderId;
    Long receiverId;
    String content;
}
