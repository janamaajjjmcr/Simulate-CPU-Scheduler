the important part of the code with description
1the first Java code represents an abstract class, CPUScheduler, designed to implement various CPU scheduling algorithms. It contains methods and data structures for managing processes, computing metrics, and maintaining the execution timeline. Let's break it down:
________________________________________
1. Class Fields:
•	  private final List<Row> rows; // List of processes (rows)
•	  private final List<Event> timeline; // List of events for the Gantt chart
•	  private int timeQuantum; // Time quantum for Round Robin scheduling (default = 1)
•	rows: A list of Row objects, where each row represents a process in the scheduling system. It stores details like process ID, arrival time, burst time, waiting time, and turnaround time.
•	timeline: A list of Event objects used to construct the Gantt chart, which visualizes the scheduling of processes over time.
•	timeQuantum: An integer representing the time quantum for Round Robin scheduling. Defaults to 1.
________________________________________
2. Constructor:
    public CPUScheduler() {
        rows = new ArrayList<>();
        timeline = new ArrayList<>();
        timeQuantum = 1;
    }
•	Initializes the rows and timeline lists.
•	Sets the default timeQuantum to 1.

________________________________________
3. Methods:
a. Adding and Managing Processes
    public boolean add(Row row) {
        return rows.add(row); // Add a process row
    }
•	Adds a Row (process) to the rows list. Returns true if the addition is successful.
    public Row getRow(String process) {
        for (Row row : rows) {
            if (row.getProcessName().equals(process)) {
                return row; // Return the process row by name
            }
        }
        return null;
    }
•	Retrieves a process (row) by its name from the rows list.
b. Time Quantum Management
    public void setTimeQuantum(int timeQuantum) {
        this.timeQuantum = timeQuantum; // Set the time quantum for Round Robin
    }
    
    public int getTimeQuantum() {
        return timeQuantum; // Get the time quantum
    }
•	setTimeQuantum: Updates the time quantum for Round Robin scheduling.
•	getTimeQuantum: Retrieves the current value of the time quantum.
c. Timeline Management
    public Event getEvent(Row row) {
        for (Event event : timeline) {
            if (row.getProcessName().equals(event.getProcessName())) {
                return event; // Return the event for the specified process
            }
        }
        return null;
    }

    public List<Event> getTimeline() {
        return timeline; // Return the timeline (Gantt chart events)
    }
•	getEvent: Finds the Event associated with a specific process.
•	getTimeline: Returns the complete timeline (list of events).
d. Computing Metrics
    public double getAverageWaitingTime() {
        double avg = 0.0;
        
        for (Row row : rows) {
            avg += row.getWaitingTime(); // Sum all waiting times
        }
        
        return avg / rows.size(); // Return average waiting time
    }
•	Computes the average waiting time by summing up waiting times for all processes and dividing by the total number of processes.
    public double getAverageTurnAroundTime() {
        double avg = 0.0;.34
        
        for (Row row : rows) {
            avg += row.getTurnaroundTime(); // Sum all turnaroundtimes
        }
        
        return avg / rows.size(); // Return average turnaround time
    }
•	Computes the average turnaround time similarly by summing all turnaround times and dividing by the total number of processes.
________________________________________


4. Abstract Method:
    public abstract void process(); // Abstract method for scheduling logic
}
•	Declares an abstract method process for subclasses to implement specific CPU scheduling logic (e.g., FCFS, SJF, Round Robin, etc.).
________________________________________
Key Points:
1.	Abstraction: This is a base class meant to be extended by concrete scheduling algorithms. Each subclass must implement the process method to define its scheduling behavior.
2.	Modularity: By separating the timeline (Event) and process rows (Row), the design is flexible for adding, retrieving, and processing data.
3.	Metrics Calculation: The methods for waiting time and turnaround time ensure that subclasses don't need to implement these common operations.
________________________________________
Usage:
A developer can extend CPUScheduler to create a specific scheduler like FCFSScheduler, SJFScheduler, or RoundRobinScheduler. For example:
public class FCFSScheduler extends CPUScheduler {
    @Override
    public void process() {
        // Implementation of FCFS scheduling logic
    }
}
This abstract design promotes code reuse, clarity, and separation of concerns in implementing CPU scheduling algorithms.


