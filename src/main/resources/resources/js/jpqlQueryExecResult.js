function JpqlQueryExecResultVM() {
    var self = this;

    self.nativeQuery = ko.observable();
    self.result = ko.observable();
    self.executedQuery = ko.observable();
    self.successful = ko.observable();
    self.ran = ko.pureComputed( function() {
        return self.executedQuery() != null;
    });

    self.shouldDisplayError = function() {
        return self.ran() && self.successful() === false;
    };

    self.processResult = function(executionResult) {
        self.nativeQuery(executionResult.nativeQuery);
        self.successful(executionResult.successful);
        self.executedQuery(executionResult.jpqlQuery);
        self.result(executionResult.resultAsString);
    }

}