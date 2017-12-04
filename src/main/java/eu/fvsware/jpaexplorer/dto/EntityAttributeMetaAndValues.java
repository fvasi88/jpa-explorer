package eu.fvsware.jpaexplorer.dto;

import java.util.Map;

public class EntityAttributeMetaAndValues {
    private EntityTypeInfo entityTypeInfo;
    private Map<String, EntityAttributeValueInfo> attributeValueInfoMap;

    public EntityAttributeMetaAndValues(EntityTypeInfo entityTypeInfo, Map<String, EntityAttributeValueInfo> attributeValueInfoMap) {
        this.entityTypeInfo = entityTypeInfo;
        this.attributeValueInfoMap = attributeValueInfoMap;
    }

    public EntityTypeInfo getEntityTypeInfo() {
        return entityTypeInfo;
    }

    public Map<String, EntityAttributeValueInfo> getAttributeValueInfoMap() {
        return attributeValueInfoMap;
    }
}
