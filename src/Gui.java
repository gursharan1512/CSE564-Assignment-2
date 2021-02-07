import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui implements ActionListener {

    JTextArea editor = new JTextArea();
    JLabel console = new JLabel("");
    JLabel lexical = new JLabel("");

    public void createGui() {
        JFrame frame = new JFrame("Lexer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,650);
        frame.setLayout(null);

        createPanel(frame);
        createMenuBar(frame);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);


    }

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

    private void createPanel(JFrame frame) {

        editor.setLineWrap(true);
        //textArea.setWrapStyleWord(true);
        //textArea.getMaximumSize();
        //textArea.setPreferredSize(new Dimension(420,650));

//        JPanel panel = new JPanel();
//        panel.setBounds(5,0,440,400);
//        panel.add(textArea);
//        panel.setBackground(new Color(255, 255, 255));
//        panel.setBorder(BorderFactory.createTitledBorder("Source Code"));
//        frame.add(panel);

        JScrollPane scrollPane = new JScrollPane(editor);
        scrollPane.setBounds(0,0,440,400);
        scrollPane.setBackground(new Color(235, 235, 235));
        scrollPane.getViewport().setBackground(new Color(255, 255, 255));
        scrollPane.setBorder(BorderFactory.createTitledBorder("Source Code"));
        frame.add(scrollPane);

        JScrollPane scrollPane2 = new JScrollPane(lexical);
        scrollPane2.setBounds(440,0,440,400);
        scrollPane2.setBackground(new Color(235, 235, 235));
        scrollPane2.getViewport().setBackground(new Color(255, 255, 255));
        scrollPane2.setBorder(BorderFactory.createTitledBorder("Lexical Analysis"));
        frame.add(scrollPane2);

        console.setForeground(Color.WHITE);
        JScrollPane scrollPane3 = new JScrollPane(console);
        scrollPane3.setBounds(0,400,880, 185);
        scrollPane3.setBackground(new Color(235, 235, 235));
        scrollPane3.getViewport().setBackground(Color.BLACK);
        scrollPane3.setBorder(BorderFactory.createTitledBorder("Console"));
        frame.add(scrollPane3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String inputText = editor.getText();
        String buttonName = e.getActionCommand();

        if (buttonName.equals("Run")) {
            console.setText("Program complete");
            Token token = new Token();
            token.getToken(inputText);
            //lexical.setText(textContent);
        }
        else if (buttonName.equals("Exit")) {
            System.exit(0);
        }
    }
}
