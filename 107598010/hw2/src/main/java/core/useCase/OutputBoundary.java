package core.useCase;

import core.useCaseContract.OutputDataFolder.OutputData;
import java.util.List;

public interface OutputBoundary {
    OutputData getOutputData();

    List<OutputData> getOutputDataList();
}
