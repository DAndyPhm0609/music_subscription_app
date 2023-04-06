package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class FileMeta {
    private static final long serialVersionUID = 1L;

    private int id;

    private String fileName;

    private String filePath;

    private String version;

    public FileMeta(String fileName, String filePath, String version) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.version = version;
    }
}