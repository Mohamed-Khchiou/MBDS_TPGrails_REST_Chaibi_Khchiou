<%@ page import="mbds_tpgrails_rest_chaibi_khchiou_2.Illustration" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<a href="#edit-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                           default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="edit-user" class="content scaffold-edit" role="main">
    <h1><g:message code="default.edit.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.user}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.user}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:uploadForm resource="${this.user}" name="uploadFeaturedImage" method="PUT" action="update">
        <g:hiddenField name="version" value="${this.user?.version}"/>
        <g:hiddenField name="id" value="${this.user?.id}"/>
        <fieldset class="form">
            <fieldset class="form">
                <div class='fieldcontain required'>
                    <label for='userName'>User Name
                        <span class='required-indicator'>*</span>
                    </label><input type="text" name="userName" value="${this.user.userName}" required="" maxlength="20" id="userName" />
                        </div><div class='fieldcontain required'>
        <label for='password'>Password
            <span class='required-indicator'>*</span>
        </label><input type="password" name="password" required="" value="" id="password"/>
    </div>

        <div class='fieldcontain required'>
            <label>Actual Thumbnail
            </label>
            <g:img uri="${this.user.thumbnail.fileName}" width="10%" height="10%" />
        </div>


        <div class='fieldcontain'>
            <label>Path : ${this.user.thumbnail.fileName}</label>
        </div>

        <div class='fieldcontain'>
            <label for='thumbnail'>Import Illustration</label>
            <input type="file" name="file" />
        </div>

        <div class='fieldcontain'>
            <label for='annonces'>Annonces</label>

            <div class='property-value' aria-labelledby="annonces-label">
                <ul>
                    <g:each in="${this.user.annonces}" var="annonce">
                        <li><g:link controller="annonce" action="show" id="${annonce.id}">${annonce.title}</g:link></li>
                    </g:each>
                </ul></div>
        </div>

        </fieldset>
        <fieldset class="buttons">
            <input class="save" type="submit"
                   value="${message(code: 'default.button.update.label', default: 'Update')}"/>
        </fieldset>
    </g:uploadForm>
</div>
</body>
</html>
