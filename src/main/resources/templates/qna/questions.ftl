<#import "./_sidebarLayout.ftl" as layout>

<#assign contents>
<div>
    <a class="btn btn-default" href="/questions/add">질문하기</a>
</div>

<ul class="questions">
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
</#assign><!-- /{contents} -->

<@layout.sidebar title="Questions" contents=contents/>

