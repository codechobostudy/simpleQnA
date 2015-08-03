<#macro nav >
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">CODECHOBO</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">게시판 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/questions">질문게시판</a></li>
                        <li><a href="#">Another action</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">Another action</a></li>
                    </ul>
                </li>
            </ul>
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <#if currentUser??>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        ${currentUser.user.getEmail()}
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="">내 정보</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="/logout">로그아웃</a></li>
                    </ul>
                </li>
                <#else>
                    <li><a href="/login">로그인</a></li>
                </#if>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
</#macro>