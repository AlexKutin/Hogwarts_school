package ru.hogwarts.school.service;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;

import java.io.IOException;

public interface AvatarService {
    Avatar findAvatarByStudentId(Long studentId);

    String uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException;
}
