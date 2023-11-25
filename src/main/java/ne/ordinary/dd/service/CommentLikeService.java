package ne.ordinary.dd.service;

import lombok.RequiredArgsConstructor;
import ne.ordinary.dd.domain.Comment;
import ne.ordinary.dd.domain.CommentLike;
import ne.ordinary.dd.domain.Notice;
import ne.ordinary.dd.domain.User;
import ne.ordinary.dd.model.CommentLikeDTO;
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

    public void addTLike(Long commentId, CommentLikeDTO commentLikeDTO){

        Optional<CommentLike> TLike = commentLikeRepository.findCommentLikeByUserCommentId(commentLikeDTO.getUserId(), commentId);
        Optional<Comment> comment = commentRepository.findById(commentId);

        if (TLike.isEmpty()){
            CommentLike commentLike =  CommentLike
                    .builder()
                    .comment(comment.get())
                    .type(commentLikeDTO.getType())
                    .user(userRepository.findUser(commentLikeDTO.getUserId()))
                    .build();

            commentLikeRepository.save(commentLike);
        }
    }
    public void addFLike(Long commentId, CommentLikeDTO commentLikeDTO){

        Optional<CommentLike> FLike = commentLikeRepository.findCommentLikeByUserCommentId(commentLikeDTO.getUserId(), commentId);
        Optional<Comment> comment = commentRepository.findById(commentId);

        if (FLike.isEmpty()){
            CommentLike commentLike =  CommentLike
                    .builder()
                    .comment(comment.get())
                    .type(commentLikeDTO.getType())
                    .user(userRepository.findUser(commentLikeDTO.getUserId()))
                    .build();

            commentLikeRepository.save(commentLike);
        }
    }

    public void removeTLike(Long commentId, Long userId){
        Optional<CommentLike> TLike = commentLikeRepository.findCommentLikeByUserCommentId(commentId, userId);
        if (TLike.isEmpty()){
            commentLikeRepository.delete(TLike.get());
        }
    }
    public void removeFLike(Long commentId, Long userId){
        Optional<CommentLike> FLike = commentLikeRepository.findCommentLikeByUserCommentId(commentId, userId);
        if (FLike.isEmpty()){
            commentLikeRepository.delete(FLike.get());
        }
    }
}
