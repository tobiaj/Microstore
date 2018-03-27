package fileservice.rabbitmq;

public class Message {

    private String nameID;
    private String encryptionkey;
    private String fileName;
    private String UUID;

    public Message() {

    }


    public Message(String nameID, String encryptionkey, String fileName, String uuid) {
        this.nameID = nameID;
        this.encryptionkey = encryptionkey;
        this.fileName = fileName;
        this.UUID = uuid;
    }

    public String getNameID() {
        return nameID;
    }

    public void setNameID(String nameID) {
        this.nameID = nameID;
    }

    public String getEncryptionkey() {
        return encryptionkey;
    }

    public void setEncryptionkey(String encryptionkey) {
        this.encryptionkey = encryptionkey;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    @Override
    public String toString() {
        return "Message{" +
                "nameID='" + nameID + '\'' +
                ", encryptionkey='" + encryptionkey + '\'' +
                ", fileName='" + fileName + '\'' +
                ", UUID='" + UUID + '\'' +
                '}';
    }
}
