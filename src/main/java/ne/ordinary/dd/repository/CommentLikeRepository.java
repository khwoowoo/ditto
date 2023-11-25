package ne.ordinary.dd.repository;

import ne.ordinary.dd.domain.Comment;
import ne.ordinary.dd.domain.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    Optional<CommentLike> findCommentLikeByCommentId(Long commentId);

    void delete(Optional<CommentLike> commentLike);
}
