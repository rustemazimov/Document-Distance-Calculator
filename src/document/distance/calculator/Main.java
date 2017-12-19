/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package document.distance.calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Rustem
 */
public class Main {
    private String sourceFolderPath;
    private String searchingFilePath;
    private DocVisualiser resultWindow;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Main();
    }

    private Main() {
        MyFrame window = new MyFrame();
        window.setVisible(true);
        window.addActionListenerToSearchButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchingFilePath = window.getSearchingDocLabelTxt();
                sourceFolderPath = window.getSourceDocsLabelTxt();
                Result result;
                try {
                    result = MainWorker.getInstance().calculateResults(searchingFilePath, sourceFolderPath);
                } catch (Exception ex) {
                    showMessageDialog("Error", "Only .docx format is supported\nProgram can't read files while they are open");
                    return;
                }
                window.fillFileNameList(result.getSortedSourceFileNames());
                window.fillDistanceList(toStringArray(result.getDistances()));
                /*showMessageDialog("Info", "Document Distances was computed,\n"
                        + "Listed names for search results");*/
            }
        });
        window.addActionListenerToListItemCliked(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (resultWindow == null)
                {
                    resultWindow = new DocVisualiser();
                }
                try {
                    resultWindow.setTextFromPath(sourceFolderPath + "\\" + window.getSelectedItemFromList());
                    resultWindow.setVisible(true);
                    /*try {
                    new ProcessBuilder("winword.", sourceFolderPath + "\\" + window.getSelectedItemFromList()).start();
                    } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    private void showMessageDialog(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    private String[] toStringArray(double[] numArray) {
        String[] txtArray = new String[numArray.length];
        for (int i = 0; i < numArray.length; i++) {
            txtArray[i] = String.valueOf(numArray[i]);
        }
        return txtArray;
    }
    
}
