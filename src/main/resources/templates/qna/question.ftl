<!DOCTYPE html>
<html>
<head>
    <title>helloworld</title>
</head>
<body>

<div>
    <h1>${question.title}</h1>

    <div>날짜: ${question.contents.date}</div>
    <div>본문 : ${question.contents.body}</div>
</div>
<ul>
<#list question.answers as answer>
    <li>
        <div>${answer.contents.body}</div>
    </li>
</#list>
</ul>

</body>
</html>