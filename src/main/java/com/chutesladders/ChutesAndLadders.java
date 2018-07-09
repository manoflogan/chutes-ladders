// Copyright 2018. All Rights Reserved.
package com.chutesladders;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * An instance of this class represents the entry point for chutes and ladders.
 * 
 * @author krishnanand (Kartik Krishnanand)
 */
public class ChutesAndLadders {
  
  /**
   * Reads the input for the number of players from the command line.
   * 
   * @param is inpustream object
   */
  private static void readFromInputStream(InputStream is) {
    try(Scanner scanner = new Scanner(is)) {
      // Read number of players from command line.
      int players = -1;
      while(true) {
          System.out.print("Enter number of players (between 2 - 4): ");
          players = scanner.nextInt();
          if (players >= 2 && players <= 4) {
             break;
          }
          System.out.println("Number of players should be between 2 - 4");
      }
      List<String> playerNames = new ArrayList<>();
      for (int i = 0; i < players; i++) {
        System.out.print("Enter name of player # " + (i + 1) + ": ");
        String name = scanner.next();
        playerNames.add(name);
      }
      Game game = new Game(playerNames);
      game.playGame();
    }
  }
  
  public static void main(String [] args) {
    readFromInputStream(System.in);
  }

}
