// Copyright 2018. All Rights Reserved.
package com.chutesladders;

/**
 * An instance of this class encapsulates the playing board for snakes and ladders.
 * 
 * @author krishnanand (Kartik Krishnanand)
 */
public class Board {
  
  private static final Board BOARD = new Board();
  
  private final Square[] squares;
  
  private Board() {
    this.squares = new Square[100];
    this.initialiseSquares();
  }
  
  public void initialiseSquares() {
    for (int i = 0; i < 100; i ++) {
      this.squares[i] = new Square(i + 1);
    }
    this.addLadders();
    this.addChutes();
  }
  
  public Square[] getSquares() {
    return squares;
  }

  /**
   * Adds ladders to the board.
   */
  private void addLadders() {
    int[][] ladders = {
        {1,38}, {4,14}, {9,31}, {21,42}, {28,84}, {36, 44}, {51, 67}, {71, 91}, {80, 100}};
    for (int[] ladder: ladders) {
      this.squares[ladder[0] - 1].setLadder(new Ladder(ladder[0] - 1, ladder[1] - 1));
    }
  }
  
  private void addChutes() {
    int[][] chutes = {
        {16, 6}, {47, 26}, {49, 11}, {56, 52}, {62, 19}, {64, 60}, {87, 24}, {93, 73}, {95, 75},
        {98, 78}};
    for (int[] chute: chutes) {
      this.squares[chute[0] - 1].setChute(new Chute(chute[0] - 1, chute[1] - 1));
    }
  }
  
  public static final Board getInstance() {
    return BOARD;
  }
}
