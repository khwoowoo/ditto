package ne.ordinary.dd.repository;

import ne.ordinary.dd.domain.Feed;
import ne.ordinary.dd.domain.FeedLike;
import ne.ordinary.dd.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeedLikeRepository extends JpaRepository<FeedLike, Long> {

    @Query("select fl " +
            "from FeedLike fl " +
            "where fl.feed.id = :feedId and fl.user.id = :userId")
    Optional<FeedLike> findByUserIdAndFeedId(@Param("feedId") Long feedId, @Param("userId") Long userId);

    Long countByFeedId(Long feelId);

    Optional<FeedLike> findByFeedAndUser(Feed feed, User user);
}
