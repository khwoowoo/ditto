package ne.ordinary.dd.model;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    String uuid;
    boolean isCheck;
    String username;
    String content;
}
