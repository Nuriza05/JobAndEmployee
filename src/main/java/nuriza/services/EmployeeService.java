package nuriza.services;

import nuriza.models.Employee;
import nuriza.models.Job;

import java.util.List;
import java.util.Map;

/**
 * @created : Lenovo Nuriza
 **/
public interface EmployeeService {
    void createEmployee();
    void addEmployee(Employee employee);
    void dropTable();
    void cleanTable();
    void updateEmployee(Long id,Employee employee);
    List<Employee> getAllEmployees();
    Employee findByEmail(String email);
    Map<Employee, Job> getEmployeeById(Long employeeId);
    List<Employee> getEmployeeByPosition(String position);
}
