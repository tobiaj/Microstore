package main;

import fileservice.FileService;
import fileservice.FileServiceInterface;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@SpringBootApplication
@RestController
public class Controller {


    FileServiceInterface fileService;
    public Controller(){

        this.fileService = new FileService();
    }


    @RequestMapping("/")
    public String home(){
        return "The store application";
    }

    @RequestMapping(value = "/storeFile", method = RequestMethod.POST)
    public ResponseEntity<String> storeFile(@RequestParam("nameID") String nameID, @RequestParam(value = "file", required = false) MultipartFile multipartFile) throws Exception {


        String returnMessage = fileService.storeFile(nameID, multipartFile);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType("application/json"));

        return new ResponseEntity<String>(returnMessage, HttpStatus.OK);


    }
}
