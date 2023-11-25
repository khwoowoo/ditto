package ne.ordinary.dd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoteResponseDto {
    private String content;
    private String receiverName;
    private String senderName;


}
