public class Row {
    private String processName;
    private int arrivalTime;
    private int burstTime;
    private int priorityLevel;
    private int waitingTime;
    private int turnaroundTime;

    // Full constructor
    private Row(String processName, int arrivalTime, int burstTime, int priorityLevel, int waitingTime, int turnaroundTime) {
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priorityLevel = priorityLevel;
        this.waitingTime = waitingTime;
        this.turnaroundTime = turnaroundTime;
    }

    // Constructor with priority level
    public Row(String processName, int arrivalTime, int burstTime, int priorityLevel) {
        this(processName, arrivalTime, burstTime, priorityLevel, 0, 0);
    }

    // Constructor without priority level (defaults to 0)
    public Row(String processName, int arrivalTime, int burstTime) {
        this(processName, arrivalTime, burstTime, 0, 0, 0);
    }

    // Setter for burst time
    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    // Setter for waiting time
    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    // Setter for turnaround time
    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    // Getter for process name
    public String getProcessName() {
        return this.processName;
    }

    // Getter for arrival time
    public int getArrivalTime() {
        return this.arrivalTime;
    }

    // Getter for burst time
    public int getBurstTime() {
        return this.burstTime;
    }

    // Getter for priority level
    public int getPriorityLevel() {
        return this.priorityLevel;
    }

    // Getter for waiting time
    public int getWaitingTime() {
        return this.waitingTime;
    }

    // Getter for turnaround time
    public int getTurnaroundTime() {
        return this.turnaroundTime;
    }
}
