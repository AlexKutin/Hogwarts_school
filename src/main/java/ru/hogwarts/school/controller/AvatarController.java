package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.exception.AvatarNotFoundException;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Tag(name = "Аватарки", description = "Управление аватарками студентов")
@RestController
@RequestMapping("/avatar")
public class AvatarController {
    private static final int MAX_AVATAR_SIZE = 50 * 1024;   // max avatar file size in kilobytes
    private final AvatarService avatarService;

    @Autowired
    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @Operation(summary = "Загрузка аватарки студента на сервер", description = "Загрузка аватарки студента их файла")
    @PostMapping(value = "{studentId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(
            @PathVariable @Parameter(description = "Идентификатор студента") Long studentId,
            @RequestParam MultipartFile avatarFile) throws IOException {
        if (avatarFile.getSize() > MAX_AVATAR_SIZE) {
            return ResponseEntity.badRequest().body("File is too big, max avatar file size is " + MAX_AVATAR_SIZE);
        }
        String avatarName = avatarService.uploadAvatar(studentId, avatarFile);

        return ResponseEntity.ok(avatarName + " upload successfully");
    }

    @Operation(summary = "Получение аватарки студента из БД", description = "Получение аватарки студента из БД")
    @GetMapping("/db/{studentId}")
    public ResponseEntity<byte[]> downloadAvatarFromDatabase(
            @PathVariable @Parameter(description = "Идентификатор студента") Long studentId) {
        Avatar avatar = avatarService.findAvatarByStudentId(studentId);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        httpHeaders.setContentLength(avatar.getFileSize());

        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(avatar.getData());
    }

    @Operation(summary = "Получение аватарки студента из директории с сервера",
            description = "Получение аватарки студента из директории с сервера")
    @GetMapping("/file/{studentId}")
    public void downloadAvatarFromFile(
            @PathVariable @Parameter(description = "Идентификатор студента") Long studentId,
            HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.findAvatarByStudentId(studentId);
        Path avatarPath = Path.of(avatar.getFilePath());

        try (InputStream is = Files.newInputStream(avatarPath);
             OutputStream os = response.getOutputStream()
        ) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType(avatar.getMediaType());
            response.setContentLengthLong(avatar.getFileSize());

            is.transferTo(os);
        }
    }

    @ExceptionHandler(AvatarNotFoundException.class)  // 400
    public ResponseEntity<?> handleAvatarNotFound(AvatarNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
