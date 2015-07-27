<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#import "../_basicLayout.ftl" as layout>
<#assign contents>
<div class="container">
    <div class="col-sm-offset-2 col-sm-10">
        <h1>질문 수정</h1>
    </div>

    <form class="form-horizontal" action="/questions/${question.id}/edit" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="form-group">
            <label class="col-sm-2 control-label">제목</label>

            <div class="col-sm-10">
                <input type="text" name="title" value="${question.title}" placeholder="제목을 입력하세요.">
            </div>
        </div>
        <div>
            <div class="form-group">
                <label class="col-sm-2 control-label">본문</label>

                <div class="col-sm-10">
                    <textarea name="body" rows="10" cols="80"
                              placeholder="본문을 입력하세요.">${question.contents.body}</textarea>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input type="submit" class="btn btn-default" value="확인">
                <input type="button" class="btn btn-default" id="btn-cancel" value="취소">
            </div>
        </div>
    </form>
</div>
</#assign>

<#assign script>
<script>
    $("#btn-cancel").on("click", function () {
        location.href = "/questions/${question.id}";
    });
</script>
</#assign>

<@layout.basic title="수정하기" contents=contents script=script/>