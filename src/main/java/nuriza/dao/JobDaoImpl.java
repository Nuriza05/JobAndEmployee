package nuriza.dao;

import nuriza.config.DatabaseConnection;
import nuriza.models.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
public class JobDaoImpl implements JobDao {
    private Connection connection;

    public JobDaoImpl() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void createJobTable() {
        String query = "create table jobs (" +
                "id serial primary key," +
                "position varchar not null," +
                "profession varchar not null," +
                "description varchar not null," +
                "experience int" +
                ");";

        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
            System.out.println("Table is successfully created!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void addJob(Job job) {
        String query = "insert into jobs (position, profession,description,experience) values (?,?,?,?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, job.getPosition());
            preparedStatement.setString(2, job.getProfession());
            preparedStatement.setString(3, job.getDescription());
            preparedStatement.setInt(4, job.getExperience());
            preparedStatement.execute();
            System.out.println("Job is successfully saved!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Job getJobById(Long jobId) {
        String query = "select * from jobs where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, jobId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Job job = new Job();
            while (resultSet.next()) {
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString(2));
                job.setProfession(resultSet.getString(3));
                job.setDescription(resultSet.getString(4));
                job.setExperience(resultSet.getInt(5));
            }
            resultSet.close();
            return job;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Job> sortByExperience(String ascOrDesc) {
        List<Job> jobs = new ArrayList<>();
        String query = null;
        if (ascOrDesc.equals("asc")) {
            query = "select * from jobs order by experience;";
        } else if (ascOrDesc.equals("desc")) {
            query = "select * from jobs order by experience desc;";
        }
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                jobs.add(new Job(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5)));
            }
            return jobs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Job getJobByEmployeeId(Long employeeId) {
        String query = "select * from jobs join employees e on jobs.id = e.jobId where e.id = ?; ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Job job = new Job();
            while (resultSet.next()) {
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("position"));
                job.setProfession(resultSet.getString("profession"));
                job.setDescription(resultSet.getString("description"));
                job.setExperience(resultSet.getInt("experience"));
            }
            resultSet.close();
            return job;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteDescriptionColumn() {
        String query = "alter table jobs drop column description;";
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
            System.out.println("Column is successfully deleted!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
