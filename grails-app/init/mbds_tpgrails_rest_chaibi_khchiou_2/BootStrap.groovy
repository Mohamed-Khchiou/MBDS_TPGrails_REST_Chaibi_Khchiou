package mbds_tpgrails_rest_chaibi_khchiou_2

import groovy.time.TimeCategory

import java.time.Year

class BootStrap {

    def init = { servletContext ->
       def user = new User(userName: "Florimichon", password: "pwd", thumbnail: new Illustration(fileName: "/assets/apple-touch-icon-retina.png")).save(flush:true)

        (1..5).each {
            user.addToAnnonces(new Annonce(
                    title : "title"+it,
                    description: "cest cool",
                    validTill:  new Date(),
                    state: Boolean.TRUE
            )
                    .addToIllustrations(new Illustration(fileName:  "/assets/apple-touch-icon-retina.png"))
                    .addToIllustrations(new Illustration(fileName:  "/assets/apple-touch-icon-retina.png"))
                    .addToIllustrations(new Illustration(fileName:  "/assets/apple-touch-icon-retina.png"))

            )
            user.save(flush:true)
    }

        def date = new Date()
        use (TimeCategory) {
            date = new Date() + 2.month
        }

        user.addToAnnonces(new Annonce(
                title : "title"+12,
                description: "cest bien",
                validTill:  date,
                state: Boolean.TRUE
        )
                .addToIllustrations(new Illustration(fileName:  "/assets/apple-touch-icon-retina.png"))

        )
        user.save(flush:true)


    }
    def destroy = {
    }
}
