package core.useCaseContract.InputDataFolder;

public class UpdateCourseInputData extends InputData {
    private String courseName;
    private String courseDescription;
    private String courseTarget;
    private int coursePrice;
    private String courseAttentionNote;
    private String courseNote;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCourseTarget() {
        return courseTarget;
    }

    public void setCourseTarget(String courseTarget) {
        this.courseTarget = courseTarget;
    }

    public int getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(int coursePrice) {
        this.coursePrice = coursePrice;
    }

    public String getCourseAttentionNote() {
        return courseAttentionNote;
    }

    public void setCourseAttentionNote(String courseAttentionNote) {
        this.courseAttentionNote = courseAttentionNote;
    }

    public String getCourseNote() {
        return courseNote;
    }

    public void setCourseNote(String courseNote) {
        this.courseNote = courseNote;
    }

}
