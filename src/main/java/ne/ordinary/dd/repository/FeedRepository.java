package ne.ordinary.dd.repository;

import ne.ordinary.dd.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, Long> {
}
