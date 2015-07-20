package io.codechobostudy.qna.domain;

import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
public class Contents {
    private String body;
    private Date date;

    public Contents() {
    }

    public Contents(String body, Date date) {
        this.body = body;
        this.date = date;
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
}
