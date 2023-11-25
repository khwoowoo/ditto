package ne.ordinary.dd.repository;

import ne.ordinary.dd.domain.Notice;
import ne.ordinary.dd.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findAllByUserOrderByCreatedAtDesc(User user);
}