2 the second’ Main Class’ Java program serves as a simulation of three popular CPU scheduling algorithms: First-Come-First-Serve (FCFS), Shortest Job First (SJF), and Round Robin (RR). Here's a detailed explanation:
________________________________________
Main Class Overview:
The Main class:
1.	Runs three scheduling algorithms (fcfs, sjf, rr) with predefined processes.
2.	Displays the results (process details, Gantt chart, and averages) for each scheduling algorithm.
________________________________________
Methods in the Main Class:
1. main()
    public static void main(String[] args) {
        System.out.println("-----------------FCFS----------------");
        fcfs();
        System.out.println("-----------------SJF-----------------");
        sjf();
        System.out.println("-----------------RR------------------");
        rr();
    }
•	Purpose: Acts as the program's entry point. It: 
o	Prints headers to distinguish between the three algorithms.
o	Invokes the methods (fcfs, sjf, rr) for testing and displaying results for each scheduling strategy.
________________________________________
2. fcfs()
    public static void fcfs() {
        CPUScheduler fcfs = new FirstComeFirstServe();
        fcfs.add(new Row("P1", 0, 5));
        fcfs.add(new Row("P2", 2, 4));
        fcfs.add(new Row("P3", 4, 3));
        fcfs.add(new Row("P4", 6, 6));
        fcfs.process();
        display(fcfs);
    }
•	Purpose: Simulates First-Come-First-Serve (FCFS) scheduling.
•	Steps: 
1.	Creates an instance of FirstComeFirstServe (a subclass of CPUScheduler implementing FCFS logic).
2.	Adds processes (rows) to the scheduler, each with: 
	Process name: P1, P2, etc.
	Arrival time (AT): When the process arrives.
	Burst time (BT): How long the process takes to execute.
3.	Calls process() to execute the FCFS algorithm.
4.	Uses display() to print results.
________________________________________
3. sjf()
    public static void sjf() {
        CPUScheduler sjf = new ShortestJobFirst();
        sjf.add(new Row("P1", 0, 5));
        sjf.add(new Row("P2", 2, 3));
        sjf.add(new Row("P3", 4, 2));
        sjf.add(new Row("P4", 6, 4));
        sjf.add(new Row("P5", 7, 1));
        sjf.process();
        display(sjf);
    }
•	Purpose: Simulates Shortest Job First (SJF) scheduling.
•	Steps: 
1.	Creates an instance of ShortestJobFirst (another subclass of CPUScheduler implementing SJF logic).
2.	Adds processes with varying arrival times and burst times.
3.	Executes the SJF scheduling algorithm.
4.	Prints results via display().
________________________________________
4. rr()
    public static void rr() {
        CPUScheduler rr = new RoundRobin();
        rr.setTimeQuantum(2);
        rr.add(new Row("P1", 0, 4));
        rr.add(new Row("P2", 1, 5));
        rr.add(new Row("P3", 2, 6));
        rr.add(new Row("P4", 4, 1));
        rr.add(new Row("P5", 6, 3));
        rr.add(new Row("P6", 7, 2));
        rr.process();
        display(rr);
    }
