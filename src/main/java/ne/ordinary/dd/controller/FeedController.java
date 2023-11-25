package ne.ordinary.dd.controller;

import lombok.RequiredArgsConstructor;
import ne.ordinary.dd.model.FeedResponse;
import ne.ordinary.dd.model.ResponseDTO;
import ne.ordinary.dd.service.FeedService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeedController {

    private final FeedService feedService;

    @GetMapping("/feed/{id}")
    public ResponseDTO getFeed(@PathVariable Long id) {
        FeedResponse.FeedDTO feedDTO = feedService.getFeed(id);

        return new ResponseDTO(feedDTO);
    }

}

