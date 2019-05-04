package core.useCase;

import core.DataMapper;
import core.useCaseContract.InputDataFolder.InputData;
import core.useCaseContract.InputDataFolder.InsertCourseInputData;
import core.useCaseContract.OutputDataFolder.InsertCourseOutputData;
import core.useCaseContract.OutputDataFolder.OutputData;
import adapter.gateway.Database;
import core.entity.Course;

public class InsertCourseUseCase implements UseCaseInteractor {
    private InsertCourseInputData inputData;
    private InsertCourseOutputData outputData;
    private Database database;
    public InsertCourseUseCase(InputData input, OutputBoundary output, Database database) {
        this.inputData = (InsertCourseInputData)input;
        this.database = database;
        this.outputData =(InsertCourseOutputData) output.getOutputData();
    }

    @Override
    public void execute() {
        DataMapper dataMapper = new DataMapper();
        Course course = new Course(inputData.getCourseName(),
                inputData.getCourseDescription(),
                inputData.getCourseTarget(),
                inputData.getCoursePrice(),
                inputData.getCourseAttentionNote(),
                inputData.getCourseNote()
        );
        int dataBaseStatement =database.insert(dataMapper.convertCourseToDatabaseDto(course));
        convertCourseToCourseOutputData(dataBaseStatement,outputData);
    }
    private void  convertCourseToCourseOutputData(int statement, OutputData outputData) {
        if (statement == 1)
        {
            outputData.setStatement("新增成功");
        }
        else
        {
            outputData.setStatement("新增失敗");
        }
    }
}
