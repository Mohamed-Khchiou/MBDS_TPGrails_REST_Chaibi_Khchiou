package mbds_tpgrails_rest_chaibi_khchiou_2

import grails.rest.RestfulController

import grails.converters.JSON
import grails.converters.XML

import java.text.SimpleDateFormat

class ApiController {

    AnnonceService annonceService
    UserService userService
    IllustrationService illustrationService

    /**
     * Will handle GET / PUT / PATCH / DELETE requests
     */
    def annonce() {
        switch (request.getMethod()) {
            case "GET":
                if (!params.id)
                    return response.status = 400
                def annonceInstance = annonceService.get(params.id)
                if (!annonceInstance)
                    return response.status = 404
                response.withFormat {
                    xml { render annonceInstance as XML }
                    json { render annonceInstance as JSON }
                }
                break
            case "PUT":
                if (request.contentType.contains("json")) {
                    def jsonRequest = request.getJSON()
                    if (!!jsonRequest) {
                        def annonceInstance = annonceService.get(jsonRequest.getAt("id"))
                        if (!annonceInstance) {
                            return response.status = 404
                        } else {
                            if (!jsonRequest.keySet().contains('title') ||
                                    !jsonRequest.keySet().contains('description') ||
                                    !jsonRequest.keySet().contains('dateCreated') ||
                                    !jsonRequest.keySet().contains('validTill') ||
                                    !jsonRequest.keySet().contains('illustrations') ||
                                    !jsonRequest.keySet().contains('state') ||
                                    !jsonRequest.keySet().contains('author')) {

                                return response.status = 400

                            }

                            // Title
                            annonceInstance.title = jsonRequest.getAt("title")

                            // Description
                            annonceInstance.description = jsonRequest.getAt("description")

                            // DateCreated
                            annonceInstance.dateCreated = new SimpleDateFormat("yyyy-MM-dd H:m:s.ms").parse(jsonRequest.getAt("dateCreated"))

                            // ValidTill
                            annonceInstance.validTill = new SimpleDateFormat("yyyy-MM-dd H:m:s.ms").parse(jsonRequest.getAt("validTill"))

                            // Illustrations
                            List<Illustration> illustrations = []
                            jsonRequest.getAt("illustrations").each {
                                def illustrationInstance = illustrationService.get(it)
                                if (illustrationInstance != null) {
                                    illustrations.add(illustrationInstance)
                                }
                            }
                            annonceInstance.illustrations = illustrations

                            // State
                            annonceInstance.state = jsonRequest.getAt("state")

                            // Author
                            def userInstance = userService.get(jsonRequest.getAt("author"))
                            if (userInstance != null)
                                annonceInstance.author = userInstance

                            // Save changes
                            annonceInstance.save(flush: true)
                            return response.status = 200
                        }
                    }
                }
                return response.status = 400
            case "PATCH":
                if (request.contentType.contains("json")) {
                    def jsonRequest = request.getJSON()
                    if (!!jsonRequest) {
                        def annonceInstance = annonceService.get(jsonRequest.getAt("id"))
                        if (!annonceInstance) {
                            return response.status = 404
                        } else {

                            // Title
                            if (jsonRequest.title)
                                annonceInstance.title = jsonRequest.getAt("title") ?: annonceInstance.title

                            // Description
                            if (jsonRequest.description)
                                annonceInstance.description = jsonRequest.getAt("description") ?: annonceInstance.description


                            // DateCreated
                            if (jsonRequest.dateCreated)
                                annonceInstance.dateCreated = new SimpleDateFormat("yyyy-MM-dd H:m:s.ms").parse(jsonRequest.getAt("dateCreated"))


                            // ValidTill
                            if (jsonRequest.validTill)
                                annonceInstance.validTill = new SimpleDateFormat("yyyy-MM-dd H:m:s.ms").parse(jsonRequest.getAt("validTill"))


                            // Illustrations
                            if (jsonRequest.illustrations) {
                                List<Illustration> illustrations = []
                                jsonRequest.getAt("illustrations").each {
                                    def illustrationInstance = illustrationService.get(it)
                                    if (illustrationInstance != null) {
                                        illustrations.add(illustrationInstance)
                                    }
                                }
                                annonceInstance.illustrations = illustrations ?: annonceInstance.illustrations
                            }


                            // State
                            if (jsonRequest.state) {
                                annonceInstance.state = jsonRequest.getAt("state") ?: annonceInstance.state
                            }

                            // Author
                            if (jsonRequest.author) {
                                def userInstance = userService.get(jsonRequest.getAt("author"))
                                if (userInstance != null)
                                    annonceInstance.author = userInstance
                            }


                            // Save changes
                            annonceInstance.save(flush: true)
                            return response.status = 200
                        }
                    }
                }
                return response.status = 400
            case "DELETE":
                if (!params.id)
                    return response.status = 400
                def annonceInstance = annonceService.get(params.id)
                if (!annonceInstance)
                    return response.status = 404

                annonceInstance.delete(flush: true)
                return response.status = 200
            default:
                return response.status = 405
                break
        }
        return response.status = 406
    }

    /**
     * Will handle GET / POST requests
     */
    def annonces() {
        switch (request.getMethod()) {
            case "GET":
                def annoncesInstance = annonceService.list()
                if (!annoncesInstance)
                    return response.status = 404
                response.withFormat {
                    xml { render annoncesInstance as XML }
                    json { render annoncesInstance as JSON }
                }
                break
            case "POST":
                if (request.contentType.contains("json")) {
                    def newAnnonceInstance = request.getJSON()
                    if (!!newAnnonceInstance) {
                        def obj = new Annonce(newAnnonceInstance)
                        if (!!obj) {
                            obj.save(flush: true)
                            return response.status = 201
                        }
                    }
                }
                return response.status = 400

                break
            default:
                return response.status = 405
                break
        }
        return response.status = 406
    }
}