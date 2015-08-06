package io.codechobostudy.qna.domain.qna;

import io.codechobostudy.qna.domain.auth.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Answer {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Question question;

    @OneToOne(fetch = FetchType.EAGER)
    private AnswerContent content;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANSWER_ID")
    private List<AnswerContent> contentHistory = new ArrayList<>();

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
}
