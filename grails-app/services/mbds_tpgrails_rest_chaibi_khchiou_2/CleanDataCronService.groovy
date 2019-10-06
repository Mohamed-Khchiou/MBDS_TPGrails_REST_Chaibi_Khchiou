package mbds_tpgrails_rest_chaibi_khchiou_2

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.springframework.scheduling.annotation.Scheduled

@Slf4j
// @CompileStatic fait planter l'application

class CleanDataCronService {

    boolean lazyInit = false
    AnnonceService annonceService



    @Scheduled(cron = "0 0/5 * * * ?") //toutes les 5 min
    void executeEveryTen() {

        def annonces = annonceService.list()
        /**
         * Maybe delete  the annonces entity
         */
        println "Simple Job every 5 min :"
        annonces.each {
            if (it.validTill < new Date()) {
                it.state = Boolean.FALSE
                annonceService.save(it);
            }
        }
    }
}
