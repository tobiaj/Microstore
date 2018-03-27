package fileservice.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fileholder")
public class FileEntity {

    private String NAME;
    private String ENCRYPTIONKEY;
    private String FILENAME;
    @GeneratedValue(generator = "increment")
    private int FILE_ID;
    @Id
    private String UUID;

    public FileEntity(String name, String encryptionkey, String filename, String uuid){
        this.NAME = name;
        this.ENCRYPTIONKEY = encryptionkey;
        this.FILENAME = filename;
        this.UUID = uuid;
    }


    public void setFILENAME(String FILENAME) {
        this.FILENAME = FILENAME;
    }

    public void setENCRYPTIONKEY(String ENCRYPTIONKEY) {
        this.ENCRYPTIONKEY = ENCRYPTIONKEY;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }
}
