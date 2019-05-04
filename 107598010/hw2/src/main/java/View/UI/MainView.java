package View.UI;

import View.Data.RequestData;
import View.Data.ViewModel;
import adapter.entryPoint.Controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainView implements Initializable {

    private ObservableList<ViewModel> data;
    @FXML
    private TableView<ViewModel> courseTable;
    @FXML
    private TableColumn<ViewModel, String> courseName;
    @FXML
    private TableColumn<ViewModel, String> courseDescription;
    @FXML
    private TableColumn<ViewModel, String> courseTarget;
    @FXML
    private TableColumn<ViewModel, Integer> coursePrice;
    @FXML
    private TableColumn<ViewModel, String> courseAttentionNote;
    @FXML
    private TableColumn<ViewModel, String> courseNote;
    @FXML
    private Button deleteButton;
    @FXML
    private Button updateButton;
    private Controller controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        courseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        courseDescription.setCellValueFactory(new PropertyValueFactory<>("courseDescription"));
        courseTarget.setCellValueFactory(new PropertyValueFactory<>("courseTarget"));
        coursePrice.setCellValueFactory(new PropertyValueFactory<>("coursePrice"));
        courseAttentionNote.setCellValueFactory(new PropertyValueFactory<>("courseAttentionNote"));
        courseNote.setCellValueFactory(new PropertyValueFactory<>("courseNote"));
        //listener selection if selected then updateButton and deleteButton are enable
        courseTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                    updateButton.setDisable(false);
                    deleteButton.setDisable(false);
                }
        );
    }

    @FXML
    private void handleAddCourse() throws IOException {
        RequestData requestData = new RequestData();
        showCourseEditDialogAndGetData(requestData);
        controller.insertCourse(requestData);
        updateUI();
    }

    //Update Course information
    @FXML
    private void handleUpdateCourse() throws IOException {
        int selectedIndex = courseTable.getSelectionModel().getSelectedIndex();
        RequestData requestData = new RequestData();
        requestData.setCourseName(courseTable.getItems().get(selectedIndex).getCourseName());
        showCourseEditDialogAndGetData(requestData);
        controller.updateCourse(requestData);
        updateUI();
    }

    //Delete Course
    @FXML
    private void handleDeleteCourse() {
        int selectedIndex = courseTable.getSelectionModel().getSelectedIndex();
        controller.deleteCourse(courseTable.getItems().get(selectedIndex).getCourseName());
        courseTable.getItems().remove(selectedIndex);

    }

    public void updateUI() {
        data = controller.selectAllCourse();
        courseTable.setItems(data);
        deleteButton.setDisable(true);
        updateButton.setDisable(true);
    }


    public void setController(Controller controller) {
        this.controller = controller;
    }

    //create Edit Dialog
    @FXML
    public void showCourseEditDialogAndGetData(RequestData requestData) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("SetCourseDialog.fxml"));
        Stage dialogStage = new Stage();
        Scene scene = new Scene(loader.load());
        dialogStage.setTitle("Edit Course");
        dialogStage.setScene(scene);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setScene(scene);
        CourseEditorView courseEditorView = loader.getController();
        courseEditorView.setDialogStage(dialogStage);
        courseEditorView.setCourseName(requestData);
        dialogStage.showAndWait();
        if (courseEditorView.isOkClicked()) {
            courseEditorView.setViewTextInRequestData(requestData);
        }
    }
}