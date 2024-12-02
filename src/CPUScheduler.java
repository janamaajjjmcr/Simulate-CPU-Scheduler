import java.util.ArrayList;
import java.util.List;

public abstract class CPUScheduler {
    private final List<Row> rows; // List of processes (rows)
    private final List<Event> timeline; // List of events for the Gantt chart
    private int timeQuantum; // Time quantum for Round Robin scheduling (default = 1)
    
    public CPUScheduler() {
        rows = new ArrayList<>();
        timeline = new ArrayList<>();
        timeQuantum = 1;
    }
    
    public boolean add(Row row) {
        return rows.add(row); // Add a process row
    }
    
    public void setTimeQuantum(int timeQuantum) {
        this.timeQuantum = timeQuantum; // Set the time quantum for Round Robin
    }
    
    public int getTimeQuantum() {
        return timeQuantum; // Get the time quantum
    }
    
    public double getAverageWaitingTime() {
        double avg = 0.0;
        
        for (Row row : rows) {
            avg += row.getWaitingTime(); // Sum all waiting times
        }
        
        return avg / rows.size(); // Return average waiting time
    }
    
    public double getAverageTurnAroundTime() {
        double avg = 0.0;
        
        for (Row row : rows) {
            avg += row.getTurnaroundTime(); // Sum all turnaround times
        }
        
        return avg / rows.size(); // Return average turnaround time
    }
    
    public Event getEvent(Row row) {
        for (Event event : timeline) {
            if (row.getProcessName().equals(event.getProcessName())) {
                return event; // Return the event for the specified process
            }
        }
        return null;
    }
    
    public Row getRow(String process) {
        for (Row row : rows) {
            if (row.getProcessName().equals(process)) {
                return row; // Return the process row by name
            }
        }
        return null;
    }
    
    public List<Row> getRows() {
        return rows; // Return all process rows
    }
    
    public List<Event> getTimeline() {
        return timeline; // Return the timeline (Gantt chart events)
    }
    
    public abstract void process(); // Abstract method for scheduling logic
}
