package io.codechobostudy.qna.domain.qna;

import io.codechobostudy.qna.domain.auth.User;

import javax.persistence.*;
import java.util.Map;

@Embeddable
public class Vote {
    protected Integer up = 0;
    protected Integer down = 0;
    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    protected Map<User, VoteType> userVoteMap;

    public Integer getUp() {
        return up;
    }

    public void setUp(Integer up) {
        this.up = up;
    }

    public Integer getDown() {
        return down;
    }

    public void setDown(Integer down) {
        this.down = down;
    }

    public Integer getSum() {
        return up + down;
    }

    public Map<User, VoteType> getUserVoteMap() {
        return userVoteMap;
    }

    public void setUserVoteMap(Map<User, VoteType> userVoteMap) {
        this.userVoteMap = userVoteMap;
    }

}
