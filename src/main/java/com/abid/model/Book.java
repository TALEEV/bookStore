package com.abid.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Book implements Serializable {


    /**
     * 
     */
    private static final long serialVersionUID = -5774213845138983169L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer isbn;

    private String name;

    private String description;

    private String author;

    private String classification;

    private Double price;

}
