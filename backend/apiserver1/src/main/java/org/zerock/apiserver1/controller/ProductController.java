package org.zerock.apiserver1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.apiserver1.dto.PageRequestDTO;
import org.zerock.apiserver1.dto.PageResponseDTO;
import org.zerock.apiserver1.dto.ProductDTO;
import org.zerock.apiserver1.service.ProductService;
import org.zerock.apiserver1.util.CustomFileUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {


    private final CustomFileUtil fileUtil;
private final ProductService productService;
//    @PostMapping("/")
//    public Map<String,String> register(ProductDTO productDTO){
//        log.info(("register"+productDTO));
//
//
//        List<MultipartFile> files =productDTO.getFiles();
//
//        List<String> uploadedFileNames=fileUtil.saveFiles(files);
//
//        productDTO.setUploadedFileNames(uploadedFileNames);
//
//        log.info(uploadedFileNames);
//
//        return Map.of("RESULT","SUCCESS");
//    }

    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGET(@PathVariable("fileName") String fileName){
        return fileUtil.getFile(fileName);
    }




//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/list")
    public PageResponseDTO<ProductDTO> list(PageRequestDTO pageResponseDTO){

        return productService.getList(pageResponseDTO);
    }


    @PostMapping("/")
    public Map<String,Long> register(ProductDTO productDTO){

        List<MultipartFile> files=productDTO.getFiles();

        List<String> uploadFileNames = fileUtil.saveFiles(files);
        productDTO.setUploadedFileNames(uploadFileNames);

        log.info(uploadFileNames);
        Long pno= productService.register(productDTO);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return Map.of("result",pno);
    }

    @GetMapping("/{pno}")
    public ProductDTO read(@PathVariable("pno") Long pno) {

        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }

        return productService.get(pno);
    }


    @PutMapping("/{pno}")
    public Map<String, String> modify(@PathVariable Long pno, ProductDTO productDTO) {

        // productDTO안에 pno값만 고정
        productDTO.setPno(pno);

        // 원래 상품을 가지고 온다. old product 가져온다. DB에 저장되어 있는 저장된 정보
        ProductDTO oldProductDTO = productService.get(pno);

        // 새로 업로드할 파일 가져온다.
        List<MultipartFile> files = productDTO.getFiles();

        // 새로 업로드할 파일 저장
        List<String> currentUploadFileNames = fileUtil.saveFiles(files);

        // 지워지지 않는 파일들 keep files 예전 파일 그대로 있는 것
        List<String> uploadedFileNames = productDTO.getUploadedFileNames();

        if(currentUploadFileNames != null && !currentUploadFileNames.isEmpty()) {

            uploadedFileNames.addAll(currentUploadFileNames);
        }

        // 수정하기
        productService.modify(productDTO);

        // 오래된 파일이 문제. 지워져야 하는 파일들 처리
        // 기존 파일들을 가져온다.
        List<String> oldFileNames = oldProductDTO.getUploadedFileNames();

        if(oldFileNames != null && !oldFileNames.isEmpty()) {

            // 기존 파일 남아 있는지 확인
            // uploadedFileNames에서 없다는건 이 파일은 이제 존재할 의미가 없다는것 그런것만 골라서 모은다.
            List<String> removeFiles =
                    oldFileNames.stream().filter(fileName -> uploadedFileNames.indexOf(fileName) == -1 )
                            .collect(Collectors.toList());

            // 삭제한다.
            fileUtil.deleteFiles(removeFiles);

            log.info(removeFiles);
        }

        return Map.of("RESULT", "SUCCESS");
    }

        @DeleteMapping("/{pno}")
    public Map<String,String> remove(@PathVariable Long pno){
        List<String> oldFileNames=productService.get(pno).getUploadedFileNames();

            fileUtil.deleteFiles(oldFileNames);
        productService.remove(pno);

        log.info(oldFileNames);

        return Map.of("RESULT","SUCCESS");
        }


    }

