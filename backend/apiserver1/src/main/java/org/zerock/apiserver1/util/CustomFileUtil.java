package org.zerock.apiserver1.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Log4j2
@RequiredArgsConstructor

public class CustomFileUtil {

    @Value("${org.zerock.upload.path}")
    private String uploadPath;
    // application properties에서 입력한 주소값 가지고온것
    //---
    @PostConstruct  //초기화 시킬때 자주시킴
    public void init(){
        File tempFolder=new File(uploadPath);

        if(tempFolder.exists() ==false){
            tempFolder.mkdir();
//없다면 폴더를 생성
        }
//c드라이브 밑에 어디어디 폴더위치 쓸라고씀
        uploadPath=tempFolder.getAbsolutePath();
        log.info("-----------------");
        log.info(uploadPath);
    }



    public List<String> saveFiles(List<MultipartFile> files) throws RuntimeException{

        if(files ==null || files.size()==0){
       return null;
        }

        List<String> uploadNames=new ArrayList<>();
        for(MultipartFile file:files){
            String savedName= UUID.randomUUID().toString()+"_"+file.getOriginalFilename();
// 원래 파일이름에 임의의 UUID를 추가하여 업로드된 각파일에 고유한 이름을 생성함 그래야  중복파일 충돌하지않음

            //------


            Path savePath= Paths.get(uploadPath,savedName); // C:/uploads/파일이름  결합해 전체경로만듬

            try {
                Files.copy(file.getInputStream(),savePath); //원본 파일 업로드

                // 업로드된 파일의 데이터를 읽어 지정된 경로에 저장



                //이미지인 경우에만 썸네일 만들도록 하기

                String contentType=file.getContentType();// mime 타입
                if(contentType != null || contentType.startsWith("image")){

                    Path thumbnailPath=Paths.get(uploadPath,"S_"+savedName);
                    Thumbnails.of(savePath.toFile()).size(200,200).toFile(thumbnailPath.toFile());

                }

                uploadNames.add(savedName);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } //end



        return uploadNames;

    }
public ResponseEntity<Resource> getFile(String fileName){
Resource resource=new FileSystemResource(uploadPath+File.separator+fileName);
if(!resource.isReadable()){
    resource=new FileSystemResource(uploadPath+File.separator+"default.png");

}

    HttpHeaders httpHeaders = new HttpHeaders();
    try {
        httpHeaders.add("Content-Type",Files.probeContentType(resource.getFile().toPath()));
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    //내가 보내는 타입을 알려줘야함



//file.separator 그 url뒤에 /
return ResponseEntity.ok().headers(httpHeaders).body(resource);
    }


    public void deleteFiles(List<String> fileNames){
        if(fileNames ==null || fileNames.size()==0){
            return;
        }
        fileNames.forEach(fileName->{
            String tumbnailFileName="s_"+fileName;
            Path thumbnailPath=Paths.get(uploadPath,tumbnailFileName);
            Path filePath=Paths.get(uploadPath,fileName);
            try {
                Files.deleteIfExists(filePath);
                Files.deleteIfExists(thumbnailPath);
            }catch(IOException e){
                throw new RuntimeException(e.getMessage());
            }

        });

    }
}
