package nuriza.dao;

import nuriza.config.DatabaseConnection;
import nuriza.models.Employee;
import nuriza.models.Job;


import java.sql.*;
import java.util.*;

/**
 * @created : Lenovo Nuriza
 **/
public class EmployeeDaoImpl implements EmployeeDao{
    private Connection connection;
    public EmployeeDaoImpl(){
        this.connection = DatabaseConnection.getConnection();
    }
    public void createEmployee() {
        String query = "create table employees(" +
                "id serial primary key," +
                "firstName varchar not null," +
                "lastName varchar not  null," +
                "age int," +
                "email varchar unique," +
                "jobId int references jobs(id)" +
                ");";
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
            System.out.println("Table is successfully  created!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addEmployee(Employee employee) {
        String query = "insert into employees (firstName, lastName, age, email, jobId) values (?,?,?,?,?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setInt(5, employee.getJobId());
            preparedStatement.executeUpdate();
            System.out.println("Employee is successfully saved!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropTable() {
        String query = "DROP TABLE employees CASCADE;";
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
            System.out.println("Table is successfully dropped!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanTable() {
      String query = "TRUNCATE table employees;";
      try(Statement statement = connection.createStatement()){
          statement.execute(query);
          System.out.println("Table is successfully cleaned!");
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
    }

    public void updateEmployee(Long id, Employee employee) {
      String query = "UPDATE employees set firstName = ?, lastName = ?, age = ?, email = ?, jobId = ? where id = ?;";
      try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
          preparedStatement.setString(1, employee.getFirstName());
          preparedStatement.setString(2,employee.getLastName());
          preparedStatement.setInt(3,employee.getAge());
          preparedStatement.setString(4,employee.getEmail());
          preparedStatement.setInt(5,employee.getJobId());
          preparedStatement.setLong(6,id);
         int i = preparedStatement.executeUpdate();
         if(i>0){
             System.out.println("Successfully updated!");
         }
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * from employees;";
        try(Statement statement = connection.createStatement()){
           ResultSet resultSet = statement.executeQuery(query);
           while (resultSet.next()){
               employees.add(new Employee(resultSet.getLong(1),resultSet.getString(2),
                       resultSet.getString(3),resultSet.getInt(4),resultSet.getString(5),resultSet.getInt(6)));
           }
           return employees;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Employee findByEmail(String email) {
        String query = "select* from employees where email = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,email);
           ResultSet resultSet = preparedStatement.executeQuery();
            Employee employee = new Employee();
           while (resultSet.next()){
               employee.setId(resultSet.getLong(1));
               employee.setFirstName(resultSet.getString("firstName"));
               employee.setLastName(resultSet.getString("lastName"));
               employee.setEmail(resultSet.getString("email"));
               employee.setAge(resultSet.getInt("age"));
               employee.setJobId(resultSet.getInt("jobId"));
           }
           resultSet.close();
           return employee;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        Map<Employee,Job> employeeJobMap = new HashMap<>();
        Employee employee = new Employee();
        Job job = new Job();
        String query = "SELECT * from employees JOIN jobs j on j.id = employees.jobId where employees.id = ?;";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                employee.setId(resultSet.getLong("id"));
                employee.setFirstName(resultSet.getString("firstName"));
                employee.setLastName(resultSet.getString("lastName"));
                employee.setAge(resultSet.getInt("age"));
                employee.setEmail(resultSet.getString("email"));
                employee.setJobId(resultSet.getInt("jobId"));

                job.setId(resultSet.getLong("id"));
                job.setExperience(resultSet.getInt("experience"));
                job.setProfession(resultSet.getString("profession"));
                job.setDescription(resultSet.getString("description"));
                job.setPosition(resultSet.getString("position"));

                employeeJobMap.put(employee,job);
            }
            return employeeJobMap;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }return null;
    }

    public List<Employee> getEmployeeByPosition(String position) {
        List<Employee> employees = new ArrayList<>();
        String query = "select* from employees join jobs j on j.id = employees.jobId where j.position = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,position);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("id"));
                employee.setFirstName(resultSet.getString("firstName"));
                employee.setLastName(resultSet.getString("lastName"));
                employee.setEmail(resultSet.getString("email"));
                employee.setAge(resultSet.getInt("age"));
                employee.setJobId(resultSet.getInt("jobId"));
                employees.add(employee);
            }
            resultSet.close();
            return employees;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
