package nuriza.services;

import nuriza.dao.JobDao;
import nuriza.dao.JobDaoImpl;
import nuriza.models.Job;

import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
public class JobServiceImpl implements JobService{
    JobDao jobDao = new JobDaoImpl();
    @Override
    public void createJobTable() {
        jobDao.createJobTable();
    }

    @Override
    public void addJob(Job job) {
        jobDao.addJob(job);

    }

    @Override
    public Job getJobById(Long jobId) {
        return jobDao.getJobById(jobId);
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        return jobDao.sortByExperience(ascOrDesc);
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        return jobDao.getJobByEmployeeId(employeeId);
    }

    @Override
    public void deleteDescriptionColumn() {
       jobDao.deleteDescriptionColumn();
    }
}
