<#macro pagination currentPage totalPage paginationSize=9>

    <#if (currentPage <= paginationSize)>
        <#if paginationSize<totalPage>
            <#assign interval = 1..paginationSize>
        <#else>
            <#assign interval = 1..totalPage>
        </#if>
    <#elseif ((totalPage - currentPage) < paginationSize)>
        <#if (1 >= (totalPage - paginationSize))>
            <#assign interval = 1..totalPage>
        <#else>
            <#assign interval = (totalPage-paginationSize+1)..totalPage>
        </#if>
    <#else>
        <#assign interval = (currentPage-(paginationSize-1)/2)..(currentPage+(paginationSize-1)/2)>
    </#if>

<nav>
    <ul class="pagination">

        <#if currentPage == 1>
            <li class="disabled">
                <a href="#" aria-label="Previous">
                    <span aria-hidden="true">&lt;Prev</span>
                </a>
            </li>
        <#else>
            <li>
                <a href="/questions?page=${currentPage - 1}" aria-label="Previous">
                    <span aria-hidden="true">&lt;Prev</span>
                </a>
            </li>
        </#if>

        <#if !(interval?seq_contains(1))>
            <li><a href="/questions?page=1">1</a></li>
            <li><a href="#" class="disabled">...</a></li>
        </#if>
        <#list interval as page>
            <#if page == currentPage>
                <li class="active"><a href="#">${page}</a></li>
            <#else >
                <li><a href="/questions?page=${page}">${page}</a></li>
            </#if>
        </#list>
        <#if !(interval?seq_contains(totalPage))>
            <li><a href="#" class="disabled">...</a></li>
            <li><a href="/questions?page=${totalPage}">${totalPage}</a></li>
        </#if>


        <#if currentPage != totalPage>
            <li>
                <a href="/questions?page=${currentPage + 1}" aria-label="Previous">
                    <span aria-hidden="true">Next&gt;</span>
                </a>
            </li>
        <#else>
            <li class="disabled">
                <a href="#" aria-label="Previous">
                    <span aria-hidden="true">Next&gt;</span>
                </a>
            </li>
        </#if>

    </ul>
</nav>
</#macro>
<#--pagination end-->