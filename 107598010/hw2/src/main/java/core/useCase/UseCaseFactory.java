package core.useCase;

import core.useCaseContract.InputDataFolder.InputData;
import adapter.gateway.Database;

public class UseCaseFactory {
    private Database database;
    public UseCaseFactory(Database database) {
        this.database = database;
    }

    public UseCaseInteractor createInsertUseCase(InputData inputData, OutputBoundary presenter) {
        return new InsertCourseUseCase(inputData, presenter, database);
    }

    public UseCaseInteractor createSelectAllCourse(InputData inputData, OutputBoundary presenter) {
        return new SelectCourseUseCase(inputData, presenter, database);
    }

    public UseCaseInteractor createDeleteCourse(InputData inputData, OutputBoundary presenter) {
        return new DeleteCourseUseCase(inputData, presenter, database);
    }

    public UseCaseInteractor createUpdateCourse(InputData inputData, OutputBoundary presenter) {
        return new UpdateCourseUseCase(inputData, presenter, database);
    }
}
