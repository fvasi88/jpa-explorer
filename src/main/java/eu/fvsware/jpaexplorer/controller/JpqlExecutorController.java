package eu.fvsware.jpaexplorer.controller;

import eu.fvsware.jpaexplorer.dto.JpqlQueryExecution;
import eu.fvsware.jpaexplorer.service.JpqlQueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/executeJpqlQuery")
public class JpqlExecutorController {

    @Autowired
    private JpqlQueryExecutor jpqlQueryExecutor;

    @RequestMapping(method = RequestMethod.POST)
    public JpqlQueryExecution executeJpqlQuery(@RequestBody String query) {
        return jpqlQueryExecutor.executeQuery(query);
    }

}
