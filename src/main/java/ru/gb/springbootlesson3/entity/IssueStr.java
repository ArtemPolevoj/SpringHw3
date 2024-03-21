package ru.gb.springbootlesson3.entity;

import lombok.Data;

@Data
public class IssueStr {
    private static long genId;
    private final long id;


    private final String readerStr;
    private final String bookStr;
    private final String issuedAtStr;
    private final String returnedAtStr;

    public IssueStr(String readerStr, String bookStr, String issuedAtStr, String returnedAtStr) {
        id = genId++;;
        this.readerStr = readerStr;
        this.bookStr = bookStr;
        this.issuedAtStr = issuedAtStr;
        this.returnedAtStr = returnedAtStr;
    }
}
