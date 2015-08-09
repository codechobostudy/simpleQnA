<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#import "./_sidebarLayout.ftl" as layout>

<#assign css>
<link href="//cdnjs.cloudflare.com/ajax/libs/summernote/0.6.15/summernote.min.css" rel="stylesheet">
<style>
    .question-header {
        margin-bottom: 20px;
    }

    .question-title {
        margin-bottom: 5px;
    }
</style>
</#assign>

<#assign contents>
<div class="question" data-question-id="${question.id}">
    <div class="question-header">
        <h1 class="question-title">${question.title}</h1>

        <div class="tags">
            <#list question.tags as tag>
                <span class="label label-primary">${tag.name}</span>
            </#list>
        </div>
    </div>
    <!--/.question-header-->

    <div class="question-contents panel panel-default">
        <div class="contents-body panel-body">
        ${question.body}
        </div>

        <div class="contents-metadata panel-footer">
            <div class="contents-author">작성자 : ${question.editUser.email}</div>
            <div class="contents-date">작성일 : ${question.editDate}</div>
        </div>
    </div>

    <hr>

    <div class="question-action btn-group" role="group">
        <a href="/questions" class="btn btn-default">목록으로</a>
        <#if currentUser?? && (currentUser.role == "ADMIN" || currentUser.user.id == question.editUser.id)>
            <a href="/questions/${question.id}/edit" class="btn btn-default pull-right">수정</a>
            <a href="/questions/${question.id}/delete" class="btn btn-default pull-right">삭제</a>
        </#if>
    </div>
</div>

<div class="answers-wrapper">
    <div class="answers-info">
        <h2>등록된 답변 : ${question.answers?size}</h2>
    </div>

    <div class="answers">
        <#list question.answers as answer>
            <div class="answer-wrapper" data-answer-id="${answer.id}">

                <div class="answer panel panel-default">
                    <div class="contents-body panel-body">${answer.body}</div>
                    <div class="panel-footer">
                        <div class="answer-meta-data">
                            <div class="contents-author">작성자 : ${answer.editUser.email}</div>
                            <div class="contents-date">작성일 : ${answer.editDate}</div>
                        </div>

                        <#if currentUser?? && (currentUser.role == "ADMIN" || currentUser.user.id == answer.editUser.id)>
                            <div class="btn-group" role="group">
                                <button class="btn-edit-answer btn btn-default">수정</button>
                                <button class="btn-delete-answer btn btn-default">삭제</button>
                            </div>
                        </#if>
                    </div>
                    <!--/ .panel-footer-->
                </div>
                <!--/ .answer-->

                <#if currentUser?? && (currentUser.role == "ADMIN" || currentUser.user.id == answer.editUser.id)>
                    <div class="answer-editor-wrapper" style="display: none">
                        <div class="answer-editor"></div>
                        <button class="btn-edit-confirm-answer btn btn-default">확인</button>
                        <hr>
                    </div>
                    <!--/.answer-editor-->
                </#if>
            </div>
        </#list>
    </div>

    <#if currentUser??>
        <div class="new-answer">
            <div>신규 댓글 등록</div>
            <div id="newAnswerEditor"></div>
            <div>
                <button class="btn-new-answer">추가</button>
            </div>
        </div>
    <#else>
        <div class="answer-need-login well well-lg text-center">
            <span class="lead">
                <a href="/login">로그인</a> 후 답변을 등록할 수 있습니다.
            </span>
        </div>
    </#if>
</div>
</#assign>

<#assign script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.6.15/summernote.js"></script>
<script>
    var _questionId = $(".question").attr("data-question-id");

    // 기존 답변 수정
    // 수정 버튼을 누를 때 내용을 에디터 창으로 변환한다.
    $(".btn-edit-answer").on("click", function () {
        var $answer = $(this).closest(".answer");
        var $editorWrapper = $answer.next();
        var $editor = $editorWrapper.find(".answer-editor");

        var editor = $editor.summernote({
            height: $answer.height()
        });

        //TODO 서버에서 contents를 가져온 뒤 보여줄 것
        editor.code($answer.find(".contents-body").html());

        $editorWrapper.show();
        $answer.hide();
    });

    $(".btn-edit-confirm-answer").on("click", function () {
        window.test = $(this);
        var $answer = $(this).parent().prev();
        var $editorWrapper = $(this).parent();
        var $editor = $editorWrapper.find(".answer-editor");

        var answerId = $(this).closest(".answer-wrapper").attr("data-answer-id");
        var body = $editor.code();

        console.log($answer);
        $.ajax({
            url: "/questions/" + _questionId + "/answers/" + answerId + "/edit",
            method: "post",
            data: {
                id : answerId,
                body : body,
                "${_csrf.parameterName}": "${_csrf.token}"
            }
        }).done(function () {
            $answer.find(".contents-body").html(body);
            $answer.show();
            $editor.destroy();
            $editorWrapper.hide();
        }).error(function (data) {
            alert("에러발생");
            console.log(data);
        });
    });

    $(".btn-delete-answer").on("click", function () {
        var answerId = $(this).closest(".answer-wrapper").attr("data-answer-id");

        $.ajax({
            url: "/questions/" + _questionId + "/answers/" + answerId + "/delete",
            method: "post",
            data : {
                "${_csrf.parameterName}": "${_csrf.token}"
            }
        }).done(function () {
            location.href = "/questions/" + _questionId
        }).error(function (data) {
            alert("에러발생");
            console.log(data);
        });
    });

    //신규 답변 추가
    $(".btn-new-answer").on("click", function () {
        var params = {
            "${_csrf.parameterName}": "${_csrf.token}",
            body: $("#newAnswerEditor").code()
        };

        $.ajax({
            url: "/questions/" + _questionId + "/answers",
            method: "post",
            data: params
        }).done(function (data) {
            location.href = "/questions/" + _questionId
        }).error(function (data) {
            alert("에러발생");
            console.log(data);
        });
    });

    $('#newAnswerEditor').summernote({
        height: 300
    });
</script>
</#assign>

<@layout.sidebar title="${question.title}" css=css contents=contents script=script/>