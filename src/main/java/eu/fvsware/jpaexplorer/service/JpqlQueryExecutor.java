package eu.fvsware.jpaexplorer.service;

import eu.fvsware.jpaexplorer.dto.FailedJpqlExecution;
import eu.fvsware.jpaexplorer.dto.JpqlQueryExecution;
import eu.fvsware.jpaexplorer.dto.SuccessfulJpqlQueryExecution;
import eu.fvsware.jpaexplorer.model.Address;
import eu.fvsware.jpaexplorer.model.Employee;
import eu.fvsware.jpaexplorer.model.Phone;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.hql.internal.ast.ASTQueryTranslatorFactory;
import org.hibernate.hql.spi.QueryTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.joining;

@Service
public class JpqlQueryExecutor {


    @PersistenceContext
    private EntityManager em;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public JpqlQueryExecution executeQuery(String jpqlQuery) {
        initialize();
        String nativeQuery = null;
        try {
            Query query = em.createQuery(jpqlQuery);
            nativeQuery = getNativeQuery(query);
            List<Object> resultList = query.getResultList();
            String result = resultList
                    .stream()
                    .map(ToStringBuilder::reflectionToString)
                    .collect(joining("\n"));
            return new SuccessfulJpqlQueryExecution(jpqlQuery, nativeQuery, result);
        } catch (Exception e) {
            return new FailedJpqlExecution(jpqlQuery, nativeQuery, e.getMessage());
        }


    }

    private String getNativeQuery(Query query) {
        String hqlQueryString = query.unwrap(org.hibernate.Query.class).getQueryString();
        ASTQueryTranslatorFactory queryTranslatorFactory = new ASTQueryTranslatorFactory();
        SessionImplementor hibernateSession = em.unwrap(SessionImplementor.class);
        QueryTranslator queryTranslator = queryTranslatorFactory.createQueryTranslator("", hqlQueryString, java.util.Collections.EMPTY_MAP, hibernateSession.getFactory(), null);
        queryTranslator.compile(java.util.Collections.EMPTY_MAP, false);
        return queryTranslator.getSQLString();
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
