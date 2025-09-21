# 🖥️ Enhanced Dynamic Round Robin (EDRR) CPU Scheduler – Java Swing

This project is a **GUI-based CPU Scheduling Simulator** that implements a **proposed enhancement of the Round Robin algorithm (EDRR)**.  
It is built in **Java Swing** and allows users to input processes, simulate scheduling, and visualize results including **Gantt Chart**, **Waiting Time**, and **Turnaround Time**.

---

## 🚀 Features
- **Interactive GUI** built with Java Swing.  
- Add processes with **Arrival Time** and **Burst Time** dynamically.  
- Implements **Enhanced Dynamic Round Robin (EDRR)** scheduling:  
  - Time Quantum dynamically chosen as 80% of the maximum burst time.  
  - Adapts if processes remain incomplete.  
- Displays a **Gantt Chart** for execution order.  
- Calculates and shows:
  - Waiting Time for each process  
  - Turnaround Time for each process  
  - Average Waiting Time  
  - Average Turnaround Time  
- Clean, styled interface with modern UI elements.  


## 📷 Screenshots 
<img width="1145" height="342" alt="image" src="https://github.com/user-attachments/assets/2bb671c8-39c8-4a7d-82a1-0fbd49b0c635" />

 How It Works
1. **Input Processes**  
   - Enter *Arrival Time* and *Burst Time* for each process.  
   - Click **Add Process** to insert into the table.  

2. **Run Scheduler**  
   - Click **Run EDRR** to simulate scheduling.  
   - The app calculates **time quantum dynamically (80% of max burst)**.  
   - Displays execution order in **Gantt Chart** and summary results.  

3. **Output**  
   - Waiting and Turnaround times are shown for all processes.  
   - Average statistics provided.  

---

## 🛠 Tech Stack
- **Language:** Java  
- **Framework:** Java Swing (GUI)  
- **Concepts Used:**  
  - CPU Scheduling (Round Robin & its enhancement)  
  - Process Management  
  - Queue Data Structure  
  - GUI Event Handling  




