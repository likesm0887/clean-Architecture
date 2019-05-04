package adapter.entryPoint;

import View.Data.RequestData;
import View.Data.ViewModel;
import adapter.presenter.DeleteCoursePresenter;
import adapter.presenter.InsertCoursePresenter;
import adapter.presenter.SelectCoursePresenter;
import adapter.presenter.UpdateCoursePresenter;
import core.useCaseContract.InputDataFolder.DeleteCourseInputData;
import core.useCaseContract.InputDataFolder.UpdateCourseInputData;
import core.useCaseContract.OutputDataFolder.InsertCourseOutputData;
import core.useCaseContract.OutputDataFolder.SelectCourseOutputData;
import core.useCaseContract.OutputDataFolder.DeleteCourseOutputData;
import core.useCaseContract.OutputDataFolder.UpdataCourseOutputData;
import javafx.collections.ObservableList;
import core.useCaseContract.InputDataFolder.InsertCourseInputData;
import core.useCaseContract.InputDataFolder.SelectCourseInputData;
import core.useCase.UseCaseFactory;
import core.useCase.UseCaseInteractor;

public class Controller {
    private UseCaseFactory useCaseFactory;

    public Controller(UseCaseFactory useCaseFactory) {
        this.useCaseFactory = useCaseFactory;
    }

    private void convertViewDtoToInputData(RequestData viewDto, InsertCourseInputData inputData) {
        inputData.setCourseName(viewDto.getCourseName());
        inputData.setCourseAttentionNote(viewDto.getCourseAttentionNote());
        inputData.setCourseDescription(viewDto.getCourseDescription());
        inputData.setCourseNote(viewDto.getCourseNote());
        inputData.setCoursePrice(viewDto.getCoursePrice());
        inputData.setCourseTarget(viewDto.getCourseTarget());
    }

    private void convertViewDtoToInputData(RequestData viewDto, UpdateCourseInputData inputData) {
        inputData.setCourseName(viewDto.getCourseName());
        inputData.setCourseAttentionNote(viewDto.getCourseAttentionNote());
        inputData.setCourseDescription(viewDto.getCourseDescription());
        inputData.setCourseNote(viewDto.getCourseNote());
        inputData.setCoursePrice(viewDto.getCoursePrice());
        inputData.setCourseTarget(viewDto.getCourseTarget());
    }

    public ViewModel insertCourse(RequestData viewDto) {
        InsertCoursePresenter insertCoursePresenter = new InsertCoursePresenter(new InsertCourseOutputData());
        InsertCourseInputData inputData = new InsertCourseInputData();
        convertViewDtoToInputData(viewDto, inputData);
        UseCaseInteractor insertUseCase = useCaseFactory.createInsertUseCase(inputData, insertCoursePresenter);
        insertUseCase.execute();
        return insertCoursePresenter.buildViewModel();
    }

    public ObservableList selectAllCourse() {
        SelectCoursePresenter selectCoursePresenter = new SelectCoursePresenter(new SelectCourseOutputData());
        UseCaseInteractor selectAllCourseUseCase = useCaseFactory.createSelectAllCourse(new SelectCourseInputData(), selectCoursePresenter);
        selectAllCourseUseCase.execute();
        return selectCoursePresenter.buildObservableArrayList();
    }

    public ViewModel updateCourse(RequestData viewDto)
    {
        UpdateCoursePresenter  updateCoursePresenter = new UpdateCoursePresenter(new UpdataCourseOutputData());
        UpdateCourseInputData inputData = new  UpdateCourseInputData();
        convertViewDtoToInputData(viewDto, inputData);
        UseCaseInteractor updateUseCase = useCaseFactory.createUpdateCourse(inputData,  updateCoursePresenter);
        updateUseCase.execute();
        return  updateCoursePresenter.buildViewModel();
    }

    public ViewModel deleteCourse(String request)
    {
        DeleteCoursePresenter deleteCoursePresenter = new DeleteCoursePresenter(new DeleteCourseOutputData());
        DeleteCourseInputData inputData = new DeleteCourseInputData();
        inputData.setCourseName(request);
        UseCaseInteractor deleteCourseUseCase = useCaseFactory.createDeleteCourse(inputData,deleteCoursePresenter);
        deleteCourseUseCase.execute();
        return deleteCoursePresenter.buildViewModel();
    }
}
