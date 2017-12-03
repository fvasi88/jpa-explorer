package eu.fvsware.jpaexplorer.dto;

import javax.persistence.metamodel.Type;

public class IdInfo {
    private String idType;
    private String persistenceType;

    public IdInfo(String idType, String persistenceType) {
        this.idType = idType;
        this.persistenceType = persistenceType;
    }

    public String getIdType() {
        return idType;
    }

    public String getPersistenceType() {
        return persistenceType;
    }

    public static IdInfo fromIdType(Type idType) {
        return new IdInfo(idType.getJavaType().getName(), idType.getPersistenceType().toString().toLowerCase()) ;
    }
}
