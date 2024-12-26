package com.demo.book.entity;


import com.demo.book.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String authorName;
    private String bookName;
    private float price;
    private float rating;
    private String genre;

    /*@Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", authorName='" + authorName + '\'' +
                ", bookName='" + bookName + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", transRef='" + getTransRef() + '\'' +
                ", createdDt='" + getCreatedDt() + '\'' +
                ", lastModifiedDt='" + getLastModifiedDt() + '\'' +
                ", genere='"+getGenre()+
                '}';
    }*/
}
