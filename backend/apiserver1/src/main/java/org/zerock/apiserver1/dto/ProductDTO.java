package org.zerock.apiserver1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductDTO {

    private Long pno;
    private String pname;
    private int price;
    private String pdesc;

    private boolean delFlag;

//업로드 처리할얘
    @Builder.Default
    private List<MultipartFile> files=new ArrayList<>();


    //조회할떄쓸거 업로드된파일
    @Builder.Default
     private List<String> uploadFileNames=new ArrayList<>();


}
