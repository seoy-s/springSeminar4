package com.spring.seminar4.entity;

import lombok.*;

import javax.persistence.*;

@Data
@ToString
@Entity
@Builder
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "writer")
    private String writer;

    @Column(name = "content")
    private String content;
}
