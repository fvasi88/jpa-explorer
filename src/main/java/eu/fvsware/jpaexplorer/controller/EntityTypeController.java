package eu.fvsware.jpaexplorer.controller;

import eu.fvsware.jpaexplorer.dto.EntityTypeInfo;
import eu.fvsware.jpaexplorer.service.EntityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.metamodel.Metamodel;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/entityType")
public class EntityTypeController {

    @Autowired
    private EntityTypeService entityTypeService;

    @RequestMapping
    public List<EntityTypeInfo> getAllEntityTypes() {
        return entityTypeService.getAllEntityTypes();
    }

}
