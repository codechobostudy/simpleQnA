<#import "_basicLayout.ftl" as layout>
<#assign contents>

<div class="container">

    <h1>Simple QnA</h1>

    <ul>
        <li><a href="/login">로그인</a></li>
        <li><a href="/user/create">유저생성</a></li>
        <li><a href="/questions">질문리스트</a></li>
    </ul>

</div>

</#assign>
<@layout.basic title="Navigation" contents=contents/>