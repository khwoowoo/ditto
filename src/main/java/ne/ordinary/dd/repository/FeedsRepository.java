package ne.ordinary.dd.repository;

import ne.ordinary.dd.domain.Feed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface FeedsRepository extends JpaRepository<Feed, Long> {
    @Query(value = "SELECT fl.feedId, f.userId, f.title, f.content, f.category, f.createdAt, f.updatedAt, COUNT(fl.feedId) AS likeCount\n" +
            "FROM FeedLike fl JOIN Feed f ON fl.feedId = f.id\n" +
            "GROUP BY fl.feedId, f.userId, f.title, fl.feedId, f.content, f.category, f.createdAt, f.updatedAt\n" +
            "ORDER BY likeCount DESC", nativeQuery = true)
    Page<FeedResult> findAllByOrderByLikeCountDesc(PageRequest pageRequest);

    @Query(value = "SELECT fl.feedId, f.userId, f.title, f.content, f.category, f.createdAt, f.updatedAt, COUNT(fl.feedId) AS likeCount\n" +
            "FROM FeedLike fl JOIN Feed f ON fl.feedId = f.id\n" +
            "WHERE f.category = :category \n" +
            "GROUP BY fl.feedId, f.userId, f.title, fl.feedId, f.content, f.category, f.createdAt, f.updatedAt\n" +
            "ORDER BY likeCount DESC", nativeQuery = true)
    Page<FeedResult> findAllByCategoryOrderByLikeCountDesc(@Param("category") String category, PageRequest pageRequest);

    Optional<Feed> findById(Long feedId);

    interface FeedResult {
        Long getFeedId();
        Long getUserId();
        String getTitle();
        String getContent();
        String getCategory();
        LocalDateTime getCreatedAt();
        LocalDateTime getUpdatedAt();
        Long getLikeCount();
    }

    Page<Feed> findAllByOrderByCreatedAtDesc(PageRequest pageRequest);

    Page<Feed> findAllByCategoryOrderByCreatedAtDesc(String category, PageRequest pageRequest);

    Page<Feed> findAllByTitleContainingOrderByCreatedAtDesc(String keyword, PageRequest pageRequest);
}
