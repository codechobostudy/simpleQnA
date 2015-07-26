<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="form" type="io.codechobostudy.qna.dto.auth.UserCreateForm" -->
<#import "/spring.ftl" as spring>
<#import "../_basicLayout.ftl" as layout>


<#assign contents>
<div class="container">

    <div class="col-sm-offset-2 col-sm-10">
        <h1>회원가입</h1>
    </div>
    <div>
    <form role="form" class="form-horizontal" name="form" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <@spring.bind "form" />
        <#if spring.status.error>
            <ul>
                <#list spring.status.errorMessages as error>
                    <li>${error}</li>
                </#list>
            </ul>
        </#if>

        <div class="form-group">
            <label for="email" class="control-label col-sm-2">Email address</label>

            <div class="col-sm-10">
                <input type="email" name="email" id="email" value="${form.email}" required autofocus/>
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="control-label col-sm-2">Password</label>

            <div class="col-sm-10">
                <input type="password" name="password" id="password" required/>
            </div>
        </div>
        <div class="form-group">
            <label for="passwordRepeated" class="control-label col-sm-2">Repeat</label>

            <div class="col-sm-10">
                <input type="password" name="passwordRepeated" id="passwordRepeated" required/>
            </div>
        </div>


        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-lg btn-primary">회원가입</button>
            </div>
        </div>

    </form>

</#assign>
</div> <!--/.container -->

<@layout.basic title="회원가입" contents=contents/>