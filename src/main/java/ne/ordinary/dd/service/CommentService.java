package ne.ordinary.dd.service;

import lombok.RequiredArgsConstructor;
import ne.ordinary.dd.core.exception.Exception404;
import ne.ordinary.dd.domain.Comment;
import ne.ordinary.dd.domain.Feed;
import ne.ordinary.dd.model.CommentRequestDTO;
import ne.ordinary.dd.repository.CommentRepository;
import ne.ordinary.dd.repository.FeedsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final FeedsRepository feedsRepository;
    //댓글 작성
    public ResponseEntity<String> saveComment(Long feedId, CommentRequestDTO commentRequestDTO){
        Comment comment = commentRequestDTO.toEntity();
        Optional<Feed> feed = feedsRepository.findById(feedId);
        commentRepository.save(comment);
        comment.setFeed(feed.get());
        return ResponseEntity.ok("댓글이 작성되었습니다");
    }

    //대댓글 작성
    public ResponseEntity<String> saveRecomment(Long feedId, Long parentId, CommentRequestDTO commentRequestDTO){
        Comment comment = commentRequestDTO.toEntity();
        Optional<Feed> feed = feedsRepository.findById(feedId);
        comment.confirmParent(commentRepository.findById(parentId).orElseThrow(() -> new Exception404("댓글이 존재하지 않습니다.")));
        commentRepository.save(comment);
        comment.setFeed(feed.get());
        return ResponseEntity.ok("대댓글이 작성되었습니다");
    }

    //댓글 수정
    public ResponseEntity<String> updateComment(Long commentId, CommentRequestDTO commentRequestDTO){
        Optional<Comment> comment = commentRepository.findById(commentId);
        comment.get().setContent(commentRequestDTO.getContent());
        return ResponseEntity.ok("댓글이 수정되었습니다");
    }

    //댓글 삭제
    public ResponseEntity<String> deleteComment(Long commentId){
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new Exception404("댓글이 없습니다."));
        comment.remove();
        List<Comment> removableCommentList = comment.findRemovableList();
        removableCommentList.forEach(removableComment -> commentRepository.delete(removableComment));
        return ResponseEntity.ok("댓글이 삭제되었습니다");
    }
}