•	Purpose: Simulates Round Robin (RR) scheduling.
•	Steps: 
1.	Creates an instance of RoundRobin (a subclass of CPUScheduler implementing RR logic).
2.	Sets the time quantum (a key parameter in RR) to 2.
3.	Adds multiple processes with different arrival and burst times.
4.	Executes the RR scheduling algorithm.
5.	Displays the results.
________________________________________
5. display(CPUScheduler object)
    public static void display(CPUScheduler object) {
        System.out.println("Process\tAT\tBT\tWT\tTAT");

        for (Row row : object.getRows()) {
            System.out.println(row.getProcessName() + "\t" + row.getArrivalTime() + "\t" + row.getBurstTime() + "\t" + row.getWaitingTime() + "\t" + row.getTurnaroundTime());
        }

        System.out.println();

        for (int i = 0; i < object.getTimeline().size(); i++) {
            List<Event> timeline = object.getTimeline();
            System.out.print(timeline.get(i).getStartTime() + "(" + timeline.get(i).getProcessName() + ")");

            if (i == object.getTimeline().size() - 1) {
                System.out.print(timeline.get(i).getFinishTime());
            }
        }

        System.out.println("\n\nAverage WT: " + object.getAverageWaitingTime() + "\nAverage TAT: " + object.getAverageTurnAroundTime());
    }
}
•	Purpose: Displays the results of a scheduling simulation.
•	Steps: 
1.	Prints the table header: Process Name (P), Arrival Time (AT), Burst Time (BT), Waiting Time (WT), Turnaround Time (TAT).
2.	Loops through all rows (processes) in the scheduler and prints their data.
3.	Prints the Gantt chart: 
	Iterates through the timeline list and displays each event in the format StartTime(ProcessName)FinishTime.
4.	Prints the average waiting time (WT) and average turnaround time (TAT), calculated using the methods from CPUScheduler.
________________________________________
Summary:
•	This program organizes CPU scheduling algorithms under the CPUScheduler abstraction.
•	It tests and compares FCFS, SJF, and RR using predefined input data.
•	The display() method provides a clear representation of results, including process details, a Gantt chart, and average metrics.
•	Each scheduling algorithm is implemented in a subclass (FirstComeFirstServe, ShortestJobFirst, RoundRobin), encapsulating their specific logic.

 
3 the third program implements a GUI-based CPU Scheduler Simulator for simulating various scheduling algorithms such as FCFS, SJF, and Round Robin (RR). The GUI lets users input processes and interactively compute scheduling metrics and visualize the timeline.
________________________________________
Key Components of the Code
1. JFrame and Main Panel
•	The frame serves as the main application window.
•	mainPanel holds all GUI components, including the table, buttons, labels, and the Gantt chart visualization.
________________________________________
2. Process Table (JTable)
        model = new DefaultTableModel(new String[] { "Process", "AT", "BT", "WT", "TAT" }, 0);

        table = new JTable(model);
        table.setFillsViewportHeight(true);
        tablePane = new JScrollPane(table);
•	Purpose: Displays the list of processes with their Arrival Time (AT), Burst Time (BT), Waiting Time (WT), and Turnaround Time (TAT).
•	Users can: 
o	Add rows using the "Add" button.
o	Remove selected rows using the "Remove" button.
________________________________________
3. Algorithm Selection and Compute Button
        option = new JComboBox<>(new String[] { "FCFS", "SJF", "RR" });
        computeBtn = new JButton("Compute");
•	Algorithm Selection (JComboBox): Allows users to choose between FCFS, SJF, or RR.
•	Compute Button: 
o	Retrieves selected scheduling algorithm.
o	Reads process data (from the table).
o	Executes the chosen scheduling algorithm and calculates WT and TAT.
o	Updates the table with computed values and draws the Gantt chart.
________________________________________
4. Gantt Chart Visualization (CustomPanel)
    class CustomPanel extends JPanel {
        private List<Event> timeline;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (timeline != null) {
                for (int i = 0; i < timeline.size(); i++) {
                    Event event = timeline.get(i);
                    int x = 30 * (i + 1);
                    int y = 20;

                    g.drawRect(x, y, 30, 30);
                    g.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    g.drawString(event.getProcessName(), x + 10, y + 20);
                    g.setFont(new Font("Segoe UI", Font.PLAIN, 11));
                    g.drawString(Integer.toString(event.getStartTime()), x - 5, y + 45);

                    if (i == timeline.size() - 1) {
                        g.drawString(Integer.toString(event.getFinishTime()), x + 27, y + 45);
                    }
                }
            }
        }
