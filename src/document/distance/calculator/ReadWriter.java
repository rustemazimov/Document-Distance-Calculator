/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package document.distance.calculator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
/**
 *
 * @author Romeo
 */
public class ReadWriter {
    public String[] readListOfWords(String filePath) throws IOException{
        List<String> listOfWords = new ArrayList<>();
                FileInputStream fis = new FileInputStream(filePath);

                XWPFDocument document = new XWPFDocument(fis);

                List<XWPFParagraph> paragraphs = document.getParagraphs();


                for (XWPFParagraph para : paragraphs) {
                    //System.out.println(para.getText());
                    String[] wordsInALine = para.getText().split(" ");
                    for(String aWordInALine: wordsInALine){
                        listOfWords.add(aWordInALine);
                    }
                }
                fis.close();
        return listOfWords.toArray(new String[listOfWords.size()]);
          
    }
    
    String readString(String filePath) throws IOException {
        StringBuilder builder = new StringBuilder();
        FileInputStream fis = new FileInputStream(filePath);

                XWPFDocument document = new XWPFDocument(fis);

                List<XWPFParagraph> paragraphs = document.getParagraphs();


                for (XWPFParagraph para : paragraphs) {
                    builder.append(para.getText() + "\n");
                }
                fis.close();
        return builder.toString();

    }

}
