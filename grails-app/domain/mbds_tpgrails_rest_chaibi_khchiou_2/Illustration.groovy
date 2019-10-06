package mbds_tpgrails_rest_chaibi_khchiou_2

class Illustration {

    String fileName


    static constraints = {
        fileName blank: false, nullable: false
    }

    @Override
    String toString()
    {
        return fileName;
    }

}
