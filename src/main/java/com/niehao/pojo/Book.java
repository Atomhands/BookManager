package com.niehao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {
    private String bookId;
    private String bookName;
    private String publisher;
    private String author;
    private String bookType;
    private Integer remain;
}
