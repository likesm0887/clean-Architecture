package adapter.presenter;

import View.Data.ViewModel;
import core.useCase.OutputBoundary;
import core.useCaseContract.OutputDataFolder.OutputData;
import java.util.ArrayList;
import java.util.List;

public class DeleteCoursePresenter implements OutputBoundary {
    private OutputData outputData;
    private List<OutputData> outputDataList = new ArrayList<>();
    public DeleteCoursePresenter(OutputData outputData)
    {
        this.outputData=outputData;
    }
    @Override
    public OutputData getOutputData() {
        return outputData;
    }

    @Override
    public List<OutputData> getOutputDataList() {
        return outputDataList;
    }

    public ViewModel buildViewModel()
    {
        ViewModel viewModel =new ViewModel();
        viewModel.setStatement(outputData.getStatement());
        return viewModel;
    }
}
