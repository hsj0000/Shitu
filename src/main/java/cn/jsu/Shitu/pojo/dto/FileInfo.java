package cn.jsu.projectmanage.pojo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FileInfo {
    private Integer fileId;
    private String fileName;
    private String fileType;
    private Date createTime;
    private String url;
    private long size;

    public FileInfo() {
    }

    public FileInfo(Integer fileId, String fileName, String fileType, Date createTime, String url, long size) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.createTime = createTime;
        this.url = url;
        this.size = size;
    }
}
