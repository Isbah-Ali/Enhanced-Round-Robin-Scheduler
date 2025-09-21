import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class EDRRScheduler extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextArea outputArea;
    private JButton runButton, addButton;
    private JTextField arrivalField, burstField;

    static class Process {
        String id;
        int arrival, burst, remaining, finish, waiting, turnaround;

        Process(String id, int arrival, int burst) {
            this.id = id;
            this.arrival = arrival;
            this.burst = burst;
            this.remaining = burst;
        }
    }

    private ArrayList<Process> processes = new ArrayList<>();

    public EDRRScheduler() {
        setTitle("Corrected EDRR Scheduler");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
                JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Arrival Time:"));
        arrivalField = new JTextField(5);
        inputPanel.add(arrivalField);
        inputPanel.add(new JLabel("Burst Time:"));
        burstField = new JTextField(5);
        inputPanel.add(burstField);
        addButton = new JButton("Add Process");
        inputPanel.add(addButton);
        inputPanel.setBackground(new Color(230, 240, 255));  // light blue

        runButton = new JButton("Run EDRR");
        inputPanel.add(runButton);
        add(inputPanel, BorderLayout.NORTH);
        addButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        runButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        
        addButton.setBackground(new Color(0, 120, 215));      // Windows blue
        addButton.setForeground(Color.WHITE);
        runButton.setBackground(new Color(34, 139, 34));       // Forest green
        runButton.setForeground(Color.WHITE);



        tableModel = new DefaultTableModel(new String[]{"ID", "Arrival", "Burst"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
table.getTableHeader().setBackground(new Color(220, 220, 220)); // light gray


                outputArea = new JTextArea(10, 60); // Set rows/cols
        outputArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        outputArea.setEditable(false);
        outputArea.setBorder(BorderFactory.createTitledBorder("EDRR Output"));

        JScrollPane outputScroll = new JScrollPane(outputArea);
        outputScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(outputScroll, BorderLayout.SOUTH);
                JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Process List"));
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
        add(tablePanel, BorderLayout.CENTER);

        addButton.addActionListener(e -> {
            try {
                int arrival = Integer.parseInt(arrivalField.getText());
                int burst = Integer.parseInt(burstField.getText());
                String id = "P" + (tableModel.getRowCount() + 1);
                tableModel.addRow(new Object[]{id, arrival, burst});
                processes.add(new Process(id, arrival, burst));
                arrivalField.setText("");
                burstField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter valid integers.");
            }
        });

        runButton.addActionListener(e -> runEDRR());
    }

    private void runEDRR() {
                outputArea.setText(""); // Clear output

        StringBuilder output = new StringBuilder();

        if (processes.isEmpty()) {
            outputArea.setText("No processes to schedule.");
            return;
        }

        processes.sort(Comparator.comparingInt(p -> p.arrival));
        int btMax = processes.stream().mapToInt(p -> p.burst).max().orElse(0);
        int qt = (int)(0.8 * btMax);
        int time = 0, completed = 0, i = 0;
        int n = processes.size();
        Queue<Process> readyQueue = new LinkedList<>();
        StringBuilder gantt = new StringBuilder("Gantt Chart:\n");

        while (completed < n) {
            while (i < n && processes.get(i).arrival <= time) {
                readyQueue.offer(processes.get(i));
                i++;
            }

            if (readyQueue.isEmpty()) {
                if (i < n) time = processes.get(i).arrival;
                continue;
            }

            boolean executed = false;
            Queue<Process> nextQueue = new LinkedList<>();

            while (!readyQueue.isEmpty()) {
                Process p = readyQueue.poll();
                if (p.remaining <= qt) {
                    gantt.append("| ").append(p.id).append(" (").append(time).append("-").append(time + p.remaining).append(") ");
                    time += p.remaining;
                    p.finish = time;
                    p.remaining = 0;
                    completed++;
                    executed = true;

                    while (i < n && processes.get(i).arrival <= time) {
                        readyQueue.offer(processes.get(i));
                        i++;
                    }
                } else {
                    nextQueue.offer(p);
                }
            }

            if (!executed && !nextQueue.isEmpty()) {
                qt = btMax;
            }

            readyQueue = nextQueue;
        }
        gantt.append("|\n\n");
        output.append(gantt.toString());

        double totalWait = 0, totalTAT = 0;
        output.append(String.format("%-5s %-10s %-10s %-15s %-15s\n", "ID", "Arrival", "Burst", "Waiting Time", "Turnaround Time"));

        for (Process p : processes) {
            p.turnaround = p.finish - p.arrival;
            p.waiting = p.turnaround - p.burst;
            totalWait += p.waiting;
            totalTAT += p.turnaround;
            output.append(String.format("%-5s %-10d %-10d %-15d %-15d\n", p.id, p.arrival, p.burst, p.waiting, p.turnaround));
        }

        output.append(String.format("\nAverage Waiting Time: %.2f", totalWait / n));
        output.append(String.format("\nAverage Turnaround Time: %.2f", totalTAT / n));
        
                outputArea.setText(output.toString());

    }

    public static void main(String[] args) {
        try {
    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
            UIManager.setLookAndFeel(info.getClassName());
            break;
            
        }
    }
} catch (Exception e) {
    // Fallback to default
}

        SwingUtilities.invokeLater(() -> new EDRRScheduler().setVisible(true));
    }
}
