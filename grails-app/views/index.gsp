<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>
</head>
<body>
    <content tag="nav">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Application Status <span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a href="#">Environment: ${grails.util.Environment.current.name}</a></li>
                <li><a href="#">App profile: ${grailsApplication.config.grails?.profile}</a></li>
                <li><a href="#">App version:
                    <g:meta name="info.app.version"/></a>
                </li>
                <li role="separator" class="divider"></li>
                <li><a href="#">Grails version:
                    <g:meta name="info.app.grailsVersion"/></a>
                </li>
                <li><a href="#">Groovy version: ${GroovySystem.getVersion()}</a></li>
                <li><a href="#">JVM version: ${System.getProperty('java.version')}</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="#">Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Artefacts <span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a href="#">Controllers: ${grailsApplication.controllerClasses.size()}</a></li>
                <li><a href="#">Domains: ${grailsApplication.domainClasses.size()}</a></li>
                <li><a href="#">Services: ${grailsApplication.serviceClasses.size()}</a></li>
                <li><a href="#">Tag Libraries: ${grailsApplication.tagLibClasses.size()}</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Installed Plugins <span class="caret"></span></a>
            <ul class="dropdown-menu">
                <g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
                    <li><a href="#">${plugin.name} - ${plugin.version}</a></li>
                </g:each>
            </ul>
        </li>
    </content>

    <div id="content" role="main">
        <section class="row colset-2-its">
            <h1>Welcome to Shiro Demo</h1>

            <p>This application demonstrate how to use shiro grails plugin with Spring Security plugin.
                Shiro plugin configurations can be found in "application.groovy" file</p>

            <div id="controllers" role="navigation">
                <div>
                  <p>
                    <user:authenticated>Welcome back <user:loggedInUser property="username" />!</user:authenticated>
                  </p>
                  </div>
                  <p>
                    <g:if test="${flash.message}">
                    ${flash.message}
                  </g:if>
                  </p>
                <h2>Available Controllers:</h2>

                <ul>
                    <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">


                          <g:if test="${c.name=='Login'}">
                            <sec:ifNotLoggedIn>
                            <li class="controller">
                              <g:link controller="login">Login</g:link>
                            </li>
                            </sec:ifNotLoggedIn>
                          </g:if>

                          <g:elseif test="${c.name=='Logout'}">
                          <sec:ifLoggedIn>
                            <li class="controller">
                              <g:link controller="logout">Logout <sec:username/></g:link>
                            </li>
                          </sec:ifLoggedIn>
                          </g:elseif>

                          <g:else>
                            <li class="controller">
                              <g:link controller="${c.logicalPropertyName}">${c.name}</g:link>
                            </li>
                          </g:else>


                    </g:each>
                </ul>

            </div>
        </section>
    </div>

</body>
</html>
