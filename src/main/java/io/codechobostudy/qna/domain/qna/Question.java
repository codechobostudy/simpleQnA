package io.codechobostudy.qna.domain.qna;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Question {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @Embedded
    private Contents contents = new Contents();

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="QUESTION_ID")
    private List<Answer> answers = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name="X_QUESTION_TAG",
            joinColumns={@JoinColumn(name="QUESTION_ID")},
            inverseJoinColumns={@JoinColumn(name="TAG_ID")})
    private Set<Tag> tags = new HashSet<>();

    public Question() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Contents getContents() {
        return contents;
    }

    public void setContents(Contents contents) {
        this.contents = contents;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
