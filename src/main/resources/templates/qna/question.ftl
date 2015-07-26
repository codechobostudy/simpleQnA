<#import "./_sidebarLayout.ftl" as layout>

<#assign contents>

<div class="question-wrapper">
    <h1>${question.title}</h1>

    <div class="row">
    ${question.contents.body}
    </div>

    <div class="row question-meta-data">
        <div class="tags">
            <span class="label label-primary">#java</span>
            <span class="label label-primary">#spring</span>
            <span class="label label-primary">#javascript</span>
        </div>

        <div class="thumbnail user">
            만든이 : Test
        </div>
        <div class="contents-date">${question.contents.createDate}</div>
    </div>

    <hr>

    <div class="row">
        <div lass="btn-group" role="group">
            <a href="/questions" class="btn btn-default">목록으로</a>
            <a href="/questions/${question.id}/edit" class="btn btn-default pull-right">수정</a>
            <a href="/questions/${question.id}/delete" class="btn btn-default pull-right">삭제</a>
        </div>
    </div>
</div>


<div class="answer-wrapper">
    <div class="answers">
        <#list question.answers as answer>
            <div class="answer" data-answer-id="${answer.id}">
                <div>작성일: ${answer.contents.createDate}</div>
                <div>
                    <textarea class="answer-body">${answer.contents.body}</textarea>
                </div>
                <div>
                    <button class="btn-edit-answer">수정</button>
                    <button class="btn-delete-answer">삭제</button>
                </div>
            </div>
            <#if answer_has_next>
                <hr>
            </#if>
        </#list>
    </div>

    <div class="new-answer">
        <div>신규 댓글</div>
        <div>
            <textarea class="new-answer-body"></textarea>
        </div>
        <div>
            <button class="btn-new-answer">추가</button>
        </div>
    </div>
</div>
</#assign>

<#assign script>
<script>
    $(".btn-new-answer").on("click", function () {
        var params = {
            body: $(".new-answer-body").val()
        };

        $.ajax({
            url: "/questions/${question.id}/answers",
            method: "post",
            data: params
        }).done(function (data) {
            location.href = "/questions/${question.id}"
        }).error(function (data) {
            alert("에러발생");
            console.log(data);
        });
    });

    $(".btn-edit-answer").on("click", function () {
        var answerId = $(this).closest(".answer").attr("data-answer-id");

        var params = {
            body: $(this).closest(".answer").find(".answer-body").val()
        };

        $.ajax({
            url: "/questions/${question.id}/answers/" + answerId + "/edit",
            method: "post",
            data: params
        }).done(function () {
            location.href = "/questions/${question.id}"
        }).error(function (data) {
            alert("에러발생");
            console.log(data);
        });
    });

    $(".btn-delete-answer").on("click", function () {
        var answerId = $(this).closest(".answer").attr("data-answer-id");

        $.ajax({
            url: "/questions/${question.id}/answers/" + answerId,
            method: "delete"
        }).done(function () {
            location.href = "/questions/${question.id}"
        }).error(function (data) {
            alert("에러발생");
            console.log(data);
        });
    });
</script>
</#assign>

<@layout.sidebar title="${question.title}" contents=contents script=script/>