package com.zavalajp.jpnotepad;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class FunctionFile {

    GUI gui;
    String fileName;
    String fileAddress;

    public FunctionFile(GUI gui) {
        this.gui = gui;
    }

    public void newFile() {
        gui.textArea.setText("");
        gui.window.setTitle("New");
        fileName = null;
        fileAddress = null;
    }

    public void openFile() {
        FileDialog fd = new FileDialog(gui.window, "Open", FileDialog.LOAD);
        fd.setVisible(true);

        if (fd.getFile() != null) {
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();
            gui.window.setTitle(fileName);
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileAddress + fileName));
            gui.textArea.setText("");
            String line;
            while ((line = br.readLine()) != null) {
                gui.textArea.append(line + "\n");
            }
            br.close();
        } catch(Exception e) {
            System.out.println("ERROR: File not opened");
        }
    }

    public void saveFile() {
        if (fileName == null) {
            saveAsFile();
        }
        else {
            try {
                FileWriter fw = new FileWriter(fileAddress + fileName);
                fw.write(gui.textArea.getText());
                gui.window.setTitle(fileName);
                fw.close();
            } catch (Exception e) {
                System.out.println("Error: could not save");
            }
        }
    }

    public void saveAsFile() {
        FileDialog fd = new FileDialog(gui.window, "Save", FileDialog.SAVE);
        fd.setVisible(true);

        if (fd.getFile() != null) {
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();
            gui.window.setTitle(fileName);
        }

        try {
            FileWriter fw = new FileWriter(fileAddress + fileName);
            fw.write(gui.textArea.getText());
            fw.close();
        } catch(Exception e) {
            System.out.println("ERROR: cannot save");
        }
    }

    public void exitProgram() {
        System.exit(0);
    }
}
