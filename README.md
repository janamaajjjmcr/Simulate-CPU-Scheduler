# 🖥️ CPU Scheduler Simulator

A Java project that simulates three CPU scheduling algorithms: **First-Come-First-Serve (FCFS)**, **Shortest Job First (SJF)**, and **Round Robin (RR)** — with both **console** and **GUI** interfaces.

---

## ⚙️ Core Components

### 🔧 `CPUScheduler` (Abstract Class)
A base class for all CPU scheduling strategies.

**Key Fields:**
```java
private final List<Row> rows;       // List of processes
private final List<Event> timeline; // Gantt chart timeline
private int timeQuantum;            // Time quantum for Round Robin
```

**Main Methods:**

- ➕ `add()` – Add a process  
- 📊 `getAverageWaitingTime()` – Calculate average waiting time  
- 📈 `getAverageTurnAroundTime()` – Calculate average turnaround time

---

## 🧠 Scheduling Algorithms

### 🕒 First-Come-First-Serve (FCFS)

- Non-preemptive  
- Processes are executed in order of arrival  
- Steps:  
  1. Sort processes by arrival time  
  2. Execute each process to completion  
  3. Waiting Time = Start Time - Arrival Time

### ⏳ Shortest Job First (SJF)

- Non-preemptive  
- Picks the shortest available job at each scheduling point  
- Steps:  
  1. At current time, choose available process with shortest burst time  
  2. Execute to completion  
  3. Repeat until all processes are done

### 🔄 Round Robin (RR)

- Preemptive scheduling  
- Each process is given a time quantum  
- Steps:  
  1. Execute each process for one time slice  
  2. If not completed, move it to the back of the queue  
  3. Repeat until all processes complete  
  4. Waiting Time is calculated over multiple executions

---

## 🖼️ GUI Features

- 📋 Add and remove processes using a dynamic table  
- 🧮 Choose scheduling algorithm from dropdown (FCFS, SJF, RR)  
- 📊 Visualize the Gantt chart for process execution  
- 📈 Display metrics such as:
  - Average Waiting Time  
  - Average Turnaround Time

---

## 🚀 How to Use

### 🖥️ Console Version
```java
public static void main(String[] args) {
    CPUScheduler fcfs = new FirstComeFirstServe();
    fcfs.add(new Row("P1", 0, 5));
    fcfs.process();
    display(fcfs); // Outputs timeline and metrics
}
```

### 🖱️ GUI Version

1. Run the `GUI` class  
2. Add processes in the table (ID, Arrival Time, Burst Time)  
3. Select a scheduling algorithm  
4. Click **Compute** to view the Gantt chart and metrics

---

## 📌 Key Metrics

- **Waiting Time (WT):**  
  `WT = Start Time - Arrival Time`

- **Turnaround Time (TAT):**  
  `TAT = WT + Burst Time`

---

## 💡 Notes

> 📎 This project was built for **educational purposes** to demonstrate the logic behind classic CPU scheduling techniques.  
> It includes both algorithmic implementation and GUI for visualization.  
> Not intended for production or real-time systems.

---

## 🛠️ Built With

- 🧑‍💻 Java  
- 🎨 Java Swing (for GUI)  
- 🧠 Object-Oriented Programming  
- 📐 Custom Gantt chart rendering

---
 👨‍💻

 ![image](https://github.com/user-attachments/assets/a68e43d8-e575-448f-893f-aa824b156152)


## 1-FCFS

![image](https://github.com/user-attachments/assets/cd2e7dfc-d108-4934-921a-21e60abb54a5)

## 2-SJF

![image](https://github.com/user-attachments/assets/345c922a-af71-43ed-85f3-e1bc2ca69552)

## 3-RR

![image](https://github.com/user-attachments/assets/e250f54f-f834-4bb6-96ba-a227cabafc72)

![image](https://github.com/user-attachments/assets/c8421828-b916-422e-9d99-6fad68df8aa9)




