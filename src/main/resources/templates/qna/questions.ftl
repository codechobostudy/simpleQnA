<!DOCTYPE html>
<html>
<head>
    <title>helloworld</title>
</head>
<body>

<h1>질문리스트</h1>

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