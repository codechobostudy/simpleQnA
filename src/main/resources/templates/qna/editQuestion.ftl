<!DOCTYPE html>
<html>
<head>
    <title>helloworld</title>
</head>
<body>

<div>
    <form action="/questions/${question.id}/edit" method="post">
        <div>
            제목: <input type="text" name="title" value="${question.title}">
        </div>
        <div>
            본문<br>
            <textarea name="body" rows="10" cols="80">${question.contents.body}</textarea>
        </div>
        <input type="submit" value="확인">
        <input type="button" value="취소" onclick="location.href='/questions/${question.id}'">
    </form>
</div>

</body>
</html>