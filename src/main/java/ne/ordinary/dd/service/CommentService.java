package ne.ordinary.dd.service;

import lombok.RequiredArgsConstructor;
import ne.ordinary.dd.core.exception.Exception404;
import ne.ordinary.dd.domain.Comment;
import ne.ordinary.dd.model.CommentRequestDTO;
import ne.ordinary.dd.repository.CommentRepository;
import ne.ordinary.dd.repository.FeedRepository;
import ne.ordinary.dd.repository.UserRepositority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepositority userRepositority;
    private final FeedRepository feedRepository;
    //댓글 작성
    public void saveComment(Long feedId, CommentRequestDTO commentRequestDTO){
        Comment comment = commentRequestDTO.toEntity();
        comment.confirmPost(feedRepository.findById(feedId).orElseThrow(() -> new Exception404("게시글이 존재하지 않습니다.")));

        commentRepository.save(comment);
    }

    //대댓글 작성
    public void saveRecomment(Long feedId, Long parentId, CommentRequestDTO commentRequestDTO){
        Comment comment = commentRequestDTO.toEntity();
        comment.confirmPost(feedRepository.findById(feedId).orElseThrow(() -> new Exception404("게시글이 존재하지 않습니다.")));
        comment.confirmParent(commentRepository.findById(parentId).orElseThrow(() -> new Exception404("댓글이 존재하지 않습니다.")));
        commentRepository.save(comment);
    }

    //댓글 수정
    public void updateComment(Long commentId, CommentRequestDTO commentRequestDTO){
        Optional<Comment> comment = commentRepository.findById(commentId);
        comment.get().setContent(commentRequestDTO.getContent());
    }

    //댓글 삭제
    public void deleteComment(Long commentId){
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new Exception404("댓글이 없습니다."));
        comment.remove();
        List<Comment> removableCommentList = comment.findRemovableList();
        removableCommentList.forEach(removableComment -> commentRepository.delete(removableComment));
    }
}
