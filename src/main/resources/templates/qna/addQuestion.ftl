<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#import "../_basicLayout.ftl" as layout>
<#assign contents>
<div class="container">
    <div class="col-sm-offset-2 col-sm-10">
        <h1>게시물 추가</h1>
    </div>

    <form class="form-horizontal" action="/questions/add" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="form-group">
            <label class="col-sm-2 control-label">제목</label>

            <div class="col-sm-10">
                <input type="text" class="form-control" name="title" placeholder="제목을 입력하세요.">
            </div>
        </div>
        <div>
            <div class="form-group">
                <label class="col-sm-2 control-label">본문</label>

                <div class="col-sm-10">
                    <textarea name="body" class="form-control col-sm-10" rows="10" cols="80"
                              placeholder="본문을 입력하세요."></textarea>
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
        location.href = "/questions"
    });
</script>
</#assign>

<@layout.basic title="게시물 작성" contents=contents script=script/>