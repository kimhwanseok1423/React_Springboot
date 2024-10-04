package org.zerock.apiserver1.dto;


import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
@Data
public class PageResponseDTO<E> {

    public List<E> dtoList;

    private List<Integer> pageNumList;


    private PageRequestDTO pageRequestDTO;

    private boolean prev, next;

    private int totalCount, prevPage, nextPage, totalPage, current;
@Builder(builderMethodName = "withAll")
    public PageResponseDTO(List<E> dtoList, PageRequestDTO pageRequestDTO, long totalCount ) {
        this.dtoList = dtoList;
        this.pageRequestDTO = pageRequestDTO;
        this.totalCount=(int)totalCount;


        //끝페이지
        int end=(int)(Math.ceil(pageRequestDTO.getPage() /10.0))*10;

        int start=end-9;

        //진짜 마지막 페이지
      int last=  (int)Math.ceil(totalCount/(double)pageRequestDTO.getSize());

      end=end > last ? last : end;

      this.prev=start>1;

      this.next=totalCount>end*pageRequestDTO.getSize();

      this.pageNumList= IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());

      this.prevPage=prev ? start -1 : 0;

      this.nextPage=next ? end+1 : 0;



    this.totalPage = this.pageNumList.size();
    this.current=pageRequestDTO.getPage();
    }

    //   1....10
    //   11....20
    // prev  21....30
    // 현재 페이지 3이면          1        3          10
    //   3 /10 으로 나누면 0.3  거기다 *10 한후 -9//
}