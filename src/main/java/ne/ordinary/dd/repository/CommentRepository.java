package ne.ordinary.dd.repository;

import ne.ordinary.dd.domain.Comment;
import ne.ordinary.dd.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Override
    Optional<Comment> findById(Long commentId);
}
