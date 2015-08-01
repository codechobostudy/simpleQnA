<#import "./_sidebarLayout.ftl" as layout>
<#import "questions_partial.ftl" as partial>

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

<div class="text-center">
    <@partial.pagination currentPage totalPages/>
</div>

</#assign><!-- /{contents} -->

<@layout.sidebar title="Questions!" contents=contents />

