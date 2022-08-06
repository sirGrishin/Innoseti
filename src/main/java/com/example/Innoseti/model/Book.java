package com.example.Innoseti.model;

import lombok.*;

import javax.persistence.*;

/**
 * @author Ilya Grishin
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title")
    private String title;


}
