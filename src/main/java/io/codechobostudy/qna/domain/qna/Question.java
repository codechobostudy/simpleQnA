package io.codechobostudy.qna.domain.qna;

import io.codechobostudy.qna.domain.auth.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="QUESTION")
public class Question {

    @Id
    @GeneratedValue
    @Column(name="ID")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="QUESTION_CONTENT_ID")
    private QuestionContent content;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID")
    private List<QuestionContent> contentHistory = new ArrayList<>();

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

    @ManyToMany
    @JoinTable(name="X_QUESTION_TAG")
    private List<Tag> tags = new ArrayList<>();

    @Embedded
    @AssociationOverride(
            name="userVoteMap",
            joinTable = @JoinTable(name="X_QUESTION_VOTE_USER")
    )
    private Vote vote;

    public Long getId() {
        return id;
    }

    public QuestionContent getContent() {
        return content;
    }

    public void setContent(QuestionContent content) {
        this.content = content;
    }

    public List<QuestionContent> getContentHistory() {
        return contentHistory;
    }

    public String getTitle() {
        return content.getTitle();
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

    public List<Tag> getTags() {
        return tags;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Vote getVote() {
        return vote;
    }
}
