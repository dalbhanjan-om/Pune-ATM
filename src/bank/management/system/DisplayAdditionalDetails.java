package bank.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class DisplayAdditionalDetails extends JFrame {

    JTable table;
    DefaultTableModel tableModel;

    public DisplayAdditionalDetails() {
        super("Additional Details");

        // Set modern UI for the window
        getContentPane().setBackground(new Color(245, 245, 245));
        setLayout(new BorderLayout());

        // Column names for the table
        String[] columns = {"Form No", "Religion", "Category", "Income", "Education", "Occupation", "PAN", "Aadhar", "Senior Citizen", "Existing Account"};

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

        // Create a title label
        JLabel title = new JLabel("Additional Details", JLabel.CENTER);
        title.setFont(new Font("Raleway", Font.BOLD, 30));
        title.setForeground(new Color(252, 208, 76));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Add title and scrollPane to the frame
        add(title, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Frame settings
        setSize(1000, 500);
        setLocationRelativeTo(null);  // Centers the window on the screen
        setVisible(true);

        // Load data from the database
        loadDataFromDB();
    }

    // Load data from the database into the table
    public void loadDataFromDB() {
        try {
            Connn c = new Connn();
            String query = "SELECT * FROM additionaldetails";
            ResultSet rs = c.statement.executeQuery(query);

            while (rs.next()) {
                String formNo = rs.getString("formno");
                String religion = rs.getString("religion");
                String category = rs.getString("category");
                String income = rs.getString("income");
                String education = rs.getString("education");
                String occupation = rs.getString("occupation");
                String pan = rs.getString("pan");
                String aadhar = rs.getString("aadhar");
                String seniorCitizen = rs.getString("seniorcitizen");
                String existingAccount = rs.getString("existingaccount");

                // Add row to the table
                String[] data = {formNo, religion, category, income, education, occupation, pan, aadhar, seniorCitizen, existingAccount};
                tableModel.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DisplayAdditionalDetails();
    }
}
