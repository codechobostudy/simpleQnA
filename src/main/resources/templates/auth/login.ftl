<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="error" type="java.util.Optional<String>" -->
<#import "../_basicLayout.ftl" as layout>

<#assign style>
<style>
    body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #eee;
    }

    .form-login {
        max-width: 330px;
        padding: 15px;
        margin: 0 auto;
    }

    .form-login .form-signin-heading,
    .form-login .checkbox {
        margin-bottom: 10px;
    }

    .form-login .checkbox {
        font-weight: normal;
    }

    .form-login .form-control {
        position: relative;
        height: auto;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        padding: 10px;
        font-size: 16px;
    }

    .form-login .form-control:focus {
        z-index: 2;
    }

    .form-login input[type="email"] {
        margin-bottom: -1px;
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
    }

    .form-login input[type="password"] {
        margin-bottom: 10px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
    }
</style>
</#assign>

<#assign contents>
<div class="container">

    <form class="form-login" role="form" action="/login" method="post">
        <h1 class="form-signin-heading">Please Login</h1>

        <div class="alert alert-info" role="alert">admin@localhost / admin 으로 접속하세요!!</div>

        <#if error.isPresent()>
            <div class="alert alert-warning" role="alert">이메일이나 비밀번호가 일치하지 않습니다.</div>
        </#if>
        <div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <label for="email" class="sr-only">Email address</label>
            <input type="email" class="form-control" name="email" placeholder="Email" required autofocus/>

            <label for="password" class="sr-only">Password</label>
            <input type="password" class="form-control" name="password" placeholder="Password" required/>

            <div class="checkbox">
                <label>
                    <input type="checkbox" value="remember-me"> Remember me
                </label>
            </div>
        </div>

        <div>
            <div>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
            </div>
            <div class="text-center">
                or
            </div>
            <div class="text-center">
                <strong>
                    <a href="/user/create">회원가입</a>
                    /
                    <a href="/user/findId">아이디 찾기</a>
                </strong>
            </div>
        </div>
    </form>

</div><!-- /.container --!>

</#assign>

<@layout.basic title="Login" css=style contents=contents/>