package fileservice;

import org.springframework.web.multipart.MultipartFile;

public interface FileServiceInterface {

    public String storeFile(String nameID, MultipartFile multipartFile);

}
