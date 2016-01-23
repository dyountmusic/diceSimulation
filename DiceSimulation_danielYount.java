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
        throwTheDice();
    }
    
    public static void throwTheDice() {
    
        // Dice Throw 1 at 10,000 redundancy
        
        int diceThrows = 10000;
        
        diceThrows = 600000000;
        
        System.out.println("Running experiment at " + diceThrows + " redundnacy.");
        doExperiment(diceThrows);
        
        System.out.println("Running exact simulation...");
        doExact();
        
        System.out.println("Program is finished.");
        
        System.out.println("Total number of dice throws: " + diceThrows);
        
        
    }
    
    public static void calcError() {
        
        for (int j = 2; j <= 2*SIDES; j++) {
            double error = (distExact[j] - distExperiment[j]);
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
