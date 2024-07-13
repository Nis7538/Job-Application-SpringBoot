package com.project.jobapp.job;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getJobs() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return ResponseEntity.ok("Job added successfully!");
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = jobService.findById(id);
        if (job != null)
            return ResponseEntity.ok(job);

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
        boolean deleted = jobService.deleteJobById(id);
        if (deleted) {
            return ResponseEntity.ok("Job deleted successfully!");
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        boolean updated = jobService.updateJob(id, updatedJob);
        if (updated) {
            return ResponseEntity.ok("Job updated successfully!");
        }
        return ResponseEntity.notFound().build();
    }
}
