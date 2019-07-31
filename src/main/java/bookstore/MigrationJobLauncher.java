package bookstore;

import bookstore.exception.BookStoreException;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MigrationJobLauncher {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    public void startJob() {
        try {
            JobExecution jobExecution = jobLauncher.run(job, new JobParameters());
            BatchStatus status;
            do {
                status = jobExecution.getStatus();
            } while (status != BatchStatus.COMPLETED);
        } catch (Exception e) {
            throw new BookStoreException(e.getMessage());
        }
    }
}
