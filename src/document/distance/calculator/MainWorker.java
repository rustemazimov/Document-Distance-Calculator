/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package document.distance.calculator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Rustem Azimov
 */
public class MainWorker {
    private static MainWorker instance;
    private DistanceCalculator calculator = new DistanceCalculator();
    private ReadWriter reader = new ReadWriter();
    private String[] paths;
    private HashMap<Double, String> distanceMap;

    Result calculateResults(String searchingFilePath, String sourceFolderPath) throws IOException {
        return calculateResults(reader.readListOfWords(searchingFilePath), findSourceWordsMatrix(sourceFolderPath));
    }
    private Result calculateResults(String[] searchingWords, String[][] sourceDocs) {
        distanceMap = new HashMap<>();
        double[] distances = new double[sourceDocs.length];
        for (int i = 0; i < sourceDocs.length; i++) {
            distances[i] = calculator.calculateDistance(searchingWords, sourceDocs[i]);
            distanceMap.put(distances[i], paths[i]);
        }
        Arrays.sort(distances);
        List<String> sortedPaths = new ArrayList<>(distances.length);
        for (double distance : distances) {
            sortedPaths.add(distanceMap.get(distance));
        }
        return new Result(sortedPaths.toArray(paths), distances);
    }
    private String[] toPaths(File[] files) {
        String[] paths = new String[files.length];
        for(int i = 0; i < files.length; i++) {
            paths[i] = files[i].getAbsolutePath();
        }
        return paths;
    }
    private String[][] findSourceWordsMatrix(String folderPath) throws IOException {
        paths = toPaths(new File(folderPath).listFiles());
        String[][] words = new String[paths.length][];
        for (int i = 0; i < paths.length; i++) {
            words[i] = reader.readListOfWords(paths[i]);
        }
        return words;
    }
    private MainWorker() {}
    static MainWorker getInstance() {
        if(instance == null)
        {
            instance = new MainWorker();
        }
        return instance;
    }
}
