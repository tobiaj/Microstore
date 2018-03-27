package fileservice.database;

import fileservice.FileMetadata;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class DatabaseHandler {
    private static EntityManagerFactory emFactory;

    public String storeKeys(FileMetadata fileMetadata){

        FileEntity fileEntity = new FileEntity(fileMetadata.getName(), fileMetadata.getEncryptionkey(), fileMetadata.getFile().getName(), fileMetadata.getUUID());
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();


        try {
            em.getTransaction()
                    .begin();
            em.persist(fileEntity);
            em.getTransaction()
                    .commit();
            em.flush();
            em.close();

            return "Transaction Complete";
        }
        catch (PersistenceException pe){

            return "JPA ERROR";
        }

    }


    public enum PersistenceManager {
        INSTANCE;
        private PersistenceManager() {

            emFactory = Persistence.createEntityManagerFactory("fileservice");
        }
        public EntityManager getEntityManager() {
            return emFactory.createEntityManager();
        }
        public void close() {
            emFactory.close();
        }

    }
}
