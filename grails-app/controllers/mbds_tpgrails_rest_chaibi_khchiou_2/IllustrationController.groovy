package mbds_tpgrails_rest_chaibi_khchiou_2

import grails.validation.ValidationException
import org.springframework.web.multipart.MultipartFile

import static org.springframework.http.HttpStatus.*

class IllustrationController {

    IllustrationService illustrationService
    ImageUploadService imageUploadService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond illustrationService.list(params), model:[illustrationCount: illustrationService.count()]
    }

    def show(Long id) {
        respond illustrationService.get(id)
    }

    def create() {
        respond new Illustration(params)
    }

    def save(Illustration illustration) {
        if (illustration == null) {
            notFound()
            return
        }

        try {
            illustrationService.save(illustration)
        } catch (ValidationException e) {
            respond illustration.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'illustration.label', default: 'Illustration'), illustration.id])
                redirect illustration
            }
            '*' { respond illustration, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond illustrationService.get(id)
    }

    def editImage()
    {
        def identifiant = params.id
        def file = params.get("file") as MultipartFile

        if(file==null)
        {
            notFound()
            return
        }

        String name = imageUploadService.UploadFile(file.bytes, file.getOriginalFilename())
        def illu = illustrationService.get(identifiant)


        if (illu == null) {
         notFound()
         return
         }

        illu.fileName=name
        illustrationService.save(illu)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'illu.label', default: 'Illustration'), illu.id])
                redirect illu
            }
            '*'{ respond illu, [status: OK] }
        }

    }

    def update(Illustration illustration) {
        if (illustration == null) {
            notFound()
            return
        }

        try {
            illustrationService.save(illustration)
        } catch (ValidationException e) {
            respond illustration.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'illustration.label', default: 'Illustration'), illustration.id])
                redirect illustration
            }
            '*'{ respond illustration, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        illustrationService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'illustration.label', default: 'Illustration'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'illustration.label', default: 'Illustration'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
