package eu.fvsware.jpaexplorer.controller;

import eu.fvsware.jpaexplorer.dto.EntityAttributeValueInfo;
import eu.fvsware.jpaexplorer.dto.EntityAttributeValueInfoFactory;
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
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
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
        Set<Attribute> attributes = entityType.getAttributes();
        for(Attribute attribute: attributes) {
            returnMap.put(attribute.getName(), EntityAttributeValueInfoFactory.getValue(entityManager, result, attribute));
        }
        return returnMap;
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