•	Purpose: Visually represents the execution timeline for processes as a Gantt chart.
•	Key Features: 
o	Draws rectangles for each process.
o	Displays: 
	Process name inside the rectangle.
	Start and finish times below it.
o	Dynamically updates when the timeline is set using setTimeline().
________________________________________
5. Compute Logic (ActionListener for computeBtn)
        computeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) option.getSelectedItem();
                CPUScheduler scheduler;

                switch (selected) {
                    case "FCFS":
                        scheduler = new FirstComeFirstServe();
                        break;
                    case "SJF":
                        scheduler = new ShortestJobFirst();
                        break;
                    case "RR":
                        String tq = JOptionPane.showInputDialog("Time Quantum");
                        if (tq == null) {
                            return;
                        }
                        scheduler = new RoundRobin();
                        scheduler.setTimeQuantum(Integer.parseInt(tq));
                        break;
                    default:
                        return;
                }

                for (int i = 0; i < model.getRowCount(); i++) {
                    String process = (String) model.getValueAt(i, 0);
                    int at = Integer.parseInt((String) model.getValueAt(i, 1));
                    int bt = Integer.parseInt((String) model.getValueAt(i, 2));

                    scheduler.add(new Row(process, at, bt));
                }

                scheduler.process();

                for (int i = 0; i < model.getRowCount(); i++) {
                    String process = (String) model.getValueAt(i, 0);
                    Row row = scheduler.getRow(process);
                    model.setValueAt(row.getWaitingTime(), i, 3);
                    model.setValueAt(row.getTurnaroundTime(), i, 4);
                }

                wtResultLabel.setText(Double.toString(scheduler.getAverageWaitingTime()));
                tatResultLabel.setText(Double.toString(scheduler.getAverageTurnAroundTime()));

                chartPanel.setTimeline(scheduler.getTimeline());
            }
        });
•	Steps: 
1.	Determines the selected algorithm.
2.	For Round Robin, prompts for the time quantum via JOptionPane.
3.	Reads process data from the table and populates the scheduler.
4.	Executes the scheduling algorithm (process()).
5.	Updates: 
	Waiting Time (WT) and Turnaround Time (TAT) in the table.
	Average WT and TAT in the labels.
	Gantt chart timeline.
________________________________________
6. Labels for Results
        wtLabel = new JLabel("Average Waiting Time:");
        tatLabel = new JLabel("Average Turn Around Time:");
        wtResultLabel = new JLabel();
        tatResultLabel = new JLabel();
•	Purpose: Display the computed average Waiting Time (WT) and Turnaround Time (TAT).
________________________________________
How It Works
1.	Launch GUI: 
o	The program creates and displays the GUI using new GUI().
2.	Add Processes: 
o	Users add processes to the table (columns: Process, AT, BT).
3.	Select Algorithm: 
o	Choose FCFS, SJF, or RR from the dropdown menu.
4.	Compute: 
o	Click Compute to: 
	Calculate and display WT, TAT, and Gantt chart.
5.	Visualize Results: 
o	Table updates with WT and TAT for each process.
o	Gantt chart dynamically renders the process timeline.
________________________________________
Summary
•	This program creates an interactive CPU Scheduling Simulator with a user-friendly GUI.
•	It integrates: 
o	Table for input/output of processes and their attributes.
o	Buttons for user interaction.
o	ComboBox for algorithm selection.
o	CustomPanel for Gantt chart visualization.
o	Labels for displaying computed averages.
•	The compute logic ties everything together, allowing users to visualize scheduling results for different algorithms.

4 The fourth code implements the First Come, First Serve (FCFS) scheduling algorithm as a subclass of the abstract CPUScheduler class. FCFS is a simple CPU scheduling technique where processes are executed in the order of their arrival times.
________________________________________
Key Components and Explanation
1. Sorting Rows by Arrival Time
        Collections.sort(this.getRows(), (Object o1, Object o2) -> {
            if (((Row) o1).getArrivalTime() == ((Row) o2).getArrivalTime())
            {
                return 0;
            }
            else if (((Row) o1).getArrivalTime() < ((Row) o2).getArrivalTime())
            {
                return -1;
            }
            else
            {
                return 1;
            }
        });
