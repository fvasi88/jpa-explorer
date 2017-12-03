package eu.fvsware.jpaexplorer.dto;

public class FailedJpqlExecution extends JpqlQueryExecution {

    private final String errorMessage;

    public FailedJpqlExecution(String jpqlQuery, String nativeQuery, String errorMessage) {
        super(jpqlQuery, nativeQuery);
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean isSuccessful() {
        return false;
    }

    @Override
    public String getResultAsString() {
        return errorMessage;
    }
}
