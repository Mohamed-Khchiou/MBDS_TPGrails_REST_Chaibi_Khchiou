<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'annonce.label', default: 'Annonce')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<a href="#create-annonce" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="create-annonce" class="content scaffold-create" role="main">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
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
    <g:uploadForm  resource="${this.annonce}" name="uploadFeaturedImage" method="POST" action="save">
        <input type="hidden" name="_method" value="POST" id="_method">
        <input type="hidden" name="version" value="0" id="version">
        <fieldset class="form">
            <div class="fieldcontain required">
                <label for="title">Title
                    <span class="required-indicator">*</span>
                </label><input type="text" name="title" value="" required="" id="title">
            </div>

            <div class="fieldcontain required">
                <label for="description">Description
                    <span class="required-indicator">*</span>
                </label><input type="text" name="description" value="" required=""
                               id="description">
            </div>

            <div class="fieldcontain required">
                <label>Valid Till
                    <span class="required-indicator">*</span>
                </label>
                <g:datePicker name="validTill" value="${validTill}"  precision="day" relativeYears="[-2..7]"/>
            </div>

            <div class="fieldcontain required">
                <label>Illustrations<span class="required-indicator">*</span></label>
                <ul>
                    <div class="property-value" aria-labelledby="thumbnail-label">
                            <div  class="fieldcontain required" aria-labelledby="thumbnail-label">
                             <input type="file" name="file_1" required="required"/>
                            </div>
                            <div  class="fieldcontain required" aria-labelledby="thumbnail-label">
                           <input type="file" name="file_2" required="required"/>
                            </div>
                            <div  class="fieldcontain required" aria-labelledby="thumbnail-label">
                          <input type="file" name="file_3" required="required"/>
                            </div>
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
            <g:submitButton name="create" class="save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:uploadForm>
</div>
</body>
</html>
