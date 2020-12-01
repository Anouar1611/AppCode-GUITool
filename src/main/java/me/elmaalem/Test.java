package me.elmaalem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.net.URL;


public class Test {

    private JPanel allPanel;
    private JPanel menuPanel;
    private JPanel editorCodePanel;
    private JPanel outputPanel;
    private JButton fileButton;
    private JButton runButton;
    private JScrollPane editorScrollPane;
    private JEditorPane textEditorPane;
    private JTextArea textOutput;
    private JLabel consoleLabel;
    private JLabel fileNameLabel;
    private JScrollPane consoleScrollPane;
    private JTabbedPane tabPane;
    private JButton saveButton;
    private JProgressBar progressBar;
    private JLabel progressLabel;



    PrintStream printStream = new PrintStream(new CustomOutputStream(textOutput));
    //TODO : add possibilite for Input
    //CustomInputStream customInputStream = new CustomInputStream();


    private File file;

    public Test() {

        fileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String fileName = "";
                do {
                    fileName = JOptionPane.showInputDialog(allPanel,
                            "What is your file name?", "");
                }while(fileName.isEmpty());

                //TODO: convert .java to .kt kotlin
                fileNameLabel.setText(fileName+".kts");
                tabPane.setTitleAt(0,fileName+".kts" );
                /*fileNameLabel.setText(fileName+".java");
                tabPane.setTitleAt(0,fileName+".java" );*/

                //TODO: convert .java to .kt kotlin
                 file = new File("src/main/java/resourceScripts/"+fileName+".kts");
                 //file = new File("src/main/resources/"+fileName+".kts");

                try {
                    if (file.createNewFile()) {
                        System.out.println("File created: " + file.getName());
                    } else {
                        System.out.println("File already exists.");
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                try {

                    textEditorPane.setEditable(true);
                    textOutput.setText("");

                    URL url = file.toURI().toURL();
                    System.out.println(url);
                    textEditorPane.setPage(file.toURI().toURL());//URI =URL+ /#posts


                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        runButton.addActionListener(new ActionListener() {

            public  void printLines(String std, InputStream ins) throws Exception {
                String line = null;
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(ins));
                while ((line = in.readLine()) != null) {
                    System.out.println(std + " " + line);
                }
            }

            public void runProcess(String command) throws Exception {
                System.out.println("Process is currently running");
                progressBar.setValue(0);
                Process pro = Runtime.getRuntime().exec(command);
                printLines("stdout: ",pro.getInputStream());
                printLines("stderr: ",pro.getErrorStream());
                pro.waitFor();
                progressBar.setValue(100);
                System.out.println("Process finished with exit code " +pro.exitValue());

            }

            public void actionPerformed(ActionEvent e) {

                textOutput.setText("");
                System.setOut(printStream);
                System.setErr(printStream);
                //TODO : add possibilite for Input
                //System.setIn(customInputStream);

                Thread thread = new Thread(new Runnable() {
                    public void run() {
                        try {
                            //runProcess("javac -cp src src/main/resources/"+ fileNameLabel.getText());
                            System.out.println("**********");
                            //TODO: change path contact.kts to fileNameLabel.getText()
                            runProcess("kotlinc -script src/main/java/resourceScripts/"+ fileNameLabel.getText());
                            //runProcess("kotlinc -script Contact.kt  src/main/java/resourceScripts/"+ fileNameLabel.getText());
                            //runProcess("java -cp src src/main/resources/"+ fileNameLabel.getText()); // javac
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }

                    }
                });
                thread.start();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileWriter writer = null;
                try {
                    writer = new FileWriter(file);
                    textEditorPane.write(writer);
                    writer.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        progressBar.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                    if ("progress" == evt.getPropertyName()) {
                        int progress = (Integer) evt.getNewValue();
                        progressBar.setValue(progress);
                    }
                }
        });

        //TODO : add possibilite for Input
        /*textOutput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //customInputStream.keyPressed(e);

            }
        });*/
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tool");
        frame.setContentPane(new Test().allPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500,500);
        frame.setVisible(true);

    }
}


