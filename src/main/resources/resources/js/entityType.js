function EntityType(entityTypeInfo) {
    var self = this;
    self.entityName = entityTypeInfo.entityName;
    self.entityClass = entityTypeInfo.entityClass;
    self.idInfo = entityTypeInfo.idInfo;
    self.attributes = entityTypeInfo.attributeInfoList;
    self.loadedEntity = ko.observable();

    self.errorMessage = ko.observable('');
    //dynamic
    self.id = ko.observable();
    self.emptyIdWarning = ko.observable(false);
    self.dismissEmptyIdWarning = function() {
        self.emptyIdWarning(false);
    }

    self.values = ko.observable();

    $.each(self.attributes, function(idx, attribute) {
        attribute.lazyLoaded = ko.computed(function() {
            if(self.values()) {
                var valueInfo = self.values()[attribute.name];
                return valueInfo.lazyLoaded;
            }
            return "";
        });
        attribute.value = ko.computed(function() {
            if(self.values()) {
                var valueInfo = self.values()[attribute.name];
                if(!valueInfo.lazyLoaded) {
                    return valueInfo.value;
                }
            }
            return "";
        });
        attribute.forceAssociationLoad = ko.observable(false);
        attribute.attributeEntityType = ko.observable();
        attribute.loadAssociation = function() {
            $.ajax("api/entity/"+self.entityName+"/"+self.id()+"/single/"+attribute.name, {
                data: null,
                type: "get",
                contentType: "application/json",
                success: function (attributeValues) {
                    console.log(attributeValues);
                    var associatedEntity = new EntityType(attributeValues.entityTypeInfo);
                    attribute.attributeEntityType(associatedEntity);
                    associatedEntity.values(attributeValues.attributeValueInfoMap);
                    attribute.forceAssociationLoad(true);
                },
                error: function(errorMessage) {
                    self.errorMessage(errorMessage.responseJSON.status + ": " + errorMessage.responseJSON.message);
                }
            });
        }
    });
    self.loadData = function() {
        if(!self.id()) {
            self.emptyIdWarning(true);
            return;
        }
        self.errorMessage('');
        self.emptyIdWarning(false);
        $.ajax("api/entity/"+self.entityName+"/"+self.id(), {
                data: null,
                type: "get",
                contentType: "application/json",
                success: function (attributeValues) {

                    self.values(attributeValues);
                },
                error: function(errorMessage) {
                    self.errorMessage(errorMessage.responseJSON.status + ": " + errorMessage.responseJSON.message);
                }
            });
    }

}