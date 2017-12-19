/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package document.distance.calculator;

/**
 *
 * @author Rustem
 */
public class Result {
    private String[] sortedSourceFileNames;
    private double[] distances;

    public Result(String[] sortedSourceFileNames, double[] distances) {
        this.sortedSourceFileNames = sortedSourceFileNames;
        this.distances = distances;
    }

    /**
     * @return the sortedSourceFileNames
     */
    public String[] getSortedSourceFileNames() {
        return sortedSourceFileNames;
    }

    /**
     * @return the distances
     */
    public double[] getDistances() {
        return distances;
    }
    
    
}
