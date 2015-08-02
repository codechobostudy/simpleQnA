<#import "../_basicLayout.ftl" as layout>

<#macro sidebar title="" css="" script="" contents="">

    <#assign internalStyle>
    <style>
        body {
            margin-top: 50px;
        }
    </style>

    ${css}
    </#assign>

    <#assign internalContents>
    <div class="container">
        <div class="row">

            <div class="col-sm-10">
            ${contents}
            </div>

            <div class="side-bar col-sm-2">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search for...">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button">Go!</button>
                </span>
                </div>
                <!-- /.input-group -->

                <hr>
                <div class="tag-wrapper">
                    <div>
                        인기 태그 리스트
                    </div>
                    <ul class="tagList">
                        <li>Java</li>
                        <li>Ruby</li>
                        <li>Javascript</li>
                        <li>Ruby on rails</li>
                        <li>Spring</li>
                        <li>전자정부프레임워크</li>
                    </ul>
                </div>
            </div>
            <!--/.side-bar -->

        </div>
        <!-- /.row -->
    </div><!-- /.container -->
    </#assign>

    <@layout.basic title=title css=internalStyle script=script contents=internalContents/>
</#macro>