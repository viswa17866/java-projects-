import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ExpenseTrackerUI extends JFrame {

    private JTextField dateField, categoryField, amountField, noteField;
    private DefaultTableModel tableModel;
    private JTable expenseTable;

    private ArrayList<Expense> expenses;

    public ExpenseTrackerUI() {
        setTitle("Expense Tracker - Java Desktop App");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        expenses = new ArrayList<>();

        JPanel inputPanel = new JPanel(new GridLayout(2, 5, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add New Expense"));

        dateField = new JTextField();
        categoryField = new JTextField();
        amountField = new JTextField();
        noteField = new JTextField();
        JButton addButton = new JButton("Add Expense");
        JButton saveButton = new JButton("Save to File");
        JButton loadButton = new JButton("Load from File");

        inputPanel.add(new JLabel("Date (DD-MM-YYYY)"));
        inputPanel.add(new JLabel("Category"));
        inputPanel.add(new JLabel("Amount"));
        inputPanel.add(new JLabel("Note"));
        inputPanel.add(new JLabel(""));  

        inputPanel.add(dateField);
        inputPanel.add(categoryField);
        inputPanel.add(amountField);
        inputPanel.add(noteField);
        inputPanel.add(addButton);
        inputPanel.add(saveButton);
        inputPanel.add(loadButton);

     
        String[] columns = {"Date", "Category", "Amount", "Note"};
        tableModel = new DefaultTableModel(columns, 0);
        expenseTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(expenseTable);

       
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addExpense();
            }
            
        });
        saveButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        saveExpensesToFile("expenses.csv");
    }
});

loadButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        loadExpensesFromFile("expenses.csv");
    }
});


        
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    
    private void addExpense() {
        String date = dateField.getText();
        String category = categoryField.getText();
        String amountText = amountField.getText();
        String note = noteField.getText();

        try {
            double amount = Double.parseDouble(amountText);
            Expense exp = new Expense(date, category, amount, note);
            expenses.add(exp);
            tableModel.addRow(new Object[]{date, category, amount, note});
            clearInputFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearInputFields() {
        dateField.setText("");
        categoryField.setText("");
        amountField.setText("");
        noteField.setText("");
    }
    private void saveExpensesToFile(String filename) {
    try {
        java.io.PrintWriter writer = new java.io.PrintWriter(filename);
        for (Expense exp : expenses) {
            writer.println(exp.getDate() + "," + exp.getCategory() + "," + exp.getAmount() + "," + exp.getNote());
        }
        writer.close();
        JOptionPane.showMessageDialog(this, "Expenses saved successfully!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage());
    }
}

private void loadExpensesFromFile(String filename) {
    try {
        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filename));
        String line;
        expenses.clear();
        tableModel.setRowCount(0); // Clear existing table

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",", -1); // keep empty notes
            if (parts.length >= 4) {
                Expense exp = new Expense(parts[0], parts[1], Double.parseDouble(parts[2]), parts[3]);
                expenses.add(exp);
                tableModel.addRow(new Object[]{parts[0], parts[1], parts[2], parts[3]});
            }
        }
        reader.close();
        JOptionPane.showMessageDialog(this, "Expenses loaded successfully!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error loading file: " + e.getMessage());
    }
}

}
