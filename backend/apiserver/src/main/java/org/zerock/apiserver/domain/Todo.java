package org.zerock.apiserver.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Length;

import java.time.LocalDate;

@Entity
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Table(name="tbl_todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;

    @Column(length =500,nullable = false)
    private String title;
    private String content;
    private Boolean complete;

    private LocalDate dueDate;

}
