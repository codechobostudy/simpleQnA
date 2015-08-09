package io.codechobostudy.qna.domain.qna;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TAG")
public class Tag {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Question> questions = new ArrayList<>();

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
