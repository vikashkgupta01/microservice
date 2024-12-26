
package com.demo.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/*
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDto implements Serializable {

    private static final long serialVersionUID=1L;

    @Valid
    //@NotNull(message = "authorName.notNull")
    @JsonProperty("authorName")
    @Size(max=20, message = "author name can't be more than 20")
    @NotEmpty(message = "authorName is mandatory")
    private String authorName;
    private String bookName;
    private float price;
    private float rating;
    private String genre;

    public void setAuthorName(String authorName){
        if(authorName.length()<=20){
            this.authorName=authorName.trim();
        }else{
            this.authorName=authorName;
        }
    }

}
*/


@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "authorName is mandatory")
    @Size(max = 20, message = "author name can't be more than 20 characters")
    @JsonProperty("authorName")
    @NotNull
    private String authorName;
    private String bookName;
    private float price;
    private float rating;
    private String genre;
}
