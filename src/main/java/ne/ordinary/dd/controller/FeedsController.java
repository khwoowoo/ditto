package ne.ordinary.dd.controller;

import lombok.RequiredArgsConstructor;
import ne.ordinary.dd.core.exception.Exception400;
import ne.ordinary.dd.model.FeedsDTO;
import ne.ordinary.dd.model.ResponseDTO;
import ne.ordinary.dd.service.FeedsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feeds")
@RequiredArgsConstructor
public class FeedsController {
    private final FeedsService feedsService;

    // 1. 피드리스트 홈 조회 (인기글)
    @GetMapping("/hot")
    public ResponseDTO<List<FeedsDTO.Response>> getHotFeeds(@RequestParam String category, @RequestParam int page) {
        if(page < 0)
            throw new Exception400("page", "page 인덱스는 0보다 작을 수 없습니다.");
        return new ResponseDTO(feedsService.getHotFeeds(category, page));

    }

    // 2. 피드리스트 홈 조회 (최근글)
    @GetMapping("/recent")
    public ResponseDTO<List<FeedsDTO.Response>> getRecentFeeds(@RequestParam String category, @RequestParam int page) {
        if(page < 0)
            throw new Exception400("page", "page 인덱스는 0보다 작을 수 없습니다.");
        return new ResponseDTO(feedsService.getRecentFeeds(category, page));
    }

    // 3. 피드 조회
    @GetMapping("")
    public ResponseDTO<List<FeedsDTO.Response>> getFeeds(@RequestParam String keyword, @RequestParam int page) {
        if(page < 0)
            throw new Exception400("page", "page 인덱스는 0보다 작을 수 없습니다.");
        return new ResponseDTO(feedsService.getFeeds(keyword, page));
    }

    // 4. 피드 공감
    @PostMapping("/{feedId}/like")
    public ResponseDTO<String> postFeedLike(@RequestBody FeedsDTO.Request request) {
        return new ResponseDTO(feedsService.postFeedLike(request));
    }
}
