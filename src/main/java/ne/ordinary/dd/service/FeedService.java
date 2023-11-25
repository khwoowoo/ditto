package ne.ordinary.dd.service;

import lombok.RequiredArgsConstructor;
import ne.ordinary.dd.core.exception.Exception404;
import ne.ordinary.dd.domain.Comment;
import ne.ordinary.dd.domain.Feed;
import ne.ordinary.dd.domain.FeedLike;
import ne.ordinary.dd.domain.User;
import ne.ordinary.dd.model.CommentResponse;
import ne.ordinary.dd.model.FeedResponse;
import ne.ordinary.dd.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FeedService {

    private final UserRepository userRepository;
    private final FeedsRepository feedsRepository;
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final FeedLikeRepository feedLikeRepository;

    public FeedResponse.FeedDTO getFeed(Long id) {
        Feed feedPS = feedsRepository.findById(id).orElseThrow(
                () -> new Exception404("해당 피드를 찾을 수 없습니다.")
        );

        User userPS = userRepository.findById(id).orElseThrow(
                () -> new Exception404("해당 유저를 찾을 수 없습니다.")
        );

        List<Comment> comments = commentRepository.findByFeedId(feedPS.getId());
        List<CommentResponse.CommentDTO> commentDTOs = new ArrayList<>();
        for (Comment c : comments) {
            CommentResponse.CommentDTO commentDTO = new CommentResponse.CommentDTO(
                    c,
                    commentLikeRepository.countByCommentIdAndType(c.getCommentId(), 1),
                    commentLikeRepository.countByCommentIdAndType(c.getCommentId(), 2),
                    commentLikeRepository.countByCommentIdAndUserId(c.getCommentId(), userPS.getId()) >= 1 ? true : false,
                    new CommentResponse.AuthorDTO(c.getUser())
            );
            List<Comment> reComments = commentRepository.findByParentId(c.getCommentId());
            List<CommentResponse.ReCommentDTO> reCommentDTOs = new ArrayList<>();

            for (Comment re : reComments) {
                CommentResponse.ReCommentDTO reCommentDTO = new CommentResponse.ReCommentDTO(
                        re,
                        commentLikeRepository.countByCommentIdAndType(re.getCommentId(), 1),
                        commentLikeRepository.countByCommentIdAndType(re.getCommentId(), 2),
                        commentLikeRepository.countByCommentIdAndUserId(re.getCommentId(), userPS.getId()) >= 1 ? true : false,
                        new CommentResponse.AuthorDTO(re.getUser())
                );

                reCommentDTOs.add(reCommentDTO);
            }
            commentDTO.setComments(reCommentDTOs);

            commentDTOs.add(commentDTO);
        }

        Optional<FeedLike> feedLikeOP = feedLikeRepository.findByUserIdAndFeedId(userPS.getId(), feedPS.getId());
        FeedResponse.FeedDTO feedDTO = new FeedResponse.FeedDTO(
                feedPS,
                new FeedResponse.AuthorDTO(userPS),
                feedLikeOP.isPresent() && feedLikeOP.get().getSympathy1() >= 1
                        || feedLikeOP.get().getSympathy2() >= 1
                        || feedLikeOP.get().getSympathy3() >= 1
                        || feedLikeOP.get().getSympathy4() >= 1
                        || feedLikeOP.get().getSympathy5() >= 1
                        ? true : false,
                countComments(commentDTOs),
                commentDTOs
        );
        if (feedLikeOP.isPresent()) {
            FeedLike feedLike = feedLikeOP.get();
            feedDTO.setSympathy1(feedLike.getSympathy1());
            feedDTO.setSympathy2(feedLike.getSympathy2());
            feedDTO.setSympathy3(feedLike.getSympathy3());
            feedDTO.setSympathy4(feedLike.getSympathy4());
            feedDTO.setSympathy5(feedLike.getSympathy5());
        } else {
            feedDTO.setSympathy1(0);
            feedDTO.setSympathy2(0);
            feedDTO.setSympathy3(0);
            feedDTO.setSympathy4(0);
            feedDTO.setSympathy5(0);
        }

        return feedDTO;
    }

    private int countComments(List<CommentResponse.CommentDTO> comments) {
        int count = 0;

        for (CommentResponse.CommentDTO commentDTO : comments) {
            count++;
            for (CommentResponse.ReCommentDTO re : commentDTO.getComments()) {
                count++;
            }
        }

        return count;
    }
}
