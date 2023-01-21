package nuriza.dao;

import nuriza.models.Job;

import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
public interface JobDao {
    void createJobTable();
    void addJob(Job job);
    Job getJobById(Long jobId);
    List<Job> sortByExperience(String ascOrDesc);
    Job getJobByEmployeeId(Long employeeId);
    void deleteDescriptionColumn();
}
