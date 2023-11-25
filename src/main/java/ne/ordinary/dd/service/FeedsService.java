package ne.ordinary.dd.service;

import lombok.RequiredArgsConstructor;
import ne.ordinary.dd.core.exception.Exception400;
import ne.ordinary.dd.domain.Feed;
import ne.ordinary.dd.domain.FeedLike;
import ne.ordinary.dd.domain.User;
import ne.ordinary.dd.model.FeedsDTO;
import ne.ordinary.dd.repository.FeedsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedsService {
    private final FeedsRepository feedsRepository;
    private final FeedLikeRepository feedLikeRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public List<FeedsDTO.Response> getHotFeeds(String category, int page) {
        try{
            PageRequest pageRequest = PageRequest.of(page, 20);
            Page<FeedsRepository.FeedResult> feedList;

            if(category.equals("default"))
                feedList = feedsRepository.findAllByOrderByLikeCountDesc(pageRequest);
            else
                feedList = feedsRepository.findAllByCategoryOrderByLikeCountDesc(category, pageRequest);

            return feedList.stream()
                    .map(f -> {
                        Long commentCount = commentRepository.countByPostId(f.getId()); // commentRepository에 추가
                        return new FeedsDTO.Response(f, f.getLikeCount(), commentCount);
                    })
                    .collect(Collectors.toList());
        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception400("page", "page 인덱스는 0보다 작을 수 없습니다.");
        }
    }

    public List<FeedsDTO.Response> getRecentFeeds(String category, int page) {
        try{
            PageRequest pageRequest = PageRequest.of(page, 20);
            Page<Feed> feedList;
            if(category.equals("default"))
                feedList = feedsRepository.findAllByOrderByCreatedAtDesc(pageRequest);
            else
                feedList = feedsRepository.findAllByCategoryOrderByCreatedAtDesc(category, pageRequest);


            return feedList.stream()
                    .map(f -> {
                        Long likeCount = feedLikeRepository.countByFeedId(f.getId());   // feedLikeRepository에 추가
                        Long commentCount = commentRepository.countByPostId(f.getId()); // commentRepository에 추가

                        return new FeedsDTO.Response(f, likeCount, commentCount);
                    })
                    .collect(Collectors.toList());
        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception400("page", "page 인덱스는 0보다 작을 수 없습니다.");
        }
    }

    public List<FeedsDTO.Response> getFeeds(String keyword, int page) {
        try{
            PageRequest pageRequest = PageRequest.of(page, 20);
            Page<Feed> feedList = feedsRepository.findAllByTitleContainingOrderByCreatedAtDesc(keyword, pageRequest);

            if(feedList.isEmpty())
                throw new Exception400("keyword", "해당 키워드에 대한 검색 결과가 없습니다.");

            return feedList.stream()
                    .map(f -> {
                        Long likeCount = feedLikeRepository.countByFeedId(f.getId());   // feedLikeRepository에 추가
                        Long commentCount = commentRepository.countByPostId(f.getId()); // commentRepository에 추가

                        return new FeedsDTO.Response(f, likeCount, commentCount);
                    })
                    .collect(Collectors.toList());
        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception400("page", "page 인덱스는 0보다 작을 수 없습니다.");
        }
    }

    @Transactional
    public String postFeedLike(FeedsDTO.Request request) {
        Optional<Feed> getFeed = feedsRepository.findById(request.getFeedId());
        Optional<User> getUser = userRepository.findById(request.getUserId());

        if(getFeed.isEmpty() || getUser.isEmpty())
            throw new Exception400("request", "피드 Id 혹은 유저 Id가 유효하지 않습니다.");

        FeedLike feedLike = FeedLike.builder()
                .feed(getFeed.get())
                .user(getUser.get())
                .sympathy1(request.getSympathy1())
                .sympathy2(request.getSympathy2())
                .sympathy3(request.getSympathy3())
                .sympathy4(request.getSympathy4())
                .sympathy5(request.getSympathy5())
                .build();

        feedLikeRepository.save(feedLike);
        return "공감하셨습니다.";
    }
}
