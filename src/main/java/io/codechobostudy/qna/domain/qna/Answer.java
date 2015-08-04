package io.codechobostudy.qna.domain.qna;

import javax.persistence.*;

@Entity
public class Answer {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Contents contents = new Contents();

    public Answer() {
    }

    public Answer(Contents contents) {
        this.contents = contents;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contents getContents() {
        return contents;
    }

    public void setContents(Contents contents) {
        this.contents = contents;
    }
}
