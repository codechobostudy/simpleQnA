package io.codechobostudy.qna.domain.qna;

import io.codechobostudy.qna.domain.auth.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ANSWER")
public class Answer {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ANSWER_CONTENT_ID")
    private AnswerContent content;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANSWER_ID")
    private List<AnswerContent> contentHistory = new ArrayList<>();

    @Embedded
    @AssociationOverride(name = "userVoteMap", joinTable = @JoinTable(name = "X_ANSWER_VOTE_USER"))
    private Vote vote;

    public Long getId() {
        return id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public AnswerContent getContent() {
        return content;
    }

    public void setContent(AnswerContent content) {
        this.content = content;
    }

    public List<AnswerContent> getContentHistory() {
        return contentHistory;
    }

    public String getBody() {
        return content.getBody();
    }

    public Date getEditDate() {
        return content.getDate();
    }

    public User getEditUser() {
        return content.getUser();
    }

    public Vote getVote() {
        return vote;
    }
}
