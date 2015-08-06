package io.codechobostudy.qna.domain.qna;

import io.codechobostudy.qna.domain.auth.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    private QuestionContent content;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="QUESTION_ID")
    private List<QuestionContent> contentHistory = new ArrayList<>();
    @OneToMany(mappedBy = "question")
    private List<Answer> answers;
    @ManyToMany
    private List<Tag> tags = new ArrayList<>();

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
}
