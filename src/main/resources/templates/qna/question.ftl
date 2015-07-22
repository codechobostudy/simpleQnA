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
        <div class="answer" data-answer-id="${answer.id}">
            <div>작성일: ${answer.contents.createDate}</div>
            <div>
                <textarea class="answer-body">${answer.contents.body}</textarea>
            </div>
            <div>
                <button class="btn-edit-answer">수정</button>
                <button class="btn-delete-answer">삭제</button>
            </div>
        </div>
    </#list>
    </div>

    <div class="new-answer">
        <div>신규 댓글</div>
        <div>
            <textarea class="new-answer-body"></textarea>
        </div>
        <div>
            <button class="btn-new-answer">추가</button>
        </div>
    </div>
</div>

<script src="//code.jquery.com/jquery-2.1.4.min.js"></script>
<script>
    $(".btn-new-answer").on("click", function () {
        var params = {
            body: $(".new-answer-body").val()
        };

        $.ajax({
            url: "/questions/${question.id}/answers",
            method: "post",
            data: params
        }).done(function (data) {
            location.href = "/questions/${question.id}"
        }).error(function (data) {
            alert("에러발생");
            console.log(data);
        });
    });

    $(".btn-edit-answer").on("click", function () {
        var answerId = $(this).closest(".answer").attr("data-answer-id");

        var params = {
            body: $(this).closest(".answer").find(".answer-body").val()
        };

        $.ajax({
            url: "/questions/${question.id}/answers/" + answerId + "/edit",
            method: "post",
            data: params
        }).done(function () {
            location.href = "/questions/${question.id}"
        }).error(function (data) {
            alert("에러발생");
            console.log(data);
        });
    });

    $(".btn-delete-answer").on("click", function () {
        var answerId = $(this).closest(".answer").attr("data-answer-id");

        $.ajax({
            url: "/questions/${question.id}/answers/" + answerId,
            method: "delete"
        }).done(function () {
            location.href = "/questions/${question.id}"
        }).error(function (data) {
            alert("에러발생");
            console.log(data);
        });
    });

</script>
</body>
</html>