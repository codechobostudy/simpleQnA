<!DOCTYPE html>
<html>
<head>
    <title>helloworld</title>
</head>
<body>

<div>
    <h1>${question.title}</h1>

    <div>날짜: ${question.contents.createDate}</div>
    <div>본문 : ${question.contents.body}</div>
    <div>
        <button onclick="location.href='/questions/${question.id}/edit'">수정</button>
        <button onclick="location.href='/questions/${question.id}/delete'">삭제</button>
    </div>
</div>

<hr>

<div>
    <div class="answers">
    <#list question.answers as answer>
        <div>
            <div>${answer.contents.body}</div>
            <div>작성일: ${answer.contetns.createDate}</div>
        </div>
    </#list>
    </div>

</div>

</body>
</html>