package ne.ordinary.dd.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Table(name = "feedLike")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long feedLikeId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feedId", nullable = false)
    private Feed feed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(name = "sympathy1", nullable = false)
    private int sympathy1;

    @Column(name = "sympathy2", nullable = false)
    private int sympathy2;

    @Column(name = "sympathy3", nullable = false)
    private int sympathy3;

    @Column(name = "sympathy4", nullable = false)
    private int sympathy4;

    @Column(name = "sympathy5", nullable = false)
    private int sympathy5;

    @Builder
    public FeedLike(Feed feed, User user, int sympathy1, int sympathy2, int sympathy3, int sympathy4, int sympathy5) {
        this.feed = feed;
        this.user = user;
        this.sympathy1 = sympathy1;
        this.sympathy2 = sympathy2;
        this.sympathy3 = sympathy3;
        this.sympathy4 = sympathy4;
        this.sympathy5 = sympathy5;
    }
}
