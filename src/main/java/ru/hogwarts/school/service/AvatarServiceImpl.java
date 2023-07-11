package ru.hogwarts.school.service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.exception.AvatarNotFoundException;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class AvatarServiceImpl implements AvatarService {
    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;

    @Value("${application.avatars.folder}")
    private String avatarFolder;

    public AvatarServiceImpl(AvatarRepository avatarRepository, StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Avatar findAvatarByStudentId(Long studentId) {
        return avatarRepository.findById(studentId)
                .orElseThrow(() -> new AvatarNotFoundException("The avatar not found for studentId = " + studentId));
    }

    @Override
    public String uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        String avatarExtension = FilenameUtils.getExtension(avatarFile.getOriginalFilename());
        Path avatarFilePath = Path.of(avatarFolder, studentId + "." + avatarExtension);
        Files.createDirectories(avatarFilePath.getParent());
        Files.deleteIfExists(avatarFilePath);

        try (InputStream is = avatarFile.getInputStream()) {
            Files.copy(is, avatarFilePath, StandardCopyOption.REPLACE_EXISTING);
        }

        Avatar avatar = new Avatar();
        avatar.setId(studentId);
        avatar.setFilePath(avatarFilePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(Files.readAllBytes(avatarFilePath));
        avatar.setStudent(studentRepository.findById(studentId).get());

        avatarRepository.save(avatar);
        return avatarFilePath.toString();
    }
}
