package mbds_tpgrails_rest_chaibi_khchiou_2

class Annonce {

    String title
    String description
    Date dateCreated
    Date validTill
    Boolean state

    static belongsTo = [author:User]
    static hasMany = [illustrations: Illustration]

    static constraints = {
        title blank:false, nullable: false
        description blank: false, nullable: false
        validTill nullable: false
        illustrations nullable:true
    }

    @Override
    String toString()
    {
        return title
    }
}
