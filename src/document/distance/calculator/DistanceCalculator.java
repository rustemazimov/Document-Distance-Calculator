/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package document.distance.calculator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;

/**
 *
 * @author Rustem
 */
public class DistanceCalculator {
    
    private double vectorAngle(String[] words, HashMap<String, Integer> d1, HashMap<String, Integer> d2) {
        double numerator = innerProduct(words, d1, d2);
        double denominator = Math.sqrt(innerProduct(words, d1, d1) * innerProduct(words, d2, d2));
        return numerator / denominator;
    }
    private double innerProduct(String[] words, HashMap<String, Integer> d1, HashMap<String, Integer> d2) {
        double sum = 0.0;
        for(String word : words) {
            Integer count1 = d1.get(word);
            Integer count2 = d2.get(word);
            if(count1 == null || count2 == null)
            {
                continue;
            }
            sum += count1 * count2;      
        }
        return sum;
    }
    private HashMap<String, Integer> countFrequency(String[] array) {
        HashMap<String, Integer> map = new HashMap<>(array.length);
        for(String word : array) {
            Integer count = map.get(word);
            if(count == null) 
            {
                map.put(word, 1);
            }
            else
            {
                map.put(word, map.get(word));
            }
        }
        return map;
    }
    double calculateDistance(String[] array1, String[] array2) {
        return vectorAngle(prepareFullWordList(array1, array2), countFrequency(array1), countFrequency(array2));
    }
    private String[] prepareFullWordList(String[] array1, String[] array2) {
        HashSet<String> wordsSet = new HashSet<>();
        List<String> wordsList = new ArrayList<>();
        for(String wordInArray1 : array1) {
            if(wordsSet.add(wordInArray1))
            {
                wordsList.add(wordInArray1);
            }
        }
        for(String wordInArray2 : array2) {
            if(wordsSet.add(wordInArray2))
            {
                wordsList.add(wordInArray2);
            }
        }
        return wordsList.toArray(new String[wordsList.size()]);
    }
}
