<#import "./_sidebarLayout.ftl" as layout>
<#import "questions_partial.ftl" as partial>

<#assign contents>

<div class="row">
    <a class="btn btn-default" href="/questions/add">질문하기</a>
</div>

<div class="row">
    <ul class="questions list-group">
        <#list questions as question>
            <li class="list-group-item">
                <a href="/questions/${question.id}">
                    <div>${question.title}</div>
                    <div>답변갯수 : ${question.answers?size}</div>
                    <#if question.tags?has_content>
                        <div>태그:
                            <#list question.tags as tag>
                                <span class="label label-primary">#${tag.name}</span>
                            </#list>
                        </div>
                    </#if>
                </a>
            </li>
        </#list>
    </ul>

    <div class="text-center">
        <@partial.pagination currentPage totalPages/>
    </div>
</div>

</#assign><!-- /{contents} -->

<@layout.sidebar title="Questions!" contents=contents />

