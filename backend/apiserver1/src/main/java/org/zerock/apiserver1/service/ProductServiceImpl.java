package org.zerock.apiserver1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.apiserver1.domain.Product;
import org.zerock.apiserver1.domain.ProductImage;
import org.zerock.apiserver1.dto.PageRequestDTO;
import org.zerock.apiserver1.dto.PageResponseDTO;
import org.zerock.apiserver1.dto.ProductDTO;
import org.zerock.apiserver1.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService {
private final ProductRepository productRepository;

    @Override
    public PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO) {

        Pageable pageable= PageRequest.of(pageRequestDTO.getPage()-1, pageRequestDTO.getSize(),
                Sort.by("pno").descending());

        Page<Object[]> result=productRepository.selectList(pageable);

        List<ProductDTO> dtoList=result.get().map(arr->{
            ProductDTO productDTO=null;

            Product product=(Product) arr[0];
            ProductImage productImage=(ProductImage) arr[1];

            productDTO=ProductDTO.builder()
                    .pno(product.getPno())
                    .pname(product.getPname())
                    .pdesc(product.getPdesc())
                    .price(product.getPrice())
                    .build();

String imageStr= productImage.getFileName();
productDTO.setUploadFileNames(List.of(imageStr));
        return productDTO;

        }).collect(Collectors.toList());
long totalCount=result.getTotalElements();

return PageResponseDTO.<ProductDTO>withAll()
        .dtoList(dtoList)
        .totalCount(totalCount)
        .pageRequestDTO(pageRequestDTO)
        .build();






    }

    @Override
    public Long register(ProductDTO productDTO) {
        Product product=dtoEntity(productDTO);


        log.info("============");
        log.info(product);
        log.info(product.getImageList());

        Long pno = productRepository.save(product).getPno();
        return pno ;
    }

    @Override
    public ProductDTO get(Long pno) {
        Optional<Product> result=productRepository.findById(pno);
        Product product=result.orElseThrow();
        ProductDTO productDTO=entitytoDTO(product);


        return productDTO;

//이번엔 가지고있는 데이터를 조회할떄 뿌려주는거라서 entity->dto로 변환

    }

    @Override
    public void modify(ProductDTO productDTO) {

        // 조회
        Optional<Product> result = productRepository.findById(productDTO.getPno());

        Product product = result.orElseThrow();

        // 변경내용 반영
        product.changePrice(productDTO.getPrice());
        product.changeName(productDTO.getPname());
        product.changeDesc(productDTO.getPdesc());
        product.changeDel(productDTO.isDelFlag());

        // 이미지 처리를 위해 목록을 비운다.
        List<String> uploadFileNames = productDTO.getUploadFileNames();

        product.clearList();

        if(uploadFileNames != null && uploadFileNames.isEmpty()) {

            uploadFileNames.forEach(uploadName ->{
                product.addImageString(uploadName);
            });

        }

        // 저장될 때 파일이 문제 파일이 변경됐는지 알 수 가 없다.
        productRepository.save(product);

    }

    @Override
    public void remove(Long pno) {
        productRepository.deleteById(pno);
    }

    private ProductDTO entitytoDTO(Product product){
        ProductDTO productDTO=ProductDTO.builder()
                .pno(product.getPno())
                .pname(product.getPname())
                .pdesc(product.getPdesc())
                .price(product.getPrice())
                .delFlag(product.isDelFlag())


                .build();

        List<ProductImage> imageList=product.getImageList();
        if(imageList==null || imageList.isEmpty()){
            return productDTO;
        }
        List<String> fileNameList = imageList.stream().map(productImage ->
                productImage.getFileName()).toList();

        productDTO.setUploadFileNames(fileNameList);

return productDTO;
    }




    private Product dtoEntity(ProductDTO productDTO){
        Product product=Product.builder()
                .pno(productDTO.getPno())
                .pname(productDTO.getPname())
                .pdesc(productDTO.getPdesc())
                .price(productDTO.getPrice())
                .build();
        List<String> uploadedFileNames = productDTO.getUploadFileNames();

        if(uploadedFileNames ==null || uploadedFileNames.size()==0){
            return product;
        }
//elementcollection으로 관리하고있는 imageList 오브젝트는 바뀌면 새로운객체라 문제가 된다 그래서  이렇게 써준다는데 이유를 모르겠어
// uploadedFileNames에 있는 파일 이름을 엔티티의 imageList에 추가하려면,
// 각각의 파일 이름을 ProductImage 객체로 변환해서 리스트에 넣어줘야 합니다.
        // 순서 dto에서 uploadedfilenames를 가져오고 -> 파일이름을 productimage 객체로 변환한다 foreach를써서
        // -> imageList에 추가된다 이런과정을 거친다고 함

        uploadedFileNames.forEach(fileName->{
            product.addImageString(fileName);
        });

        return product;
    }
}
