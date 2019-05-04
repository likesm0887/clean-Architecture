package core;

import adapter.gateway.DatabaseDto.DatabaseDto;
import core.useCaseContract.InputDataFolder.InsertCourseInputData;
import core.useCase.InsertCourseUseCase;
import core.useCase.OutputBoundary;
import core.useCase.UseCaseInteractor;
import adapter.gateway.SqliteDatabase;
import core.entity.Course;
import core.useCaseContract.OutputDataFolder.InsertCourseOutputData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import adapter.presenter.InsertCoursePresenter;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InsertCourseUseCaseTest {
    private InsertCourseInputData inputData;
    private InsertCourseOutputData outputData;
    private UseCaseInteractor insertCourseUseCase;
    private Course course;
    @Mock
    SqliteDatabase mockDatabase = mock(SqliteDatabase.class);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        inputData = new InsertCourseInputData();
        outputData = new InsertCourseOutputData();
        OutputBoundary presenter = new InsertCoursePresenter(outputData);
        insertCourseUseCase = new InsertCourseUseCase(inputData, presenter, mockDatabase);
        course = new Course();
    }

    @Test
    public void execute() {
        final String INSERT_COURSE_NAME = "軟體工程";
        course.setCourseName(INSERT_COURSE_NAME);
        inputData.setCourseName(INSERT_COURSE_NAME);
        //當insert(隨便一個 Course)被呼叫第一次則輸出1，第二次呼叫則輸出2
        when(mockDatabase.insert(Mockito.any(DatabaseDto.class))).thenReturn(1).thenReturn(2);
        insertCourseUseCase.execute();//第一次呼叫
        Assert.assertEquals("新增成功",outputData.getStatement());
        insertCourseUseCase.execute();//第二次呼叫
        Assert.assertEquals("新增失敗",outputData.getStatement());
    }
}