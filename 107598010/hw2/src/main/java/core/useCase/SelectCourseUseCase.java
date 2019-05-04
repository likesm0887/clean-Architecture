package core.useCase;

import core.DataMapper;
import core.useCaseContract.InputDataFolder.InputData;
import core.useCaseContract.OutputDataFolder.OutputData;
import adapter.gateway.Database;
import core.entity.Course;
import core.useCaseContract.OutputDataFolder.SelectCourseOutputData;
import java.util.List;

public class SelectCourseUseCase implements UseCaseInteractor {
    private InputData inputData;
    private List<OutputData> outputData;
    private Database database;

    public SelectCourseUseCase(InputData input, OutputBoundary presenter, Database database) {
        this.inputData = input;
        this.database = database;
        this.outputData = presenter.getOutputDataList();
    }

    @Override
    public void execute() {
        DataMapper dataMapper =new DataMapper();
        convertCourseToCourseOutputData(dataMapper.convertDataMapperToCourse(database.read()));
    }

    private void convertCourseToCourseOutputData(List<Course> courses)
    {
        for (Course course : courses) {
            SelectCourseOutputData selectCourseOutputDataTemp = new SelectCourseOutputData();
            selectCourseOutputDataTemp.setCourseName(course.getCourseName());
            selectCourseOutputDataTemp.setCourseDescription(course.getCourseDescription());
            selectCourseOutputDataTemp.setCourseTarget(course.getCourseTarget());
            selectCourseOutputDataTemp.setCourseAttentionNote(course.getCourseAttentionNote());
            selectCourseOutputDataTemp.setCoursePrice(course.getCoursePrice());
            selectCourseOutputDataTemp.setCourseNote(course.getCourseNote());
            outputData.add(selectCourseOutputDataTemp);
        }

    }
}
