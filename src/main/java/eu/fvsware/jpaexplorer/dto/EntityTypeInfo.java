package eu.fvsware.jpaexplorer.dto;


import javax.persistence.metamodel.EntityType;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class EntityTypeInfo {
    private String entityName;
    private String entityClass;
    private IdInfo idInfo;
    private List<AttributeInfo> attributeInfoList;

    public String getEntityName() {
        return entityName;
    }

    public String getEntityClass() {
        return entityClass;
    }

    public IdInfo getIdInfo() {
        return idInfo;
    }

    public List<AttributeInfo> getAttributeInfoList() {
        return attributeInfoList;
    }

    public static EntityTypeInfo getFromEntityType(EntityType<?> entityType) {
        EntityTypeInfo entityTypeInfo = new EntityTypeInfo();
        entityTypeInfo.entityClass = entityType.getJavaType().getTypeName();
        entityTypeInfo.entityName = entityType.getName();

        entityTypeInfo.idInfo = IdInfo.fromIdType(entityType.getIdType());

        entityTypeInfo.attributeInfoList = entityType
                .getAttributes()
                .stream()
                .map(AttributeInfo::fromAttributeType)
                .collect(toList());

        return entityTypeInfo;
    }
}
