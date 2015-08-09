package io.codechobostudy.qna.domain.qna;

import io.codechobostudy.qna.domain.auth.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="ANSWER_CONTENT")
public class AnswerContent {
    @Id
    @GeneratedValue
    @Column(name="ID")
    private long id;

    @Lob
    @Column(name="BODY")
    private String body;

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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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
