Bonjour Monsieur, 

Vous trouverez çi joint l'ensemble du projet Grails pour notre groupe Khchiou_Chaibi.

Concernant le projet en lui même quelques informations : 

- Api Rest, l'ensemble des methodes ce situe au niveau de l'ApiController, et le fichier de requete (Postman) est present dans ce dossier fichier "API-Request.postman_collection.Json"

- bonus Ajax, non effectuer 

- bonus Cron, pour le Cron nous nous somme retrouver confronter a un problème en effet nous n'arrivons pas appeler notre service de base de données (Annonce service) car lors de 
compilation du projet etant données que notre cron doit etre compile de maniere static, l'utiliser d'un autre service qui n'est pas encore instancier gene le compilateur.
Vous pourrez retrouver notre code dans CleanDataCronService. Si vous avez une idée pour solutionner le problème n'hesitez pas a nous en faire part car nous n'avons rien trouvé.
Nous disponsons cependant d'un CRON windows (script) faisant le même travail que celui de Grails dont le script est disponible dans ScriptCleanData.ps1

Merci pour votre temps 

Cordialement,

Chaibi Khchiou

