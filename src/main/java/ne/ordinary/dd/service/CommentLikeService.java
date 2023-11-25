package ne.ordinary.dd.service;

import lombok.RequiredArgsConstructor;
import ne.ordinary.dd.domain.Comment;
import ne.ordinary.dd.domain.CommentLike;
import ne.ordinary.dd.domain.Notice;
import ne.ordinary.dd.repository.CommentLikeRepository;
import ne.ordinary.dd.repository.CommentRepository;
import ne.ordinary.dd.repository.NoticeRepository;
import ne.ordinary.dd.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public ResponseEntity<String> addTLike(Long commentId, Long userId, Long type){

        Optional<CommentLike> TLike = commentLikeRepository.findCommentLikeByCommentId(commentId);
        Optional<Comment> comment = commentRepository.findById(commentId);

        if (TLike.isEmpty()){
            CommentLike commentLike =  CommentLike
                    .builder()
                    .comment(comment.get())
                    .type(type)
                    .user(userRepository.findUser(userId))
                    .build();

            commentLikeRepository.save(commentLike);
        }
        return ResponseEntity.ok("공감하셨습니다.");
    }
    public ResponseEntity<String> addFLike(Long commentId, Long userId, Long type){

        Optional<CommentLike> FLike = commentLikeRepository.findCommentLikeByCommentId(commentId);
        Optional<Comment> comment = commentRepository.findById(commentId);

        if (FLike.isEmpty()){
            CommentLike commentLike =  CommentLike
                    .builder()
                    .comment(comment.get())
                    .type(type)
                    .user(userRepository.findUser(userId))
                    .build();

            commentLikeRepository.save(commentLike);
        }
        return ResponseEntity.ok("공감하셨습니다.");
    }

    public ResponseEntity<String> removeTLike(Long commentId){
        Optional<CommentLike> TLike = commentLikeRepository.findCommentLikeByCommentId(commentId);
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (TLike.isEmpty()){
            commentLikeRepository.delete(TLike.get());
        }
        return ResponseEntity.ok("댓글 공감이 취소되었습니다.");
    }
    public ResponseEntity<String> removeFLike(Long commentId){
        Optional<CommentLike> FLike = commentLikeRepository.findCommentLikeByCommentId(commentId);
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (FLike.isEmpty()){
            commentLikeRepository.delete(FLike.get());
        }
        return ResponseEntity.ok("댓글 공감이 취소되었습니다.");
    }
}
