package eu.fvsware.jpaexplorer.service;

import eu.fvsware.jpaexplorer.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
