function JPAExplorerViewModel() {
    var self = this;

    self.jpqlQuery = ko.observable();
    self.emptyJpqlQueryWarning = ko.observable(false);
    self.dirtyJpqlQueryBox = ko.observable(false);
    self.jpqlQueryExecResult = new JpqlQueryExecResultVM();
    self.quickInfoEntity = ko.observable();

    self.entityTypes = ko.observable();
    $.ajax("api/entityType", {
        data: null,
        type: "get",
        contentType: "application/json",
        success: function (allEntityTypes) {
            var entityTypes = [];

            $.each(allEntityTypes, function(idx, entityType) {
                entityTypes.push(new EntityType(entityType));
            });
            self.entityTypes(entityTypes);
        }
    });
    self.selectEntityTypeForQuickView = function(entityType) {
        self.quickInfoEntity(entityType);
    }
    self.computeStatusStyle = ko.computed(function() {

        if(self.jpqlQueryExecResult.ran()) {
            if(self.jpqlQueryExecResult.executedQuery() != self.jpqlQuery()) {
                return "panel-warning";
            } else if(self.jpqlQueryExecResult.successful()) {
               return "panel-success";
            } else {
                return "panel-danger";
            }
        }
        return "panel-default";
    });

    self.dismissEmptyWarning = function() {
        self.emptyJpqlQueryWarning(false);
    }
    self.jpqlQuery.subscribe(function(newVal) {
        if(newVal && self.emptyJpqlQueryWarning()) {
            self.emptyJpqlQueryWarning(false);
        }
        self.dirtyJpqlQueryBox(true);
    });
    self.executeQuery = function() {
        if(!self.jpqlQuery()) {
            self.emptyJpqlQueryWarning(true);
            return;
        }
        self.dirtyJpqlQueryBox(false);
        $.ajax("api/executeJpqlQuery", {
                data: self.jpqlQuery(),
                type: "post",
                contentType: "application/json",
                success: function (executionResult) {
                    self.jpqlQueryExecResult.processResult(executionResult);
                }
            });

    }



    /*
    "jpqlQuery": "select e from Employee ",
    "nativeQuery": null,
    "successful": false,
    "resultAsString"*/
}


// Activates knockout.js
ko.applyBindings(new JPAExplorerViewModel());