package ne.ordinary.dd.controller;

import lombok.RequiredArgsConstructor;
import ne.ordinary.dd.model.NoticeDTO;
import ne.ordinary.dd.model.ResponseDTO;
import ne.ordinary.dd.service.NoticeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    // 1. 알림 조회 API
    @GetMapping("/{userId}")
    public ResponseDTO<List<NoticeDTO>> getNotices(@PathVariable Long userId) {
        return new ResponseDTO(noticeService.getNotices((userId)));
    }
}
