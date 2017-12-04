package eu.fvsware.jpaexplorer.service;

import eu.fvsware.jpaexplorer.dto.EntityTypeInfo;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class EntityTypeService {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    public List<EntityTypeInfo> getAllEntityTypes() {
        Metamodel metamodel = entityManagerFactory.getMetamodel();
        return metamodel
                .getEntities()
                .stream()
                .map(EntityTypeInfo::getFromEntityType)
                .collect(toList());
    }


    public EntityType getEntityByName(String entityName) {
        Metamodel metamodel = entityManagerFactory.getMetamodel();
        return metamodel
                .getEntities()
                .stream()
                .filter(entityType -> entityName.equals(entityType.getName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Couldn't find entity by name " + entityName));
    }

}
