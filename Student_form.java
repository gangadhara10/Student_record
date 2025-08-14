package mssqll;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Student_form extends JFrame {
    // JDBC connection variables
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=collegedb;encrypt=false";
    private static final String DB_USER = "sa";
    private static final String DB_PASS = "1234";

    // Form components
    private JTextField txtName, txtId, txtenglish, txtmaths, txtscience, txttelugu, txtsocial;
    private JTextField txtSearchId, txtTotalGT, txtTotalLT, txtAvgGT, txtAvgLT, txtGeneralSearch;
    private JTable table;
    private DefaultTableModel tableModel;

    public Student_form() {
        StudentRecords(); // Build UI
    }

    public void StudentRecords() {
        setTitle("Student Records");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700); // Bigger size
        setLayout(null);
        setLocationRelativeTo(null); // Center on screen

        // Labels and TextFields for input
        JLabel lblName = new JLabel("Student Name");
        lblName.setBounds(20, 20, 100, 25);
        add(lblName);
        txtName = new JTextField();
        txtName.setBounds(130, 20, 200, 25);
        add(txtName);

        JLabel lblId = new JLabel("ID");
        lblId.setBounds(20, 65, 100, 25);
        add(lblId);
        txtId = new JTextField();
        txtId.setBounds(130, 65, 200, 25);
        add(txtId);

        JLabel lbleng = new JLabel("English");
        lbleng.setBounds(20, 110, 100, 25);
        add(lbleng);
        txtenglish = new JTextField();
        txtenglish.setBounds(130, 110, 200, 25);
        add(txtenglish);

        JLabel lblMath = new JLabel("Maths");
        lblMath.setBounds(20, 150, 100, 25);
        add(lblMath);
        txtmaths = new JTextField();
        txtmaths.setBounds(130, 150, 200, 25);
        add(txtmaths);

        JLabel lblSci = new JLabel("Science");
        lblSci.setBounds(20, 190, 100, 25);
        add(lblSci);
        txtscience = new JTextField();
        txtscience.setBounds(130, 190, 200, 25);
        add(txtscience);

        JLabel lbltelugu = new JLabel("Telugu");
        lbltelugu.setBounds(20, 230, 100, 25);
        add(lbltelugu);
        txttelugu = new JTextField();
        txttelugu.setBounds(130, 230, 200, 25);
        add(txttelugu);

        JLabel lblSocial = new JLabel("Social");
        lblSocial.setBounds(20, 270, 180, 25);
        add(lblSocial);
        txtsocial = new JTextField();
        txtsocial.setBounds(130, 270, 200, 25);
        add(txtsocial);

        // Submit button
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(150, 330, 140, 30);
        add(btnSubmit);

        // Search by ID
        JLabel lblSearchId = new JLabel("Search ID");
        lblSearchId.setBounds(20, 400, 100, 25);
        add(lblSearchId);
        txtSearchId = new JTextField();
        txtSearchId.setBounds(100, 400, 140, 25);
        add(txtSearchId);
        JButton btnSearchId = new JButton("Search");
        btnSearchId.setBounds(300, 400, 130, 25);
        add(btnSearchId);

        // Search by Total
        JLabel lblTotal = new JLabel("Total >");
        lblTotal.setBounds(10, 500, 50, 25);
        add(lblTotal);
        txtTotalGT = new JTextField();
        txtTotalGT.setBounds(70, 500, 100, 25);
        add(txtTotalGT);
        //
        JButton btnTotalGT = new JButton("Search");
        btnTotalGT.setBounds(70, 550, 90, 25);
        add(btnTotalGT);

        JLabel lblTotalLT = new JLabel("Total <");
        lblTotalLT.setBounds(200, 500, 50, 25);
        add(lblTotalLT);
        txtTotalLT = new JTextField();
        txtTotalLT.setBounds(250, 500, 100, 25);
        add(txtTotalLT);
        
        JButton btnTotalLT = new JButton("Search");
        btnTotalLT.setBounds(250, 550, 80, 25);
        add(btnTotalLT);
        
        //average 1
        JLabel lblAvgGT = new JLabel("Avg >");
        lblAvgGT.setBounds(430, 500, 50, 25);
        add(lblAvgGT);
        txtAvgGT = new JTextField();
        txtAvgGT.setBounds(480, 500, 100, 25);
        add(txtAvgGT);
        
        
  
        // Search by Average
      
        JButton btnAvgGT = new JButton("Search");
        btnAvgGT.setBounds(490, 550, 80, 25);
        add(btnAvgGT);
        
        
       

        // average 2
        JLabel lblAvgLT = new JLabel("Avg <");
        lblAvgLT.setBounds(600, 500, 50, 25);
        add(lblAvgLT);
        txtAvgLT = new JTextField();
        txtAvgLT.setBounds(640, 500, 100, 25);
        add(txtAvgLT);
        
        
        JButton btnAvgLT = new JButton("Search");
        btnAvgLT.setBounds(650, 550, 80, 25);
        add(btnAvgLT);

        // General Search
        JLabel lblGeneralSearch = new JLabel("ID/Name/Total/Avg Search");
        lblGeneralSearch.setBounds(100, 650, 200, 25);
        add(lblGeneralSearch);
        txtGeneralSearch = new JTextField();
        txtGeneralSearch.setBounds(300, 650, 150, 25);
        add(txtGeneralSearch);
        JButton btnGeneralSearch = new JButton("Search");
        btnGeneralSearch.setBounds(530, 650, 80, 25);
        add(btnGeneralSearch);

        // Table
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.setRowHeight(25); // Bigger rows
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(900, 20, 600, 500);
        add(scrollPane);

        // Button Actions
        btnSubmit.addActionListener(e -> insertRecord());
        btnSearchId.addActionListener(e -> searchById());
        btnTotalGT.addActionListener(e -> searchByTotal(true));
       
        btnTotalLT.addActionListener(e -> searchByTotal(false));
        btnAvgGT.addActionListener(e -> searchByAverage(true));
        btnAvgLT.addActionListener(e -> searchByAverage(false));
        btnGeneralSearch.addActionListener(e -> generalSearch());
       
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
    
    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtenglish.setText("");
        txtmaths.setText("");
        txtscience.setText("");
        txttelugu.setText("");
        txtsocial.setText("");

        txtSearchId.setText("");
        txtTotalGT.setText("");
        txtTotalLT.setText("");
        txtAvgGT.setText("");
        txtAvgLT.setText("");
        txtGeneralSearch.setText("");
    }


    private void insertRecord() {
        try (Connection con = getConnection()) {
            int id = Integer.parseInt(txtId.getText());
            String name = txtName.getText();
            int eng = Integer.parseInt(txtenglish.getText());
            int math = Integer.parseInt(txtmaths.getText());
            int sci = Integer.parseInt(txtscience.getText());
            int telugu = Integer.parseInt(txttelugu.getText());
            int social = Integer.parseInt(txtsocial.getText());

            int total = eng + math + sci + telugu + social;
            double avg = total / 5.0;

            String sql = "INSERT INTO t1(id, name, english, math, science, telugu, Social, total, average) VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, eng);
            ps.setInt(4, math);
            ps.setInt(5, sci);
            ps.setInt(6, telugu);
            ps.setInt(7, social);
            ps.setInt(8, total);
            ps.setDouble(9, avg);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Record Inserted Successfully!");
            clearFields(); // <--- This clears the text fields
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

 // Search by ID
    private void searchById() {
        String idStr = txtSearchId.getText();
        String query = "SELECT * FROM t1 WHERE id = ?";
        displayResults(query, idStr);
        txtSearchId.setText(""); // Clear after search
    }

    // Search Total > value
    private void searchByTotal(boolean greaterThan) {
        String value = greaterThan ? txtTotalGT.getText().trim() : txtTotalLT.getText().trim();
        
        // Check if input is empty
        if (value.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a total value to search.");
            return;
        }

        try {
            int totalValue = Integer.parseInt(value); // Convert input to integer
            String operator = greaterThan ? ">" : "<";
            
            String query = "SELECT * FROM t1 WHERE total " + operator + " ?";
            displayResults(query, totalValue); // Pass integer parameter

            // Clear the text field after search
            if (greaterThan) {
                txtTotalGT.setText("");
            } else {
                txtTotalLT.setText("");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
        }
    }



    // Search Average > value
    private void searchByAverage(boolean greaterThan) {
        String value = greaterThan ? txtAvgGT.getText() : txtAvgLT.getText();
        String operator = greaterThan ? ">" : "<";
        String query = "SELECT * FROM t1 WHERE average " + operator + " ?";
        displayResults(query, value);

        if (greaterThan) {
            txtAvgGT.setText("");
        } else {
            txtAvgLT.setText("");
        }
    }

    // General Search
    private void generalSearch() {
        String keyword = "%" + txtGeneralSearch.getText() + "%";
        String query = "SELECT * FROM t1 WHERE CAST(id AS VARCHAR) LIKE ? OR name LIKE ? OR CAST(total AS VARCHAR) LIKE ? OR CAST(average AS VARCHAR) LIKE ?";
        displayResults(query, keyword, keyword, keyword, keyword);
        txtGeneralSearch.setText(""); // Clear after search
    }


    private void displayResults(String query, Object... params) {
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            // Clear old table data
            tableModel.setRowCount(0);
            tableModel.setColumnCount(0);

            // Set column names
            int colCount = rsmd.getColumnCount();
            for (int i = 1; i <= colCount; i++) {
                tableModel.addColumn(rsmd.getColumnName(i));
            }

            // Add rows
            while (rs.next()) {
                Object[] rowData = new Object[colCount];
                for (int i = 0; i < colCount; i++) {
                    rowData[i] = rs.getObject(i + 1);
                }
                tableModel.addRow(rowData);
            }
        }
        catch (Exception ex) 
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Student_form form = new Student_form();
            form.setVisible(true);
        });
    }
}
