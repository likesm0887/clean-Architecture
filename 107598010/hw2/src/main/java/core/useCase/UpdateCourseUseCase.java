package core.useCase;

import core.DataMapper;
import core.entity.Course;
import core.useCaseContract.InputDataFolder.InputData;
import core.useCaseContract.InputDataFolder.UpdateCourseInputData;
import core.useCaseContract.OutputDataFolder.UpdataCourseOutputData;
import core.useCaseContract.OutputDataFolder.OutputData;
import adapter.gateway.Database;

public class UpdateCourseUseCase implements UseCaseInteractor {
    private UpdateCourseInputData inputData;
    private UpdataCourseOutputData outputData;
    private Database database;

    public UpdateCourseUseCase(InputData input, OutputBoundary output, Database database) {
        this.inputData = (UpdateCourseInputData) input;
        this.database = database;
        this.outputData = (UpdataCourseOutputData) output.getOutputData();
    }

    @Override
    public void execute() {
        DataMapper dataMapper =new DataMapper();
        Course course = new Course(inputData.getCourseName(),
                inputData.getCourseDescription(),
                inputData.getCourseTarget(),
                inputData.getCoursePrice(),
                inputData.getCourseAttentionNote(),
                inputData.getCourseNote()
        );
        int dataBaseStatement = database.update(dataMapper.convertCourseToDatabaseDto(course));
        convertCourseToCourseOutputData(dataBaseStatement, outputData);
    }

    private void convertCourseToCourseOutputData(int statement, OutputData outputData) {
        if (statement == 1) {
            outputData.setStatement("更新成功");
        } else {
            outputData.setStatement("更新失敗");
        }
    }
}
