package eu.fvsware.jpaexplorer.controller;

import eu.fvsware.jpaexplorer.dto.EntityTypeInfo;
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

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @RequestMapping
    public List<EntityTypeInfo> getAllEntityTypes() {
        Metamodel metamodel = entityManagerFactory.getMetamodel();
        return metamodel
                .getEntities()
                .stream()
                .map(EntityTypeInfo::getFromEntityType)
                .collect(toList());
    }

}
