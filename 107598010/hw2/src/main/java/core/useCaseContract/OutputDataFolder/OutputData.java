package core.useCaseContract.OutputDataFolder;

public abstract class OutputData {
    protected String statement = "";

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getStatement() {
        return this.statement;
    }
}
