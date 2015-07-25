package io.codechobostudy.qna.domain.qna;

import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
public class Contents {
    private String body;
    private Date createDate;
    private Date modifyDate;

    public Contents() {
    }

    public Contents(String body, Date createDate) {
        this.body = body;
        this.createDate = createDate;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
