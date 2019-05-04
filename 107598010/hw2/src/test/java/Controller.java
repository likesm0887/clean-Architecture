/*
public class Controller {
    private InputData input;

    private  UseCaseFactory useCaseFactory;
    private  OutputBoundary presenter;
    private UseCaseInteractor useCaseInteractor;
    Controller(UseCaseFactory useCaseFactory)
    {
        this.useCaseFactory =useCaseFactory;
    }

    public ViewModel selectCourse(InputData input , OutputBoundary present)
    {
        useCaseInteractor = useCaseFactory.createSelectCrouseUseCase();
        useCaseInteractor.execute(input,present);
        return present.buildViewModel();
    }


 courseTable.setItems(controller.select)
}
*/