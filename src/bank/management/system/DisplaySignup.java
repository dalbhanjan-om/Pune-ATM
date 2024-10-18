package bank.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class DisplaySignup extends JFrame {

    JTable table;
    DefaultTableModel tableModel;

    public DisplaySignup() {
        super("Signup Details");

        // Set modern UI for the window
        getContentPane().setBackground(new Color(245, 245, 245));
        setLayout(new BorderLayout());

        // Column names for the table
        String[] columns = {"Form No", "Name", "Father's Name", "DOB", "Gender", "Email", "Marital Status", "Address", "City", "Pin Code", "State"};

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
        JLabel title = new JLabel("Signup Details", JLabel.CENTER);
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
            String query = "SELECT * FROM signup";
            ResultSet rs = c.statement.executeQuery(query);

            while (rs.next()) {
                String formNo = rs.getString("formno");
                String name = rs.getString("name");
                String fname = rs.getString("fname");
                String dob = rs.getString("dob");
                String gender = rs.getString("gender");
                String email = rs.getString("email");
                String marital = rs.getString("marital");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String pincode = rs.getString("pincode");
                String state = rs.getString("state");

                // Add row to the table
                String[] data = {formNo, name, fname, dob, gender, email, marital, address, city, pincode, state};
                tableModel.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DisplaySignup();
    }
}
