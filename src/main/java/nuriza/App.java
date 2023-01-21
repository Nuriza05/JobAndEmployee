package nuriza;

import nuriza.config.DatabaseConnection;
import nuriza.dao.EmployeeDao;
import nuriza.models.Employee;
import nuriza.models.Job;
import nuriza.services.EmployeeService;
import nuriza.services.EmployeeServiceImpl;
import nuriza.services.JobService;
import nuriza.services.JobServiceImpl;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
//        DatabaseConnection.getConnection();
        EmployeeService empService = new EmployeeServiceImpl();
        JobService jobService = new JobServiceImpl();
        while (true) {
            System.out.println("1. Create table jobs: \n" +
                    "2. Add job:\n" +
                    "3. Get job by id:\n" +
                    "4. Sort the jobs by experience:\n" +
                    "5. Get job by employee id:\n" +
                    "6. Delete description column:\n" +
                    "7. Create the employee table:\n" +
                    "8. Add employee:\n" +
                    "9. Drop table employee:\n" +
                    "10. Clean table employee:\n" +
                    "11. Update Employee:\n" +
                    "12. Get all employee:\n" +
                    "13. Find by email:\n" +
                    "14. Get employee by id:\n" +
                    "15. Get employee by position:\n");
            int number = new Scanner(System.in).nextInt();
            switch (number) {
                case 1 -> jobService.createJobTable();
                case 2 -> {
                    System.out.print("Write the position: ");
                    String position = new Scanner(System.in).next();
                    System.out.print("Write the profession: ");
                    String prof = new Scanner(System.in).next();
                    System.out.print("Write the description: ");
                    String descr = new Scanner(System.in).next();
                    System.out.print("Write the experience: ");
                    int exp = new Scanner(System.in).nextInt();
                    jobService.addJob(new Job(position, prof, descr, exp));
                }
                case 3 -> {
                    System.out.print("Write the job id: ");
                    long id = new Scanner(System.in).nextLong();
                    System.out.println(jobService.getJobById(id));
                }
                case 4 -> {
                    System.out.print("Write the asc or desc: ");
                    String ascOrDesc = new Scanner(System.in).next();
                    System.out.println(jobService.sortByExperience(ascOrDesc));
                }
                case 5 -> {
                    System.out.print("Write the employee id: ");
                    Long id = new Scanner(System.in).nextLong();
                    System.out.println(jobService.getJobByEmployeeId(id));
                }
                case 6 -> jobService.deleteDescriptionColumn();
                case 7 -> empService.createEmployee();
                case 8 -> {
                    System.out.print("Write the first name: ");
                    String firstName = new Scanner(System.in).next();
                    System.out.print("Write the last name: ");
                    String lastName = new Scanner(System.in).next();
                    System.out.print("Write the age: ");
                    int age = new Scanner(System.in).nextInt();
                    System.out.print("Write the email: ");
                    String email = new Scanner(System.in).next();
                    System.out.print("Write the jobId: ");
                    int jobId = new Scanner(System.in).nextInt();
                    empService.addEmployee(new Employee(firstName, lastName, age, email, jobId));
                }
                case 9 -> empService.dropTable();
                case 10 -> empService.cleanTable();
                case 11 -> {
                    System.out.print("Write the id that which you want to change: ");
                    long id = new Scanner(System.in).nextLong();
                    System.out.print("Write the first name: ");
                    String firstName = new Scanner(System.in).next();
                    System.out.print("Write the last name: ");
                    String lastName = new Scanner(System.in).next();
                    System.out.print("Write the age: ");
                    int age = new Scanner(System.in).nextInt();
                    System.out.print("Write the email: ");
                    String email = new Scanner(System.in).next();
                    System.out.print("Write the jobId: ");
                    int jobId = new Scanner(System.in).nextInt();
                    empService.updateEmployee(id, new Employee(firstName, lastName, age, email, jobId));
                }
                case 12 -> System.out.println(empService.getAllEmployees());
                case 13 -> {
                    System.out.print("Write the email: ");
                    String email = new Scanner(System.in).next();
                    System.out.println(empService.findByEmail(email));
                }
                case 14 -> {
                    System.out.print("Write the id: ");
                    long id = new Scanner(System.in).nextLong();
                    System.out.println(empService.getEmployeeById(id));
                }
                case 15 -> {
                    System.out.print("Write the position: ");
                    String position = new Scanner(System.in).next();
                    System.out.println(empService.getEmployeeByPosition(position));
                }
            }
        }
    }
}
