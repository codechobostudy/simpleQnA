<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#import "../_basicLayout.ftl" as layout>

<#assign css>
<link href="//cdnjs.cloudflare.com/ajax/libs/summernote/0.6.15/summernote.min.css" rel="stylesheet">
</#assign>

<#assign contents>
<div class="container">
    <div class="col-sm-offset-2 col-sm-10">
        <h1>게시물 추가</h1>
    </div>

    <form class="form-horizontal" id="newQuestionForm" action="/questions/add" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="body"/>

        <div class="form-group">
            <label class="col-sm-2 control-label">태그</label>

            <div class="tags col-sm-10">
                <div>
                    <button class="btn-add-tag btn btn-default">tag 추가</button>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label">제목</label>

            <div class="col-sm-10">
                <input type="text" class="form-control" name="title" placeholder="제목을 입력하세요.">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">본문</label>

            <div class="col-sm-10">
                <div id="contentsBodyEditor"></div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input type="submit" class="btn btn-default" id="btn-confirm" value="확인">
                <input type="button" class="btn btn-default" id="btn-cancel" value="취소">
            </div>
        </div>
    </form>
</div>
</#assign>

<#assign script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.6.15/summernote.js"></script>
<script>
    $('#contentsBodyEditor').summernote({
        height: 300
    });

    $("#btn-cancel").on("click", function () {
        location.href = "/questions"
    });

    $("#newQuestionForm").submit(function () {
        var body = $('#contentsBodyEditor').code();
        $("[name=body]").val(body);
    });

    $('.btn-add-tag').on('click', function () {
        $(".tags").append(
                '<div class="tag-wrapper">' +
                '  <input type="text" name="tags"/>' +
                '  <button class="btn-remove-tag btn btn-default">삭제</button>' +
                '</div>'
        );
        return false;
    });

    $(".tags").on("click", "button.btn-remove-tag", function () {
        $(this).parents('.tag-wrapper').remove();
        return false;
    });

</script>
</#assign>

<@layout.basic title="게시물 작성" contents=contents css=css script=script/>