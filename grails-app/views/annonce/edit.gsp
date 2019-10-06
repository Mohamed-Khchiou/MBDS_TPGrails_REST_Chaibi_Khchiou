<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'annonce.label', default: 'Annonce')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<a href="#edit-annonce" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                              default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="edit-annonce" class="content scaffold-edit" role="main">
    <h1><g:message code="default.edit.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.annonce}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.annonce}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
%{--    <g:form resource="${this.annonce}" method="PUT">--}%
%{--        <g:hiddenField name="version" value="${this.annonce?.version}"/>--}%
%{--        <fieldset class="form">--}%
%{--            <f:all bean="annonce"/>--}%
%{--        </fieldset>--}%
%{--        <fieldset class="buttons">--}%
%{--            <input class="save" type="submit"--}%
%{--                   value="${message(code: 'default.button.update.label', default: 'Update')}"/>--}%
%{--        </fieldset>--}%
%{--    </g:form>--}%

    <g:uploadForm  resource="${this.annonce}" name="uploadFeaturedImage" method="PUT" action="update">
        <input type="hidden" name="_method" value="PUT" id="_method">
        <input type="hidden" name="version" value="${version}" id="version">
        <fieldset class="form">
            <div class="fieldcontain required">
                <label for="title">Title
                    <span class="required-indicator">*</span>
                </label><input type="text" name="title" value="${annonce.title}" required="" id="title">
            </div>

            <div class="fieldcontain required">
                <label for="description">Description
                    <span class="required-indicator">*</span>
                </label><input type="text" name="description" value="${annonce.description}" required=""
                               id="description">
            </div>

            <div class="fieldcontain required">
                <label >Valid Till
                    <span class="required-indicator">*</span>
                </label>
                <g:datePicker name="validTill" value="${new Date()}" precision="day" relativeYears="[-2..7]"/>
            </div>

            <div class="fieldcontain required">
                <label >Illustrations<span class="required-indicator">*</span></label>
                <ul>
                    <div class="property-value" aria-labelledby="thumbnail-label">
                        <g:each status="i" in="${annonce.illustrations}" var="illustration">

                                <li>
                                    <img src="${illustration.fileName}" width="10%" height="10%">
                                </li>
                            <input type="file" name="file_${i}" />
                        </g:each>
                    </div>
                </ul>
            </div>

            <div class="fieldcontain">
                <label for="state">Enabled</label><input type="hidden" name="_state">
                <input type="checkbox" name="state" checked="checked" id="state">
            </div>

            <div class="fieldcontain required">
                <label for="author">Author
                    <span class="required-indicator">*</span>
                </label><select name="author.id" required="" id="author">
                <g:each in="${mbds_tpgrails_rest_chaibi_khchiou_2.User.list()}" var="user">
                    <option value="${user.id}">${user.userName}</option>
                </g:each>
            </select>
            </div>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="update" class="save"
                            value="${message(code: 'default.button.update.label', default: 'Update')}"/>
        </fieldset>
    </g:uploadForm>
</div>
</body>
</html>
