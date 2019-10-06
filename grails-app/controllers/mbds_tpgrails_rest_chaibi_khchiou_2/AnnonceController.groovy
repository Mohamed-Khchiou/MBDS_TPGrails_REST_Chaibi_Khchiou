package mbds_tpgrails_rest_chaibi_khchiou_2

import grails.validation.ValidationException
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import javafx.scene.control.DatePicker
import org.springframework.web.multipart.MultipartFile

import java.text.SimpleDateFormat

import static org.springframework.http.HttpStatus.*


class AnnonceController {

    AnnonceService annonceService
    ImageUploadService imageUploadService
    CleanDataCronService cleanDataCronService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond annonceService.list(params), model: [annonceCount: annonceService.count()]
    }

    def cleanData(String mdp) {
        if (mdp?.equals("jkds82376438_!hjdrbgieh12")) {
            def annonces = annonceService.list()
            /**
             * Maybe delete  the annonces entity
             */
            annonces.each {
                if (it.validTill < new Date()) {
                    it.state = Boolean.FALSE
                    annonceService.save(it);
                }
            }
        }
    }

    def show(Long id) {
        respond annonceService.get(id)
    }

    def create() {
        respond new Annonce(params)
    }

    def save(Annonce annonce) {
        if (annonce == null) {
            notFound()
            return
        }

        MultipartFile file

        (1..3).each {
            file = params.get("file_" + it) as MultipartFile
            if (file != null && file.originalFilename != "") {
                def name = imageUploadService.UploadFile(file.bytes, file.originalFilename)
                annonce.addToIllustrations(new Illustration(fileName: name))
            }
        }
        def pattern = "yyyy-MM-dd"
        annonce.validTill = new SimpleDateFormat(pattern).parse(params.validTill_year + "-" + params.validTill_month + "-" + params.validTill_day)
        try {
            annonceService.save(annonce)
        } catch (ValidationException e) {
            respond annonce.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'annonce.label', default: 'Annonce'), annonce.id])
                redirect annonce
            }
            '*' { respond annonce, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond annonceService.get(id)
    }

    def update(Annonce annonce) {
        if (annonce == null) {
            notFound()
            return
        }
        MultipartFile file

        (0..2).each {
            file = params.get("file_" + it) as MultipartFile
            if (file != null && file.originalFilename != "") {
                def illu = annonce.illustrations[it - 1]
                annonce.illustrations.remove(illu)
                def name = imageUploadService.UploadFile(file.bytes, file.originalFilename)
                illu = imageUploadService.changeIllustration(illu, name)
                annonce.addToIllustrations(illu)
            }
        }

        def pattern = "yyyy-MM-dd"
        annonce.validTill = new SimpleDateFormat(pattern).parse(params.validTill_year + "-" + params.validTill_month + "-" + params.validTill_day)

        try {
            annonceService.save(annonce)
        } catch (ValidationException e) {
            respond annonce.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'annonce.label', default: 'Annonce'), annonce.id])
                redirect annonce
            }
            '*' { respond annonce, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        annonceService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'annonce.label', default: 'Annonce'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'annonce.label', default: 'Annonce'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
