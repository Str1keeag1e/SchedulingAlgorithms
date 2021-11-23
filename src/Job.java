public class Job {

	char jobID;
	int arrivalTime;
	int serviceTime;

	public Job(char jobID, int arrivalTime, int serviceTime){
		this.jobID = jobID;
		this.arrivalTime = arrivalTime;
		this.serviceTime = serviceTime;

	}


	public char getJobID() {
		return jobID;
	}

	public void setJobID(char jobID) {
		this.jobID = jobID;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public int decrementServiceTime() { return serviceTime--;}

	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}

	public String toString(){
		return getJobID() + "\t" + getArrivalTime() + "\t" + getServiceTime();
	}
}
