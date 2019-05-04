package core.useCase;

import core.useCaseContract.InputDataFolder.DeleteCourseInputData;
import core.useCaseContract.InputDataFolder.InputData;
import core.useCaseContract.OutputDataFolder.DeleteCourseOutputData;
import core.useCaseContract.OutputDataFolder.OutputData;
import adapter.gateway.Database;

public class DeleteCourseUseCase implements UseCaseInteractor {
    private DeleteCourseInputData inputData;
    private DeleteCourseOutputData outputData;
    private Database database;
    public DeleteCourseUseCase(InputData input, OutputBoundary output, Database database) {
        this.inputData = (DeleteCourseInputData)input;
        this.database = database;
        this.outputData = (DeleteCourseOutputData)output.getOutputData();
    }
    @Override
    public void execute() {
        int dataBaseStatement = database.delete(inputData.getCourseName());
        convertCourseToCourseOutputData(dataBaseStatement,outputData);

    }
    private void  convertCourseToCourseOutputData(int statement, OutputData outputData) {
        if (statement == 1)
        {
            outputData.setStatement("刪除成功");
        }
        else
        {
            outputData.setStatement("刪除失敗");
        }
    }
}
