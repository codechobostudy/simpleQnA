package io.codechobostudy.qna.dto.qna;

public class QuestionForm {
    private long id;
    private String title;
    private String body;
    private long tagId[];

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long[] getTagId() {
        return tagId;
    }

    public void setTagId(long[] tagId) {
        this.tagId = tagId;
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
}
