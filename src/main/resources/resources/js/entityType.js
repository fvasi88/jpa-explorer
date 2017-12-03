function EntityType(entityTypeInfo) {
    var self = this;
    self.entityName = entityTypeInfo.entityName;
    self.entityClass = entityTypeInfo.entityClass;
    self.idInfo = entityTypeInfo.idInfo;
    self.attributes = entityTypeInfo.attributeInfoList;
}