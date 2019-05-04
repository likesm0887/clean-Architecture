package View.UI;

import View.Data.RequestData;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.regex.Pattern;

public class CourseEditorView {

    @FXML
    private TextField courseName;
    @FXML
    private TextField courseDescription;
    @FXML
    private TextField courseTarget;
    @FXML
    private TextField coursePrice;
    @FXML
    private TextField courseAttentionNote;
    @FXML
    private TextField courseNote;
    private Stage dialogStage;
    private boolean okClicked = false;
    private RequestData requestData;

    //judge input is a Number
    private boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    @FXML
    private void initialize() {
        coursePrice.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!isNumeric(coursePrice.getText())) {
                    setAlert("請輸入數字", "重新輸入");
                    coursePrice.clear();
                }
            }
        });
    }

    //create Alert Dialog
    private void setAlert(String title, String header) {
        final Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle(title);
        alert2.setHeaderText(header);
        alert2.setContentText("請按下「確定」按鈕。並重新填寫");
        alert2.showAndWait();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public boolean isOkClicked() {
        return okClicked;
    }

    public void setCourseName(RequestData courseNameString) {
        if (null != courseNameString.getCourseName()) {
            courseName.setText(courseNameString.getCourseName());
            courseName.setDisable(true);
        }
    }

    public void setViewTextInRequestData(RequestData requestData) {
        this.requestData = requestData;

        if (this.requestData.getCourseName() == null) {
            this.requestData.setCourseName(courseName.getText());
        } else {
            this.requestData.setCourseName(requestData.getCourseName());
        }
        String temp = courseDescription.getText();
        this.requestData.setCourseDescription(temp);
        this.requestData.setCourseTarget(courseTarget.getText());
        if (coursePrice.getText().equals("")) {
            coursePrice.setText("0");
        }
        this.requestData.setCoursePrice(Integer.parseInt(coursePrice.getText().toString()));
        this.requestData.setCourseAttentionNote(courseAttentionNote.getText());
        this.requestData.setCourseNote(courseNote.getText());
    }

    @FXML
    private void handleOk() {
        if (courseName.getText().isEmpty()) {
            setAlert("課程空白", "課程空白");
        } else {
            dialogStage.close();
        }
        okClicked = true;
    }

    /// Cancel
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
