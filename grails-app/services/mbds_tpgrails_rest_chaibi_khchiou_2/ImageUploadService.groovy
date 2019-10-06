package mbds_tpgrails_rest_chaibi_khchiou_2

import grails.gorm.transactions.Transactional
import org.apache.commons.io.FileUtils

@Transactional
class ImageUploadService {

    String UploadFile( byte[] contentImage, String filename) {
        String name=""
        String[] split = filename.split("\\.")
        if(split?.length>1)
        {
            split[0]+="_"+UUID.randomUUID().toString()
            name = split[0]+"."+split[1]
            FileUtils.writeByteArrayToFile(new File("grails-app/assets/images/"+name), contentImage)
            name="/assets/"+name
        }

        return name
    }

    Illustration changeIllustration(Illustration illustration,String name)
    {
        if(!illustration.fileName.contains("apple-touch-icon-retina.png")) {
            String[] split = illustration.fileName.split("/")
            // normalement passer par des fichier de config
            File f = new File("E:/Projet/MBDS_TPGrails_REST_Chaibi_Khchiou_2/grails-app/assets/images/"+split[2])
            def response = f.delete()
        }
            illustration.delete()
            return new Illustration(fileName: name)
    }
}
