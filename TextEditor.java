package arrray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TextEditor extends JFrame implements ActionListener {
    // Create the menu bar
    JMenuBar menuBar;

    // Create the menu
    JMenu fileMenu, editMenu;

    // Create menu items for File menu
    JMenuItem newFile, openFile, saveFile, printFile, close;

    // Create menu items for Edit menu
    JMenuItem cut, copy, paste;

    // Create the text area
    JTextArea textArea;

    // Create the Save and Submit button
    JButton saveSubmit;

    public TextEditor() {
        // Set the title of the window
        setTitle("Text Editor");

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the window
        setSize(800, 600);

        // Initialize the menu bar
        menuBar = new JMenuBar();

        // Initialize the file menu
        fileMenu = new JMenu("File");

        // Initialize the edit menu
        editMenu = new JMenu("Edit");

        // Initialize the menu items for File menu
        newFile = new JMenuItem("New");
        openFile = new JMenuItem("Open");
        saveFile = new JMenuItem("Save");
        printFile = new JMenuItem("Print");
        close = new JMenuItem("Close");

        // Add action listeners for File menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        printFile.addActionListener(this);
        close.addActionListener(this);

        // Add the menu items to the File menu
        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(printFile);
        fileMenu.add(close);

        // Initialize the menu items for Edit menu
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");

        // Add action listeners for Edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);

        // Add the menu items to the Edit menu
        editMenu.add(cut);
        editMenu.add(copy);
        editMenu.add(paste);

        // Add the menus to the menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        // Set the menu bar for the frame
        setJMenuBar(menuBar);

        // Initialize the text area
        textArea = new JTextArea();

        // Add the text area to the frame
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Initialize the Save and Submit button
        saveSubmit = new JButton("Save and Submit");

        // Add action listener for Save and Submit button
        saveSubmit.addActionListener(this);

        // Add the Save and Submit button to the frame
        add(saveSubmit, BorderLayout.SOUTH);

        // Make the frame visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newFile) {
            textArea.setText("");
        } else if (e.getSource() == openFile) {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
                    textArea.read(reader, null);
                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (e.getSource() == saveFile || e.getSource() == saveSubmit) {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile()));
                    textArea.write(writer);
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (e.getSource() == saveSubmit) {
                System.exit(0);
            }
        } else if (e.getSource() == printFile) {
            try {
                textArea.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == close) {
            System.exit(0);
        } else if (e.getSource() == cut) {
            textArea.cut();
        } else if (e.getSource() == copy) {
            textArea.copy();
        } else if (e.getSource() == paste) {
            textArea.paste();
        }
    }

    public static void main(String[] args) {
        new TextEditor();
    }
}