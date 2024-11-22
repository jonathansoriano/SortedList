import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SortedListGUI extends JFrame {
    private JPanel searchPanel;
    private JPanel addItemPanel;
    private JPanel resultsPanel;

    // Text fields and area
    private JTextField searchTF;
    private JTextField addItemTF;
    private JTextArea resultsTA;
    //SortedList Class declared but not instantiated. REMEMBER THIS!!!
    private SortedList list;

    public SortedListGUI() {
        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        InitializeComponents();

        // Add panels to the main panel
        mainPanel.add(searchPanel);
        mainPanel.add(addItemPanel);
        mainPanel.add(resultsPanel);

        // Frame setup
        this.setTitle("Sorted List Application");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.add(mainPanel);
        this.setVisible(true);

        list = new SortedList();
    }

    public void InitializeComponents() {
        // Search Panel
        searchPanel = new JPanel();
        searchTF = new JTextField(15);
        JButton searchBtn = new JButton("Search");
        searchBtn.setFocusable(false);
        searchBtn.addActionListener((ActionEvent ae) -> {
            try{
                list.binarySearch(searchTF.getText().trim(), resultsTA);
            }catch(NullPointerException e){
                JOptionPane.showMessageDialog(null, "You must add elements to our list first!", "Error Occurred", JOptionPane.ERROR_MESSAGE);


            }
        });

        searchPanel.add(searchTF);
        searchPanel.add(searchBtn);

        // Add Item Panel
        addItemPanel = new JPanel();

        JLabel addLabel = new JLabel("Add items:");
        addItemTF = new JTextField(15);
        JButton addBtn = new JButton("Add");
        addBtn.setFocusable(false);
        addBtn.addActionListener((ActionEvent ae) -> {
            if (addItemTF.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter valid input", "Error", JOptionPane.WARNING_MESSAGE);
            }else{
                list.add(addItemTF.getText().trim());
                list.displaySortedList(resultsTA);
                addItemTF.setText("");
            }

                });

        addItemPanel.add(addLabel);
        addItemPanel.add(addItemTF);
        addItemPanel.add(addBtn);

        // Result Panel
        resultsPanel = new JPanel();

        resultsTA = new JTextArea(12, 30);
        resultsTA.setEditable(false); // Make it non-editable
        JScrollPane scrollPane = new JScrollPane(resultsTA);

        resultsPanel.add(scrollPane);


    }

}
