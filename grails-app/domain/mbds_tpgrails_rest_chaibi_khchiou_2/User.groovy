package mbds_tpgrails_rest_chaibi_khchiou_2

class User {

    String userName
    String password
    Date dateCreated
    Date lastUpdated
    Illustration thumbnail

    static hasMany = [annonces:Annonce]
   // static hasOne = [illustration:Illustration] possible aussi car

    static constraints = {
        userName nullable: false, blank: false, size: 5..20
        password password:true, nullable: false, blank: false
        thumbnail nullable:true
        annonces nullable: true
    }

    @Override
    String toString()
    {
        return userName
    }
}
