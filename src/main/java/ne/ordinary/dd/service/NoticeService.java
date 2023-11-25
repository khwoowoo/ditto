package ne.ordinary.dd.service;

import lombok.RequiredArgsConstructor;
import ne.ordinary.dd.domain.User;
import ne.ordinary.dd.model.NoticeDTO;
import ne.ordinary.dd.repository.NoticeRepository;
import ne.ordinary.dd.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;

    public List<NoticeDTO> getNotices(Long userId) {
        Optional<User> getUser = userRepository.findById(userId);
        if(!getUser.isPresent())
            throw new IllegalArgumentException("해당하는 user를 찾을 수 없습니다.");

        User user = getUser.get();
        return noticeRepository.findAllByUserOrderByCreatedAtDesc(user)
                .stream()
                .map(n -> new NoticeDTO(n))
                .collect(Collectors.toList());
    }
}
