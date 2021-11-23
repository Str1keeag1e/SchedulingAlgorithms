import java.util.*;

public class RoundRobinScheduler {
	
	int timeClock = 0;
	int timeQuanta;
	int numberOfJobs;
	List<Job> jobs;

	Queue<Job> jobQueue = new LinkedList<>();

	RoundRobinScheduler(int timeQuanta, List<Job> jobs){
		this.timeQuanta = timeQuanta;
		this.numberOfJobs = jobs.size();
		this.jobs = new ArrayList<>();
		for(Job job : jobs){
			this.jobs.add(new Job(job.getJobID(), job.getArrivalTime(), job.getServiceTime()));
		}
	}


	public void runJobs(){

		addJobsToQueue();
		while(numberOfJobs > 0){
			Job currentJob = jobQueue.poll();
			if(jobQueue.isEmpty()) {
				while(jobQueue.isEmpty() && currentJob.getServiceTime() != 0) {
						processJob(currentJob);
					}
				}
			else{
				processJob(currentJob);
			}
			if(currentJob.getServiceTime() != 0)
				jobQueue.add(currentJob);
		}

	}
	public void addJobsToQueue(){
		for(Job job : jobs){
			if(job.getArrivalTime() == timeClock){
				//System.out.println("Adding job: " + job.getJobID() + " at time: " + timeClock);
				jobQueue.add(job);
			}
		}
	}

	public void processJob(Job job){

		for( int i = 0; i < timeQuanta; i++) {
			job.decrementServiceTime();

			printJob(job.getJobID());


			if (job.getServiceTime() == 0) {
				numberOfJobs--;
			}
			timeClock++;
			addJobsToQueue();
		}
	}

	public void printJob(char jobID){
		for(int j = 'A'; j < 'Z'; j++ ){
			if(j == jobID){
				System.out.print(jobID);
			}
			System.out.print("\t");
		}
		System.out.println();
	}
}
