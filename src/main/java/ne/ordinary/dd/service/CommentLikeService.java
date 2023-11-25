package ne.ordinary.dd.service;

import lombok.RequiredArgsConstructor;
import ne.ordinary.dd.domain.Comment;
import ne.ordinary.dd.domain.CommentLike;
import ne.ordinary.dd.repository.CommentLikeRepository;
import ne.ordinary.dd.repository.CommentRepository;
import ne.ordinary.dd.repository.UserRepositority;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;
    private final UserRepositority userRepositority;

    public void addTLike(Long commentId){

        Optional<CommentLike> TLike = commentLikeRepository.findCommentLikeByCommentId(commentId);
        Optional<Comment> comment = commentRepository.findById(commentId);

        if (TLike == null){
            CommentLike commentLike =  CommentLike
                    .builder()
                    .comment(comment.get())
                    .type(2L)
                    .user(comment.get().getWriter())
                    .build();
        }
    }
    public void addFLike(Long commentId){

        Optional<CommentLike> TLike = commentLikeRepository.findCommentLikeByCommentId(commentId);
        Optional<Comment> comment = commentRepository.findById(commentId);

        if (TLike == null){
            CommentLike commentLike =  CommentLike
                    .builder()
                    .comment(comment.get())
                    .type(1L)
                    .user(comment.get().getWriter())
                    .build();
        }
    }

    public void removeTLike(Long commentId){
        Optional<CommentLike> TLike = commentLikeRepository.findCommentLikeByCommentId(commentId);
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (TLike != null){
            commentLikeRepository.delete(TLike);
        }
    }
    public void removeFLike(Long commentId){
        Optional<CommentLike> FLike = commentLikeRepository.findCommentLikeByCommentId(commentId);
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (FLike != null){
            commentLikeRepository.delete(FLike);
        }
    }
}
