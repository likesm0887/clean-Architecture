package adapter.presenter;

import View.Data.ViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import core.useCase.OutputBoundary;
import core.useCaseContract.OutputDataFolder.SelectCourseOutputData;
import core.useCaseContract.OutputDataFolder.OutputData;

import java.util.ArrayList;
import java.util.List;

public class SelectCoursePresenter implements OutputBoundary {
    private OutputData outputData;
    private List<OutputData> outputDataList = new ArrayList<>();
    public SelectCoursePresenter(OutputData outputData)
    {
        this.outputData=outputData;
    }
    @Override
    public OutputData getOutputData() {
        return outputData;
    }
    @Override
    public List<OutputData> getOutputDataList(){return outputDataList;}

    public ObservableList buildObservableArrayList()
    {
        List<ViewModel> viewModels = new ArrayList<ViewModel>();
        for (OutputData  outputData :outputDataList )
        {
            ViewModel viewModel =new ViewModel();
            SelectCourseOutputData selectCourseOutputData =(SelectCourseOutputData) outputData;
            viewModel.setCourseName(selectCourseOutputData.getCourseName());
            viewModel.setCourseNote(selectCourseOutputData.getCourseNote());
            viewModel.setCourseAttentionNote(selectCourseOutputData.getCourseAttentionNote());
            viewModel.setCourseTarget(selectCourseOutputData.getCourseTarget());
            viewModel.setCourseDescription(selectCourseOutputData.getCourseDescription());
            viewModel.setCoursePrice(selectCourseOutputData.getCoursePrice());
            viewModels.add(viewModel);
        }
        return FXCollections.observableArrayList(viewModels);
    }
}
