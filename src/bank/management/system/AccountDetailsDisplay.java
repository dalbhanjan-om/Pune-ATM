package bank.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class AccountDetailsDisplay extends JFrame {

    JTable table;
    DefaultTableModel tableModel;

    public AccountDetailsDisplay() {
        super("Account Details");

        // Set modern UI for the window
        getContentPane().setBackground(new Color(245, 245, 245));
        setLayout(new BorderLayout());

        // Create a title label
        JLabel title = new JLabel("Account Details", JLabel.CENTER);
        title.setFont(new Font("Raleway", Font.BOLD, 30));
        title.setForeground(new Color(252, 208, 76));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Column names for the table (adapt based on your database schema)
        String[] columns = {"Form No", "Account Type", "Card Number", "PIN"};

        // Table model and JTable setup
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        // Enhance table appearance
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        table.getTableHeader().setBackground(new Color(252, 208, 76));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setShowGrid(true);
        table.setGridColor(new Color(200, 200, 200));
        table.setSelectionBackground(new Color(184, 207, 229));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(new Color(255, 255, 255));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add title and scrollPane to the frame
        add(title, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Frame settings
        setSize(1000, 500);
        setLocationRelativeTo(null);  // Centers the window on the screen
        setVisible(true);

        // Load data from the database
        loadAccountDetails();
    }

    // Load data from the database into the table
    public void loadAccountDetails() {
        try {
            // Establish connection to the database
            Connn c1 = new Connn();
            String query = "SELECT * FROM accountdetails";

            // Execute the query and get the result set
            ResultSet rs = c1.statement.executeQuery(query);

            // Fetch data from the result set and populate the table
            while (rs.next()) {
                String formNo = rs.getString("formno");
                String accountType = rs.getString("accountType");
                String cardNumber = rs.getString("cardNumber");
                String pin = rs.getString("pin");

                // Add row to the table
                String[] data = {formNo, accountType, cardNumber, pin};
                tableModel.addRow(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AccountDetailsDisplay();
    }
}
