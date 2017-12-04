package eu.fvsware.jpaexplorer.controller;

import eu.fvsware.jpaexplorer.dto.EntityAttributeMetaAndValues;
import eu.fvsware.jpaexplorer.dto.EntityAttributeValueInfo;
import eu.fvsware.jpaexplorer.dto.EntityAttributeValueInfoFactory;
import eu.fvsware.jpaexplorer.dto.EntityTypeInfo;
import eu.fvsware.jpaexplorer.model.Address;
import eu.fvsware.jpaexplorer.model.Employee;
import eu.fvsware.jpaexplorer.model.Phone;
import eu.fvsware.jpaexplorer.service.EmployeeRepository;
import eu.fvsware.jpaexplorer.service.EntityTypeService;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/entity")
public class EntityController {

    @Autowired
    private EntityTypeService entityTypeService;

    @PersistenceContext
    private EntityManager entityManager;


    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping("/{entityName}/{id}")
    public Map<String, EntityAttributeValueInfo> getEntityDataByEntityNameAndId(@PathVariable("entityName") String entityName, @PathVariable("id")  String id) {
        initialize();
        Map<String, EntityAttributeValueInfo> returnMap = new HashMap<>();
        EntityType entityType = entityTypeService.getEntityByName(entityName);
        Object result = entityManager.find(entityType.getJavaType(), idTranslator(id, entityType));
        if(result == null) {
            throw  new IllegalArgumentException("No entity with that id found!");
        }
        Set<Attribute> attributes = entityType.getAttributes();
        for(Attribute attribute: attributes) {
            returnMap.put(attribute.getName(), EntityAttributeValueInfoFactory.getValue(entityManager, result, attribute));
        }
        return returnMap;
    }

    @RequestMapping("/{entityName}/{id}/single/{assoc}")
    public EntityAttributeMetaAndValues getSingleAssociationData(@PathVariable("entityName") String entityName, @PathVariable("id")  String id, @PathVariable("assoc") String assoc) {
        initialize();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        EntityType entityType = entityTypeService.getEntityByName(entityName);
        Set<Attribute> attributes = entityType.getAttributes();
        Attribute associationAttr = attributes
                .stream()
                .filter(attr -> attr.getName().equals(assoc))
                .findFirst()
                .get();
        Root from = cq.from(entityType.getJavaType());
        Object result = entityManager.find(entityType.getJavaType(), idTranslator(id, entityType));
        cq.select(from.get(assoc));
        cq.where(cb.equal(from, result));


        Map<String, EntityAttributeValueInfo> returnMap = new HashMap<>();
        List resultList = entityManager.createQuery(cq).getResultList();
        EntityType assocEntity = entityTypeService.getEntityByName(associationAttr.getJavaType().getSimpleName());
        Set<Attribute> attributes1 = assocEntity.getAttributes(); //TODO: FIX getSimpleName
        for(Attribute attribute: attributes1) {
            returnMap.put(attribute.getName(), EntityAttributeValueInfoFactory.getValue(entityManager, resultList.get(0), attribute));
        }
        return new EntityAttributeMetaAndValues(EntityTypeInfo.getFromEntityType(assocEntity), returnMap);
    }

    private Object idTranslator(String id, EntityType entityType) {
        try {
            Class javaType = entityType.getIdType().getJavaType();
            if(javaType.getSimpleName().equals("int")) {
                return Integer.parseInt(id);
            } else if(javaType.getSimpleName().equals("long")) {
                return Long.parseLong(id);
            }
            return javaType.getConstructor(String.class).newInstance(id);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private void initialize() {
        if(employeeRepository.count() == 0) {
            Employee employee = new Employee();
            Address address = new Address();
            address.setCity("Bruxelles");
            employee.setAddress(address);
            employeeRepository.save(employee);
            employee.getPhones().add(new Phone("111", employee));
            employee.getPhones().add(new Phone("112", employee));
            employee.getPhones().add(new Phone("113", employee));
        }
    }

}
