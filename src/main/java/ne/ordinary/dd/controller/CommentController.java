package ne.ordinary.dd.controller;

import lombok.RequiredArgsConstructor;
import ne.ordinary.dd.model.CommentLikeDTO;
import ne.ordinary.dd.model.CommentRequestDTO;
import ne.ordinary.dd.service.CommentLikeService;
import ne.ordinary.dd.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/comment")
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;
    private final CommentLikeService commentLikeService;

    @PostMapping("/{feedId}")
    public ResponseEntity<String> commentCreate(@PathVariable("feedId") Long feedId, @RequestBody CommentRequestDTO commentRequestDTO){
        commentService.saveComment(feedId, commentRequestDTO);
        return ResponseEntity.ok("댓글이 작성되었습니다");
    }
    @PostMapping("/{feedId}/{parentId}")
    public ResponseEntity<String> reCommentSave(@PathVariable("feedId") Long feedId, @PathVariable("parentId") Long commentId, @RequestBody CommentRequestDTO commentRequestDTO){
        commentService.saveRecomment(feedId, commentId, commentRequestDTO);
        return ResponseEntity.ok("대댓글이 작성되었습니다");
    }
    @PatchMapping("/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable("commentId") Long commentId, @RequestBody CommentRequestDTO commentRequestDTO){
        commentService.updateComment(commentId, commentRequestDTO);
        return ResponseEntity.ok("댓글이 수정되었습니다");
    }
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> delete(@PathVariable("commentId") Long commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("댓글이 삭제되었습니다");
    }
    @PostMapping("/{commentId}/Tlike")
    public ResponseEntity<String> addTlike(@PathVariable("commentId") Long commentId, @RequestBody CommentLikeDTO commentLikeDTO){
        commentLikeService.addTLike(commentId, commentLikeDTO);
        return ResponseEntity.ok("공감하였습니다");
    }
    @PostMapping("/{commentId}/Flike")
    public ResponseEntity<String> addFlike(@PathVariable("commentId") Long commentId, @RequestBody CommentLikeDTO commentLikeDTO){
        commentLikeService.addFLike(commentId, commentLikeDTO);
        return ResponseEntity.ok("공감하였습니다");
    }
    @DeleteMapping("/{commentId}/Tlike")
    public ResponseEntity<String> removeTLike(@PathVariable("commentId") Long commentId){
        commentLikeService.removeTLike(commentId);
        return ResponseEntity.ok("공감이 취소되었습니다");
    }
    @DeleteMapping("/{commentId}/Flike")
    public ResponseEntity<String> removeFLike(@PathVariable("commentId") Long commentId){
        commentLikeService.removeFLike(commentId);
        return ResponseEntity.ok("공감이 취소되었습니다");
    }
}
