/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dicesimulation_danielyount;


/**
 *
 * @author danielyount
 */
public class DiceSimulation_danielYount {
    
    private static int SIDES = 6;
    private static double[] distExperiment = new double[2*SIDES+1];
    private static double[] distExact = new double[2*SIDES+1];

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Run the experiment by calling throwTheDice
        throwTheDice();
    }
    
    /**
     * Method that simulates the experiment and calculates the exact values of the distribution
     * for dice throws
     */
    public static void throwTheDice() {
        
        int diceThrows = 50000000; //Variable which holds the number of times the experiment is run
        
        System.out.println("Running experiment at " + diceThrows + " redundnacy.");
        doExperiment(diceThrows);
        
        System.out.println("Running exact simulation...");
        doExact();
        
        System.out.println("Calculating Error Values...");
        calcError();
        
        System.out.println("The smallest value of diceThrows(N) I found to repeatedly give me results within 4 decimal places of accuracy was " + diceThrows);
        
    }
    
    /**
     * A method to calculate the error term for each array term
     */
    public static void calcError() {
        
        for (int j = 2; j <= 2*SIDES; j++) {
            double error = Math.abs(Math.abs(distExact[j]) - Math.abs(distExperiment[j]));
            System.out.println("Error is " + error);
        }
        
    }
    
    /**
     * Method for running the actual experiment by lading the arrays with results
     * from the diceRoll() method which returns an int of both die summation
     * @param n The number of times the dice should be rolled 
     * @return an array of doubles containing the result
     */
    public static double[] doExperiment(int n) {
        
        for (int i = 0; i < n; i++) {
            distExperiment[diceRoll()]++;
        }
        
        for (int j = 2; j <= 2*SIDES; j++){
            distExperiment[j] /= n;
            System.out.println("Experiment distribution at " + j + " is " + distExperiment[j]);
             
        }
        
        return distExperiment;
    }
    
    public static int diceRoll() {
        
        int d1 = 1 + (int)(Math.random() * 6); 
        int d2 = 1 + (int)(Math.random() * 6);
        
        int result = d1 + d2;
        
        return result;
    }
    
    public static double[] doExact() {
        
        for (int i = 1; i <= SIDES; i++) {
            for (int j = 1; j <= SIDES; j++) {
                distExact[i+j] += 1;
            }
        }
        
        for (int k = 2; k <= 2*SIDES; k++){
            distExact[k] /= 36.0;
            System.out.println("Exact simulation at " + k + " is " + distExact[k]);
        }
        
        return distExact;
        
        }
    
}
