package ne.ordinary.dd.controller;

import lombok.RequiredArgsConstructor;
import ne.ordinary.dd.core.exception.Exception400;
import ne.ordinary.dd.model.FeedRequest;
import ne.ordinary.dd.model.FeedResponse;
import ne.ordinary.dd.model.ResponseDTO;
import ne.ordinary.dd.service.FeedService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class FeedController {

    private final FeedService feedService;

    @GetMapping("/feed/{id}")
    public ResponseDTO getFeed(@PathVariable Long id) {
        FeedResponse.FeedDTO feedDTO = feedService.getFeed(id);

        return new ResponseDTO(feedDTO);
    }

    @PostMapping("/feed")
    public ResponseDTO addFeed(@RequestBody FeedRequest.AddDTO addDTO) {
        // RELATIONSHIP, LOVE, FAMILY, COMPANY, EMPLOYMENT, HEALTH, SELFDEV, PET, ETC
        String category = addDTO.getCategory();
        if (!isCorrected(category) || !isCorrected(addDTO.getTitle()) || addDTO.getContent() == null) {
            throw new Exception400("category || title || content", "공백이나 null 값 대신 다른 값을 입력해주세요.");
        }
        if (!category.equals("RELATIONSHIP")
                && !category.equals("LOVE")
                && !category.equals("FAMILY")
                && !category.equals("COMPANY")
                && !category.equals("EMPLOYMENT")
                && !category.equals("HEALTH")
                && !category.equals("SELFDEV")
                && !category.equals("PET")
                && !category.equals("ETC")) {
            throw new Exception400("category", "category 값을 정해진 값으로 입력해주세요.");
        }
        feedService.addFeed(addDTO);

        return new ResponseDTO();
    }

    @PatchMapping("/feed/{id}")
    public ResponseDTO updateFeed(@PathVariable Long id, @RequestBody FeedRequest.UpdateDTO updateDTO) {
        // RELATIONSHIP, LOVE, FAMILY, COMPANY, EMPLOYMENT, HEALTH, SELFDEV, PET, ETC
        String category = updateDTO.getCategory();
        if (!isCorrected(category) || !isCorrected(updateDTO.getTitle()) || updateDTO.getContent() == null) {
            throw new Exception400("category || title || content", "공백이나 null 값 대신 다른 값을 입력해주세요.");
        }
        if (!category.equals("RELATIONSHIP")
                && !category.equals("LOVE")
                && !category.equals("FAMILY")
                && !category.equals("COMPANY")
                && !category.equals("EMPLOYMENT")
                && !category.equals("HEALTH")
                && !category.equals("SELFDEV")
                && !category.equals("PET")
                && !category.equals("ETC")) {
            throw new Exception400("category", "category 값을 정해진 값으로 입력해주세요.");
        }
        feedService.updateFeed(id, updateDTO);

        return new ResponseDTO();
    }

    @DeleteMapping("/feed/{id}")
    public ResponseDTO deleteFeed(@PathVariable Long id, @RequestBody FeedRequest.DeleteDTO deleteDTO) {
        feedService.deleteFeed(id, deleteDTO);

        return new ResponseDTO();
    }

    @DeleteMapping("/feed/{id}/like")
    public ResponseDTO deleteFeedLike(@PathVariable Long id, @RequestBody FeedRequest.DeleteLikeDTO deleteLikeDTO) {
        feedService.deleteFeedLike(id, deleteLikeDTO);

        return new ResponseDTO();
    }

    private boolean isCorrected(String str) {
        if (str != null && !str.isBlank()) {
            return true;
        }
        return false;
    }
}

