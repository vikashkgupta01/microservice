package com.demo.book.common;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@AllArgsConstructor
public class BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;

    private String transRef;
    private String createdDt;
    private String lastModifiedDt;
    //private Integer version;

    public BaseEntity(){
        this.createdDt= LocalDateTime.now().toString();
        this.lastModifiedDt=LocalDateTime.now().toString();
        //this.transRef=getTransRef();
    }
}
