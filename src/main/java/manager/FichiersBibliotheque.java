package manager;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.util.IOUtils;
import dao.impl.impl.FichiersDaoImpl;
import entities.Album;
import entities.Photo;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import com.amazonaws.services.s3.model.PutObjectRequest;

/**
 * Explication des fonctions sur FichiertDaoImpl
 */

public class FichiersBibliotheque {

    private static final String bucketName = "bucketeer-d46ae3f4-c5ad-4baf-94cb-5cb86924f410";
    private static final String keyName = "AKIAJILXQGI6QFLAAIQA";

    private static class FichiersBibliothequeHolder {
        private final static FichiersBibliotheque instance = new FichiersBibliotheque();
    }

    public static FichiersBibliotheque getInstance() {
        return FichiersBibliothequeHolder.instance;
    }

    private FichiersDaoImpl fichiersDao = new FichiersDaoImpl();

    public List<Album> listAlbums() { return fichiersDao.listAlbums(); }

    public Album getAlbum(Integer id) {
        return fichiersDao.getAlbum(id);
    }

    public void addAlbum(String nomAlbum){fichiersDao.addAlbum(nomAlbum);}

    public void supprimerAlbum(Integer id){ fichiersDao.supprimerAlbum(id);}

    public Path getPhotoPicturePath(Integer id){
        Path picturePath = fichiersDao.getPicturePath(id);

        return picturePath;
    }

    public List<Photo> listPhotos(Integer id){return fichiersDao.listPhotos(id);}


    public Photo addPhoto(Photo photo, Part picture) {
        // on génère un nombre aléatoire afin de ne pas avoir de doublons dans la base de données
        String filename = UUID.randomUUID().toString().substring(0,8) + "-" + picture.getSubmittedFileName();
        File file = new File(filename);

        AmazonS3 s3client = new AmazonS3Client(new ProfileCredentialsProvider());

        try {
            System.out.println("Uploading a new object to S3 from a file\n");

            InputStream filecontent = picture.getInputStream();

            OutputStream outputStream = new FileOutputStream(file);
            IOUtils.copy(filecontent, outputStream);
            outputStream.close();

            s3client.putObject(new PutObjectRequest(bucketName, keyName, file));

            System.out.println("Fichier uploadé\n");

        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());

        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fichiersDao.addPhoto(photo, filename);
    }

    public void supprimerPhoto(Integer id){fichiersDao.supprimerPhoto(id);}

}
