package io.codechobostudy.qna.domain;

import javax.persistence.*;

@Entity
public class Answer {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @Embedded
    private Contents contents = new Contents();

    public Answer() {
    }

    public Answer(Question question, Contents contents) {
        this.question = question;
        this.contents = contents;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Contents getContents() {
        return contents;
    }

    public void setContents(Contents contents) {
        this.contents = contents;
    }
}
