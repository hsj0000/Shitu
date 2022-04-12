package cn.jsu.projectmanage.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInfoRecord {
    private Integer signRecord;
    private Integer signId;
    private Integer signYear;
    private Integer signMonth;
    private Integer signDay;
    private String absenceReason;
    private double totalSignTime;
}
