package com.zherdev.imageserver.controler;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.PathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController("/")
public class FilesController {

    private static String BASE_BOOK_IMAGE_PATH = "C:\\Image\\";
    private static String BASE_BOOK_FILE_PATH = "C:\\BookContent\\";

    @GetMapping(value = "/images/{fileName}")
    public ResponseEntity<InputStreamResource> getImage(@PathVariable String fileName) throws IOException {

        var file = new PathResource(BASE_BOOK_IMAGE_PATH + fileName);
        if (!file.exists())
            return ResponseEntity.notFound().build();

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(file.getInputStream()));
    }

    @GetMapping(value = "/files/{fileName}")
    public ResponseEntity<InputStreamResource> getFile(@PathVariable String fileName) throws IOException {
        var file = new PathResource(BASE_BOOK_FILE_PATH + fileName);
        if (!file.exists())
            return ResponseEntity.notFound().build();

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(file.getInputStream()));
    }
}
