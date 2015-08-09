package io.codechobostudy.qna.domain.qna;

import io.codechobostudy.qna.domain.auth.User;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="QUESTION_CONTENT")
public class QuestionContent {

    @Id
    @GeneratedValue
    @Column(name="ID")
    private long id;

    @Column(name="TITLE")
    private String title;

    @Lob
    @Column(name="BODY")
    private String body;

    //TODO CSV형식으로 변경할 것.
    //단순 자료 저장이므로, 굳이 테이블 연관관계를 만들 필요성은 없음
    @ElementCollection
    private Set<String> tags = new HashSet<>();

    @Column(name="DATE")
    private Date date;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @Column(name="CHANGE_LOG")
    private String changeLog;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Set<String> getTags() {
        return tags;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getChangeLog() {
        return changeLog;
    }

    public void setChangeLog(String changeLog) {
        this.changeLog = changeLog;
    }
}
