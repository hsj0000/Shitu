package cn.jsu.projectmanage.pojo.dto;

/**
 * @author Mo
 * @createTime 2022/1/4 21:30
 * @descripiton
 */
public class Attendance {
    private Integer attendanceId;
    private String signIn;
    private String signOut;
    private Integer userId;

    public Attendance() {
    }

    public Attendance(Integer attendanceId, String signIn, String signOut, Integer userId) {
        this.attendanceId = attendanceId;
        this.signIn = signIn;
        this.signOut = signOut;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "attendanceId=" + attendanceId +
                ", signIn='" + signIn + '\'' +
                ", signOut='" + signOut + '\'' +
                ", userId=" + userId +
                '}';
    }

    public Integer getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Integer attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getSignIn() {
        return signIn;
    }

    public void setSignIn(String signIn) {
        this.signIn = signIn;
    }

    public String getSignOut() {
        return signOut;
    }

    public void setSignOut(String signOut) {
        this.signOut = signOut;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
