package mbds_tpgrails_rest_chaibi_khchiou_2

import grails.validation.ValidationException
import org.springframework.web.multipart.MultipartFile

import static org.springframework.http.HttpStatus.*

class UserController {

    UserService userService
    IllustrationService illustrationService
    ImageUploadService imageUploadService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userService.list(params), model:[userCount: userService.count()]
    }

    def show(Long id) {
        respond userService.get(id)
    }

    def create() {
        respond new User(params)
    }

    def save(User user) {

        def file = params.get("file") as MultipartFile

        if (user == null) {
            notFound()
            return
        }

        if(file!=null && file.originalFilename!="")
        {
            def name=imageUploadService.UploadFile(file.bytes, file.originalFilename)
            user.thumbnail= new Illustration(fileName: name)
        }

        try {
            user =userService.save(user)
        } catch (ValidationException e) {
            respond user.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*' { respond user, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond userService.get(id)
    }

    def update(User user) {
        def file = params.get("file") as MultipartFile

        if (user == null) {
            notFound()
            return
        }

        if(file!=null && file.originalFilename!="")
        {
            def name=imageUploadService.UploadFile(file.bytes, file.originalFilename)
            def illustration = illustrationService.get(user.thumbnail.id)
            user.thumbnail=null
            illustration = imageUploadService.changeIllustration(illustration,name)
            illustration.save(flush:true)
            user.thumbnail = illustration
        }

        try {
            userService.save(user)
        } catch (ValidationException e) {
            respond user.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*'{ respond user, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
