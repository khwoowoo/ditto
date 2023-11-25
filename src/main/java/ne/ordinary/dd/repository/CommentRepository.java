package ne.ordinary.dd.repository;

import ne.ordinary.dd.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Override
    Optional<Comment> findById(Long commentId);

    Long countByFeedId(Long postId);

    @Query("select c " +
            "from Comment c " +
            "where c.feed.id = :feedId")
    List<Comment> findByFeedId(@Param("feedId") Long feedId);

    @Query("select c " +
            "from Comment c " +
            "where c.parent.commentId = :parentId")
    List<Comment> findByParentId(@Param("parentId") Long parentId);
}
