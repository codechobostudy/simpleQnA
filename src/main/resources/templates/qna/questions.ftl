<#import "./_sidebarLayout.ftl" as layout>

<#--pagination-->
<#function max x y>
    <#if (x<y)><#return y><#else><#return x></#if>
</#function>
<#function min x y>
    <#if (x<y)><#return x><#else><#return y></#if>
</#function>
<#macro pages questionPage p>
    <#assign size = questionPage?size>
    <#if (p<=5)> <#-- p among first 5 pages -->
        <#assign interval = 1..(min(5,size))>
    <#elseif ((size-p)<5)> <#-- p among last 5 pages -->
        <#assign interval = (max(1,(size-4)))..size >
    <#else>
        <#assign interval = (p-2)..(p+2)>
    </#if>
    <#if !(interval?seq_contains(1))>
    <a class="btn-page" href="/questions?page=1">1</a> ... <#rt>
    </#if>
    <#list interval as page>
        <#if page=p>
        <${page}> <#t>
        <#else>
        <a class="btn-page" href="/questions?page=${page}">${page}</a> <#t>
        </#if>
    </#list>
    <#if !(interval?seq_contains(size))>
    ... ${size}<#lt>
    </#if>
</#macro>
<#--pagination end-->

<#assign currentPage = "${RequestParameters.page?default(1)}"?number >

<#assign contents>

    <@pages 1..questionPage currentPage/>

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

<@layout.sidebar title="Questions!" contents=contents />