•	Purpose: Ensures processes are ordered by their Arrival Time (AT).
•	How It Works: 
o	Uses Collections.sort with a custom comparator.
o	Compares the ArrivalTime of two Row objects. 
	Returns -1 if the first process arrives earlier than the second.
	Returns 1 if the first process arrives later than the second.
	Returns 0 if both have the same ArrivalTime.
________________________________________
2. Building the Gantt Chart (timeline)
        List<Event> timeline = this.getTimeline();
        
        for (Row row : this.getRows())
        {
            if (timeline.isEmpty())
            {
                timeline.add(new Event(row.getProcessName(), row.getArrivalTime(), row.getArrivalTime() + row.getBurstTime()));
            }
            else
            {
                Event event = timeline.get(timeline.size() - 1);
                timeline.add(new Event(row.getProcessName(), event.getFinishTime(), event.getFinishTime() + row.getBurstTime()));
            }
        }
•	Purpose: Constructs a timeline for process execution (used for Gantt chart visualization).
•	Logic:
1.	First Process: 
	Starts at its ArrivalTime.
	Ends after its BurstTime.
2.	Subsequent Processes: 
	Start after the previous process finishes (event.getFinishTime()).
	End after executing for their BurstTime.
•	Event Class:
1.	Represents a segment of the Gantt chart for a process.
2.	Contains: 
	ProcessName: Name of the process.
	StartTime: When the process starts execution.
	FinishTime: When the process finishes execution.
________________________________________
3. Calculating Waiting Time (WT) and Turnaround Time (TAT)
        for (Row row : this.getRows())
        {
            row.setWaitingTime(this.getEvent(row).getStartTime() - row.getArrivalTime());
            row.setTurnaroundTime(row.getWaitingTime() + row.getBurstTime());
        }
•	Purpose: Updates each process's metrics.
•	Waiting Time (WT): 
o	WT=StartTime−ArrivalTimeWT = \text{StartTime} - \text{ArrivalTime}
•	Turnaround Time (TAT): 
o	TAT=WT+BTTAT = WT + BT
•	These metrics are computed for each Row (process) in the list using data from the Event timeline.
________________________________________
Execution Flow
1.	Sorting Processes:
o	Processes are arranged in ascending order of their arrival times.
2.	Generating Timeline:
o	Each process is added to the timeline in sorted order.
o	Start and finish times are calculated and recorded in Event objects.
3.	Computing Metrics:
o	For each process: 
	Waiting Time is calculated based on when the process starts relative to its arrival.
	Turnaround Time is the sum of its Waiting Time and Burst Time.
________________________________________
Summary
•	Class: Implements the First Come, First Serve (FCFS) scheduling algorithm.
•	Key Features: 
o	Orders processes by Arrival Time.
o	Constructs a Gantt chart timeline using an Event list.
o	Computes Waiting Time and Turnaround Time for each process.
•	Output: 
o	Gantt chart for process execution.
o	Metrics (WT and TAT) for all processes.


5 Fifth code implements the Shortest Job First (SJF) scheduling algorithm, a non-preemptive CPU scheduling technique. In SJF, the process with the smallest Burst Time (BT) is selected for execution from all processes that have arrived at the current time.
________________________________________
Key Components and Explanation
1. Sorting Rows by Arrival Time
            Collections.sort(availableRows, (Object o1, Object o2) -> {
                if (((Row) o1).getBurstTime() == ((Row) o2).getBurstTime())
                {
                    return 0;
                }
                else if (((Row) o1).getBurstTime() < ((Row) o2).getBurstTime())
                {
                    return -1;
                }
                else
                {
                    return 1;
                }
            });
