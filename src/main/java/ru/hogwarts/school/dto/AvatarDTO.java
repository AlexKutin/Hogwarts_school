package ru.hogwarts.school.dto;

import ru.hogwarts.school.model.Avatar;

public class AvatarDTO {
    private Long id;
    private String filePath;
    private long fileSize;
    private String mediaType;
    private byte[] data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public static AvatarDTO fromAvatar(Avatar avatar) {
        AvatarDTO avatarDTO = new AvatarDTO();
        avatarDTO.setId(avatar.getId());
        avatarDTO.setFilePath(avatar.getFilePath());
        avatarDTO.setFileSize(avatar.getFileSize());
        avatarDTO.setMediaType(avatar.getMediaType());
        avatarDTO.setData(avatar.getData());

        return avatarDTO;
    }

    public Avatar toAvatar() {
        Avatar avatar = new Avatar();
        avatar.setId(this.getId());
        avatar.setFilePath(this.getFilePath());
        avatar.setFileSize(this.getFileSize());
        avatar.setMediaType(this.getMediaType());
        avatar.setData(this.getData());

        return avatar;
    }
}
