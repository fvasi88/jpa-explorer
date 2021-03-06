package eu.fvsware.jpaexplorer.dto;

public abstract class JpqlQueryExecution {
    private final String jpqlQuery;
    private final String nativeQuery;

    public JpqlQueryExecution(String jpqlQuery, String nativeQuery) {
        this.jpqlQuery = jpqlQuery;
        this.nativeQuery = nativeQuery;
    }

    public String getJpqlQuery() {
        return jpqlQuery;
    }

    public String getNativeQuery() {
        return nativeQuery;
    }

    public abstract boolean isSuccessful();
    public abstract String getResultAsString();
}
