package eu.fvsware.jpaexplorer.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.Attribute;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EntityAttributeValueInfoFactory {

    public static EntityAttributeValueInfo getValue(EntityManager entityManager, Object result, Attribute attribute) {

        if(entityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(result, attribute.getName())) {
            return new EntityAttributeValueInfo(false, ToStringBuilder.reflectionToString(getAttributeValueByName(result, attribute)));
        } else {
            return new EntityAttributeValueInfo(true);
        }
    }

    private static  Object getAttributeValueByName(Object result, Attribute attribute) {
        try {
            Field declaredField = result.getClass().getDeclaredField(attribute.getJavaMember().getName());
            declaredField.setAccessible(true);
            return declaredField.get(result);
        } catch (NoSuchFieldException nse) {
            try {
                Method getter = result.getClass().getDeclaredMethod(attribute.getJavaMember().getName());
                getter.setAccessible(true);
                return getter.invoke(result);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }


    }
}
