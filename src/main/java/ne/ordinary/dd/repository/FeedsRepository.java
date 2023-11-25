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
    @Query(value = "SELECT f.id, f.user_user_id AS userId, f.title, f.content, f.category, f.created_at, f.updated_at, COUNT(fl.feed_id) AS likeCount\n" +
            "FROM feed_like fl RIGHT OUTER JOIN feed f ON fl.feed_id = f.id\n" +
            "GROUP BY fl.feed_id, f.user_user_id, f.title, fl.feed_id, f.content, f.category, f.created_at, f.updated_at\n" +
            "ORDER BY likeCount DESC", nativeQuery = true)
    Page<FeedResult> findAllByOrderByLikeCountDesc(PageRequest pageRequest);

    @Query(value = "SELECT f.id, f.user_user_id AS userId, f.title, f.content, f.category, f.created_at, f.updated_at, COUNT(fl.feed_id) AS likeCount\n" +
            "FROM feed_like fl RIGHT OUTER JOIN feed f ON fl.feed_id = f.id\n" +
            "WHERE f.category = :category\n" +
            "GROUP BY fl.feed_id, f.user_user_id, f.title, fl.feed_id, f.content, f.category, f.created_at, f.updated_at\n" +
            "ORDER BY likeCount DESC;", nativeQuery = true)
    Page<FeedResult> findAllByCategoryOrderByLikeCountDesc(@Param("category") String category, PageRequest pageRequest);

    Optional<Feed> findById(Long feedId);

    interface FeedResult {
        Long getId();
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
