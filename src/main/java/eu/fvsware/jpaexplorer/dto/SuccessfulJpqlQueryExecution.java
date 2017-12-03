package eu.fvsware.jpaexplorer.dto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class SuccessfulJpqlQueryExecution extends JpqlQueryExecution{

    private String jpqlQueryResult;

    public SuccessfulJpqlQueryExecution(String jpqlQuery, String nativeQuery, String jpqlQueryResult) {
        super(jpqlQuery, nativeQuery);
        this.jpqlQueryResult = jpqlQueryResult;
    }

    @Override
    public boolean isSuccessful() {
        return true;
    }

    @Override
    public String getResultAsString() {
        return jpqlQueryResult;
    }
}
