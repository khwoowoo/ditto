package ne.ordinary.dd.controller;

import lombok.RequiredArgsConstructor;
import ne.ordinary.dd.model.CommentRequestDTO;
import ne.ordinary.dd.service.CommentLikeService;
import ne.ordinary.dd.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/comment")
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;
    private final CommentLikeService commentLikeService;

    @PostMapping("/comment/{feedId}")
    public void commentCreate(@PathVariable("feedId") Long feedId, CommentRequestDTO commentRequestDTO){
        commentService.saveComment(feedId, commentRequestDTO);
    }
    @PostMapping("/commeent/{feedId}/[parentId]")
    public void reCommentSave(@PathVariable("feedId") Long feedId, @PathVariable("commentId") Long commentId, CommentRequestDTO commentRequestDTO){
        commentService.saveRecomment(feedId, commentId, commentRequestDTO);
    }
    @PatchMapping("/comment/{commentId}")
    public void updateComment(@PathVariable("commentId") Long commentId, CommentRequestDTO commentRequestDTO){
        commentService.updateComment(commentId, commentRequestDTO);
    }
    @DeleteMapping("/comment/{commentId}")
    public void delete(@PathVariable("commentId") Long commentId){
        commentService.deleteComment(commentId);
    }
    @PostMapping("/comment/[commentId]/Tilke")
    public void addTlike(@PathVariable("commentId") Long commentId, @RequestBody Long userId){
        commentLikeService.addTLike(commentId, userId);
    }
    @PostMapping("/comment/[commentId]/Filke")
    public void addFlike(@PathVariable("commentId") Long commentId, @RequestBody Long userId){
        commentLikeService.addFLike(commentId, userId);
    }
    @DeleteMapping("/comment/[commentId]/Tlike")
    public void removeTLike(@PathVariable("commentLike") Long commentId){
        commentLikeService.removeTLike(commentId);
    }
    @DeleteMapping("/comment/[commentId]/Flike")
    public void removeFLike(@PathVariable("commentLike") Long commentId){
        commentLikeService.removeFLike(commentId);
    }
}
