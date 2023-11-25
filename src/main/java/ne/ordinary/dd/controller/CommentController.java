package ne.ordinary.dd.controller;

import lombok.RequiredArgsConstructor;
import ne.ordinary.dd.model.CommentLikeDTO;
import ne.ordinary.dd.model.CommentRequestDTO;
import ne.ordinary.dd.model.ResponseDTO;
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
    public ResponseDTO<String> commentCreate(@PathVariable("feedId") Long feedId, @RequestBody CommentRequestDTO commentRequestDTO){
        commentService.saveComment(feedId, commentRequestDTO);
        return new ResponseDTO<String>("댓글이 작성되었습니다");
    }
    @PostMapping("/{feedId}/{parentId}")
    public ResponseDTO<String> reCommentSave(@PathVariable("feedId") Long feedId, @PathVariable("parentId") Long commentId, @RequestBody CommentRequestDTO commentRequestDTO){
        commentService.saveRecomment(feedId, commentId, commentRequestDTO);
        return new ResponseDTO<String>("대댓글이 작성되었습니다");
    }
    @PatchMapping("/{commentId}")
    public ResponseDTO<String> updateComment(@PathVariable("commentId") Long commentId, @RequestBody CommentRequestDTO commentRequestDTO){
        commentService.updateComment(commentId, commentRequestDTO);
        return new ResponseDTO<String>("댓글이 수정되었습니다");
    }
    @DeleteMapping("/{commentId}")
    public ResponseDTO<String> delete(@PathVariable("commentId") Long commentId){
        commentService.deleteCommentraw(commentId);
        return new ResponseDTO<String>("댓글이 삭제되었습니다");
    }
    @PostMapping("/{commentId}/Tlike")
    public ResponseDTO<String> addTlike(@PathVariable("commentId") Long commentId, @RequestBody CommentLikeDTO commentLikeDTO){
        commentLikeService.addTLike(commentId, commentLikeDTO);
        return new ResponseDTO<String>("공감하였습니다");
    }
    @PostMapping("/{commentId}/Flike")
    public ResponseDTO<String> addFlike(@PathVariable("commentId") Long commentId, @RequestBody CommentLikeDTO commentLikeDTO){
        commentLikeService.addFLike(commentId, commentLikeDTO);
        return new ResponseDTO<String>("공감하였습니다");
    }
    @DeleteMapping("/{commentId}/{userId}/Tlike")
    public ResponseDTO<String> removeTLike(@PathVariable("commentId") Long commentId, @PathVariable("userId") Long userId){
        commentLikeService.removeTLike(commentId, userId);
        return new ResponseDTO<String>("공감이 취소되었습니다");
    }
    @DeleteMapping("/{commentId}/{userId}/Flike")
    public ResponseDTO<String> removeFLike(@PathVariable("commentId") Long commentId, @PathVariable("userId") Long userId){
        commentLikeService.removeFLike(commentId, userId);
        return new ResponseDTO<String>("공감이 취소되었습니다");
    }
}