•	Purpose: Orders the processes based on Arrival Time (AT) so they can be processed sequentially.
•	Logic: 
o	Compares two processes' arrival times.
o	Orders them in ascending order.
________________________________________
2. Deep Copy of Rows
        List<Row> rows = Utility.deepCopy(this.getRows());
•	Purpose: Creates a deep copy of the list of processes (rows) to work on a temporary copy while keeping the original intact.
•	Utility Class: Assumes Utility.deepCopy provides a way to clone the Row objects to avoid modifying the original list.
________________________________________
3. Main Scheduling Loop
        int time = rows.get(0).getArrivalTime();
        
        while (!rows.isEmpty())
        {
            List<Row> availableRows = new ArrayList();
            
            for (Row row : rows)
            {
                if (row.getArrivalTime() <= time)
                {
                    availableRows.add(row);
                }
            }
            
            Collections.sort(availableRows, (Object o1, Object o2) -> {
                if (((Row) o1).getBurstTime() == ((Row) o2).getBurstTime())
                {
                    return 0;
                }
                else if (((Row) o1).getBurstTime() < ((Row) o2).getBurstTime())
                {
                    return -1;
                }
                else
                {
                    return 1;
                }
            });
            
            Row row = availableRows.get(0);
            this.getTimeline().add(new Event(row.getProcessName(), time, time + row.getBurstTime()));
            time += row.getBurstTime();
            
            for (int i = 0; i < rows.size(); i++)
            {
                if (rows.get(i).getProcessName().equals(row.getProcessName()))
                {
                    rows.remove(i);
                    break;
                }
            }
        }
•	Steps: 
1.	Initialize time:
	Start at the arrival time of the first process.
2.	Select Available Processes:
	From the remaining processes in rows, collect those with ArrivalTime <= time.
3.	Sort by Burst Time:
	Among the available processes, sort them by their Burst Time (BT) in ascending order.
4.	Select the Shortest Job:
	Pick the process with the smallest burst time (availableRows.get(0)).
5.	Add to Timeline:
	Add the process to the Gantt chart with: 
	Start Time: Current time.
	Finish Time: time + BT.
	Increment time by the selected process's burst time.
6.	Remove Process:
	Remove the selected process from the rows list.
7.	Repeat:
	Continue until all processes have been scheduled.
________________________________________
4. Calculating Waiting Time (WT) and Turnaround Time (TAT)
        for (Row row : this.getRows())
        {
            row.setWaitingTime(this.getEvent(row).getStartTime() - row.getArrivalTime());
            row.setTurnaroundTime(row.getWaitingTime() + row.getBurstTime());
        }
•	Purpose: Updates each process's metrics.
•	Waiting Time (WT): 
o	WT=StartTime−ArrivalTimeWT = \text{StartTime} - \text{ArrivalTime}
•	Turnaround Time (TAT): 
o	TAT=WT+BTTAT = WT + BT
________________________________________
Summary
•	Class: Implements the Shortest Job First (SJF) scheduling algorithm.
•	Key Features: 
o	Processes are executed in order of the smallest Burst Time among available processes.
o	Non-preemptive: A process runs to completion once started.
o	Computes Waiting Time and Turnaround Time for all processes.
•	Output: 
o	Gantt chart for process execution.
o	Metrics (WT and TAT) for all processes.
 

6Sixth code implements the Round Robin (RR) scheduling algorithm, a preemptive CPU scheduling technique. In Round Robin, each process is given a fixed time quantum to execute. If the process doesn’t finish during its time quantum, it is added back to the queue for further execution.
________________________________________
Key Components and Explanation
1. Sorting Rows by Arrival Time
        Collections.sort(this.getRows(), (Object o1, Object o2) -> {
            if (((Row) o1).getArrivalTime() == ((Row) o2).getArrivalTime())
            {
                return 0;
            }
            else if (((Row) o1).getArrivalTime() < ((Row) o2).getArrivalTime())
            {
                return -1;
            }
            else
            {
                return 1;
            }
        });
