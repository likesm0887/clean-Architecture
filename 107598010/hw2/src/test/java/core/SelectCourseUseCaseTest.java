package core;

import adapter.gateway.DatabaseDto.DatabaseDto;
import adapter.presenter.InsertCoursePresenter;
import core.useCaseContract.InputDataFolder.SelectCourseInputData;
import core.useCase.OutputBoundary;
import core.useCase.SelectCourseUseCase;
import core.useCase.UseCaseInteractor;
import adapter.gateway.Database;

import core.entity.Course;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import core.useCaseContract.OutputDataFolder.SelectCourseOutputData;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class SelectCourseUseCaseTest {
    private SelectCourseInputData inputData;
    private OutputBoundary presenter;
    private UseCaseInteractor selectCourseUseCase;
    private SelectCourseOutputData outputData;
    @Mock
    Database mockDatabaseImpl = mock(Database.class);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        inputData = new SelectCourseInputData();
        outputData = new SelectCourseOutputData();
        presenter = new InsertCoursePresenter(outputData);
        selectCourseUseCase = new SelectCourseUseCase(inputData, presenter, mockDatabaseImpl);
    }

    @Test
    public void execute() {
        final String QUERY_COURSE_NAME = "軟體工程";

        List list = new ArrayList<Course>();
        List spy = spy(list);
        DatabaseDto course = new DatabaseDto();
        course.setCourseName(QUERY_COURSE_NAME);
        List<DatabaseDto> mockCourseList =new ArrayList<>();
        mockCourseList.add(course);
        when(mockDatabaseImpl.read()).thenReturn(mockCourseList);
        selectCourseUseCase.execute();
        SelectCourseOutputData actual = (SelectCourseOutputData) presenter.getOutputDataList().get(0);
        Assert.assertEquals(actual.getCourseName(), QUERY_COURSE_NAME);
    }
}