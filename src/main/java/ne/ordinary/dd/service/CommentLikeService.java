package ne.ordinary.dd.service;

import lombok.RequiredArgsConstructor;
import ne.ordinary.dd.domain.Comment;
import ne.ordinary.dd.domain.CommentLike;
import ne.ordinary.dd.repository.CommentLikeRepository;
import ne.ordinary.dd.repository.CommentRepository;
import ne.ordinary.dd.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public void addTLike(Long commentId, Long userId){

        Optional<CommentLike> TLike = commentLikeRepository.findCommentLikeByCommentId(commentId);
        Optional<Comment> comment = commentRepository.findById(commentId);

        if (TLike.isEmpty()){
            CommentLike commentLike =  CommentLike
                    .builder()
                    .comment(comment.get())
                    .type(2L)
                    .user(userRepository.findUser(userId))
                    .build();
        }
    }
    public void addFLike(Long commentId, Long userId){

        Optional<CommentLike> FLike = commentLikeRepository.findCommentLikeByCommentId(commentId);
        Optional<Comment> comment = commentRepository.findById(commentId);

        if (FLike.isEmpty()){
            CommentLike commentLike =  CommentLike
                    .builder()
                    .comment(comment.get())
                    .type(1L)
                    .user(userRepository.findUser(userId))
                    .build();
        }
    }

    public void removeTLike(Long commentId){
        Optional<CommentLike> TLike = commentLikeRepository.findCommentLikeByCommentId(commentId);
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (TLike.isEmpty()){
            commentLikeRepository.delete(TLike);
        }
    }
    public void removeFLike(Long commentId){
        Optional<CommentLike> FLike = commentLikeRepository.findCommentLikeByCommentId(commentId);
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (FLike.isEmpty()){
            commentLikeRepository.delete(FLike);
        }
    }
}
