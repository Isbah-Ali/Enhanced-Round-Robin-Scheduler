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


## HOW ALGORITHM WORKS:

**1. Input**  
- Each process has:  
  - **Arrival Time (AT)** → when the process enters the ready queue.  
  - **Burst Time (BT)** → how long it needs to finish execution.  

---

**2. Initial Time Quantum (TQ) Selection**  
- The scheduler finds the **largest burst time (BTmax)** among all processes.  
- The initial time quantum is calculated as:  
TQ = 0.8 × BTmax

yaml
Copy code
- This ensures that:  
- Shorter jobs may finish in one go.  
- Longer jobs still get enough CPU time to make progress.  

---

**3. Scheduling Loop**  
- The system clock starts at **time = 0**.  
- At each step:  
- Add all processes with **AT ≤ current time** into the **Ready Queue**.  
- If the ready queue is empty → advance the clock to the **next arrival time**.  
- From the ready queue, processes are executed in **arrival order** using the current quantum `TQ`.  

---

**4. Execution Rules**  
- For each process `P`:  
- If **remaining time ≤ TQ** → run it completely and mark it **finished**.  
- If **remaining time > TQ** → run it for `TQ` units, then put it back into the queue with updated remaining time.  

---

**5. Dynamic Quantum Adjustment**  
- If in one full cycle **no process finishes**, the algorithm assumes the quantum was too small.  
- In that case, the quantum is increased to:  
TQ = BTmax
- This prevents **starvation** and ensures all processes eventually complete.  


## 📷 Screenshots 
<img width="837" height="301" alt="image" src="https://github.com/user-attachments/assets/84d0729d-b676-4dde-bc97-9618f2b553e7" />
<img width="842" height="582" alt="image" src="https://github.com/user-attachments/assets/ac9e16b6-c9ad-4bd5-80d4-93be108c8761" />


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




