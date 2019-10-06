package mbds_tpgrails_rest_chaibi_khchiou_2

import grails.gorm.services.Service

@Service(User)
interface UserService {

    User get(Serializable id)

    List<User> list(Map args)

    Long count()

    void delete(Serializable id)

    User save(User user)

}