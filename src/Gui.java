import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Provides methods to implement graphical user interface for accepting code block and displays final token information.
 *
 * @author Gursharanjit Singh Ghotra
 * @author Manthan Agrawal
 */

public class Gui implements ActionListener {

    JTextArea editor = new JTextArea();
    JLabel console = new JLabel("");
    JLabel lexical = new JLabel("");
    DefaultTableModel tableModel = new DefaultTableModel();
    /**
     * creates a GUI base JFrame window to host different JPanels.
     */
    public void createGui() {
        JFrame frame = new JFrame("Lexer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 650);
        frame.setLayout(null);

        createPanel(frame);
        createMenuBar(frame);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    /**
     * This method creates the menu bar which provides option to Run the code.
     * @param frame - JFrame to host Menu Bar.
     */
    private void createMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(this);
        menuFile.add(exitMenuItem);

        JMenuItem runButton = new JMenuItem("Run");
        runButton.addActionListener(this);

        menuBar.add(menuFile);
        menuBar.add(runButton);
        frame.setJMenuBar(menuBar);
    }

    /**
     * Implements method to create 3 JPanels to take input and display output.
     * @param frame - JFrame to host Menu Bar.
     */
    private void createPanel(JFrame frame) {
        editor.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(editor);
        scrollPane.setBounds(0, 0, 440, 400);
        scrollPane.setBackground(new Color(235, 235, 235));
        scrollPane.getViewport().setBackground(new Color(255, 255, 255));
        scrollPane.setBorder(BorderFactory.createTitledBorder("Source Code"));
        frame.add(scrollPane);

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane2 = new JScrollPane(table);
        scrollPane2.setBounds(440, 0, 440, 400);
        scrollPane2.setBackground(new Color(235, 235, 235));
        scrollPane2.getViewport().setBackground(new Color(255, 255, 255));
        scrollPane2.setBorder(BorderFactory.createTitledBorder("Lexical Analysis"));
        frame.add(scrollPane2);

        console.setForeground(Color.WHITE);
        JScrollPane scrollPane3 = new JScrollPane(console);
        scrollPane3.setBounds(0, 400, 880, 185);
        scrollPane3.setBackground(new Color(235, 235, 235));
        scrollPane3.getViewport().setBackground(Color.BLACK);
        scrollPane3.setBorder(BorderFactory.createTitledBorder("Console"));
        frame.add(scrollPane3);
    }

    /**
     * Implements method to pass the input data to tokenizer after clicking Run.
     * @param e - Action Event in this case clicking of run button.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        String inputText = editor.getText();
        String buttonName = e.getActionCommand();

        if (buttonName.equals("Run")) {
            int numberOfWords = 0;
            int numberOfLines = 0;
            int errorCounter = 0;
            tableModel.getDataVector().removeAllElements();
            tableModel.setColumnCount(0);

            tableModel.addColumn("line");
            tableModel.addColumn("token");
            tableModel.addColumn("string or word");
            console.setText("Program complete");
            Tokenizer tokenizer = new Tokenizer();
            ArrayList<TokenDetails> tokenDetailsList = tokenizer.getToken(inputText);
            int i;
            for (i = tokenDetailsList.size() - 1; i >= 0; i--) {
                tableModel.insertRow(0, new Object[]{tokenDetailsList.get(i).getLineNumber(), tokenDetailsList.get(i).getTokenType(), tokenDetailsList.get(i).getWord()});
                if(tokenDetailsList.get(i).getTokenType().equals("ERROR")){
                    errorCounter++;
                }
            }
            numberOfWords = tokenDetailsList.size();
            numberOfLines = tokenDetailsList.get(numberOfWords-1).getLineNumber();

            if(errorCounter != 0 ){
                console.setText("Total Number of String parsed: "+ String.valueOf(numberOfWords)+ "       In " + numberOfLines + "lines.     But " + errorCounter + "  strings does not match any rule." );
            } else {
                console.setText("Program Compiled Successfully!!!         Total Number of String parsed: "+ String.valueOf(numberOfWords)+ "       In " + numberOfLines + "  lines.");

            }



        } else if (buttonName.equals("Exit")) {
            System.exit(0);
        }
    }
}
