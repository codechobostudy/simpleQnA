<!DOCTYPE html>
<html>
<head>
    <title>helloworld</title>
</head>
<body>

<h1>질문리스트</h1>

<div>
    <button onclick="location.href='/questions/add'">추가</button>
</div>
<ul>
<#list questions as question>
    <li>
        <div>
            <a href="/questions/${question.id}">${question.title}</a>
        </div>
        <div>
            answer : ${question.answers?size}
        </div>
    </li>
</#list>
</ul>

</body>
</html>