•	Purpose: Sorts the processes by their Arrival Time (AT).
•	Reason: Ensures processes are queued in the correct order for scheduling.
________________________________________
2. Initialization
        List<Row> rows = Utility.deepCopy(this.getRows());
        int time = rows.get(0).getArrivalTime();
        int timeQuantum = this.getTimeQuantum();
•	rows: A deep copy of the original process list to avoid modifying the original data.
•	time: Keeps track of the current time.
•	timeQuantum: Specifies the time each process gets before being preempted.
________________________________________
3. Main Scheduling Loop
        while (!rows.isEmpty())
        {
            Row row = rows.get(0);
            int bt = (row.getBurstTime() < timeQuantum ? row.getBurstTime() : timeQuantum);
            this.getTimeline().add(new Event(row.getProcessName(), time, time + bt));
            time += bt;
            rows.remove(0);
            
            if (row.getBurstTime() > timeQuantum)
            {
                row.setBurstTime(row.getBurstTime() - timeQuantum);
                
                for (int i = 0; i < rows.size(); i++)
                {
                    if (rows.get(i).getArrivalTime() > time)
                    {
                        rows.add(i, row);
                        break;
                    }
                    else if (i == rows.size() - 1)
                    {
                        rows.add(row);
                        break;
                    }
                }
            }
        }
•	Step 1: Take the first process (row) from the list.
•	Step 2: Determine execution time for this round: 
o	If the remaining burst time is less than the time quantum, the process runs for its entire remaining burst time.
o	Otherwise, it executes for the time quantum (bt).
•	Step 3: Add the process execution as an Event to the Gantt chart (timeline). 
o	Start Time: Current time.
o	Finish Time: time + bt.
o	Increment the current time by bt.
•	Step 4: If the process still has burst time left: 
o	Update the remaining burst time.
o	Add it back to the appropriate position in the rows list: 
	If a process arrives later than the current time, it is placed after all already-arrived processes.
•	Step 5: Repeat until all processes are completed.
________________________________________
4. Calculating Waiting Time (WT)
        Map map = new HashMap();
        
        for (Row row : this.getRows())
        {
            map.clear();
            
            for (Event event : this.getTimeline())
            {
                if (event.getProcessName().equals(row.getProcessName()))
                {
                    if (map.containsKey(event.getProcessName()))
                    {
                        int w = event.getStartTime() - (int) map.get(event.getProcessName());
                        row.setWaitingTime(row.getWaitingTime() + w);
                    }
                    else
                    {
                        row.setWaitingTime(event.getStartTime() - row.getArrivalTime());
                    }
                    
                    map.put(event.getProcessName(), event.getFinishTime());
                }
            }
            
            row.setTurnaroundTime(row.getWaitingTime() + row.getBurstTime());
        }
    }
}
•	Purpose: Calculates waiting time by analyzing the timeline for each process.
•	Logic: 
o	First Execution: WT=StartTime−ArrivalTime\text{WT} = \text{StartTime} - \text{ArrivalTime}
o	Subsequent Executions: 
	Calculate the gap between the current start time and the previous finish time (stored in the map).
	Accumulate this gap into the total waiting time.
•	Map Usage: 
o	map stores the finish time of the last execution for a process to calculate the waiting time for its next execution.
________________________________________
5. Calculating Turnaround Time (TAT)
            row.setTurnaroundTime(row.getWaitingTime() + row.getBurstTime());
•	Turnaround Time is calculated as: 
o	TAT=WT+BT\text{TAT} = \text{WT} + \text{BT}
________________________________________
Summary
•	Class: Implements the Round Robin CPU scheduling algorithm.
•	Key Features: 
o	Preemptive: Time quantum limits process execution time.
o	Maintains fairness by cycling through processes in order of arrival.
o	Computes Waiting Time and Turnaround Time for all processes.
•	Output: 
o	Gantt chart for process execution.
o	Metrics (WT and TAT) for all processes.

