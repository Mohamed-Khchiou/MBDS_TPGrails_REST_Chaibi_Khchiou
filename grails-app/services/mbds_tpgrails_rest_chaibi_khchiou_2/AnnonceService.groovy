package mbds_tpgrails_rest_chaibi_khchiou_2

import grails.gorm.services.Service
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

@Service(Annonce)
interface AnnonceService {

    Annonce get(Serializable id)

    List<Annonce> list(Map args)

    Long count()

    void delete(Serializable id)

    Annonce save(Annonce annonce)

}