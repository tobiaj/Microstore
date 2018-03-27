package fileservice;

import fileservice.database.DatabaseHandler;
import fileservice.encryption.AES;
import fileservice.rabbitmq.RabbitMQSend;
import fileservice.storage.MinioStorage;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.util.concurrent.TimeoutException;

public class FileService implements FileServiceInterface {

    private MinioStorage minioStorage;
    private AES aes;
    private DatabaseHandler databaseHandler;
    private RabbitMQSend rabbitMQSend;

    public FileService(){
        this.minioStorage = new MinioStorage();
        this.aes = new AES();
        this.databaseHandler = new DatabaseHandler();
        this.rabbitMQSend = new RabbitMQSend();
    }

    @Override
    public String storeFile(String nameID, MultipartFile multipartFile) {
        File file = extractFile(multipartFile);

        FileMetadata fileMetadata = new FileMetadata(nameID);
        fileMetadata.setFile(file);
        fileMetadata.setFileName(file.getName());

        try {

            aes.processEncrypt(fileMetadata);
            System.out.println("Done encryption");

            if (minioStorage.uploadToStorage(fileMetadata)){

                databaseHandler.storeKeys(fileMetadata);

                rabbitMQSend.sendMessageToQueue(fileMetadata);

                return "Done upload";
            }


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidParameterSpecException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        return "Failed";
    }

    private File extractFile(MultipartFile file)
    {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return convFile;
    }
}
