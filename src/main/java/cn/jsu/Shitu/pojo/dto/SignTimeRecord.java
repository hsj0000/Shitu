package cn.jsu.projectmanage.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignTimeRecord {
    private Integer signTimeId;
    private Integer signRecordId;
    private String signTime;
    private String signoutTime;
}
