package ne.ordinary.dd.controller;

import lombok.RequiredArgsConstructor;
import ne.ordinary.dd.model.ResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeedController {

    @GetMapping("/feed/{id}")
    public ResponseDTO getFeed(@PathVariable Long id) {

    }


}
