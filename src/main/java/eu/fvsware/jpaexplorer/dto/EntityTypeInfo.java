package eu.fvsware.jpaexplorer.dto;


import javax.persistence.metamodel.EntityType;

public class EntityTypeInfo {
    private String entityName;
    private String entityClass;

    public String getEntityName() {
        return entityName;
    }

    public String getEntityClass() {
        return entityClass;
    }

    public static EntityTypeInfo getFromEntityType(EntityType<?> entityType) {
        EntityTypeInfo entityTypeInfo = new EntityTypeInfo();
        entityTypeInfo.entityClass = entityType.getJavaType().getTypeName();
        entityTypeInfo.entityName = entityType.getName();

        return entityTypeInfo;
    }
}
