<#import "../_basicLayout.ftl" as layout>
<#assign contents>
<div>
    <form action="/questions/add" method="post">
        <div>
            제목: <input type="text" name="title">
        </div>
        <div>
            본문<br>
            <textarea name="body" rows="10" cols="80"></textarea>
        </div>
        <input type="submit">
        <input type="button" value="취소" onclick="location.href='/questions'">
    </form>
</div>
</#assign>

<@layout.basic title="게시물 작성" contents=contents/>