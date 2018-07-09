// Copyright 2018. All Rights Reserved.
package com.chutesladders;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Initialises the game for the ladders.
 * 
 * @author krishnanand (Kartik Krishnanand)
 */
public class Game {
  
  private final List<Player> players;
  
  private final Board board;
  
  private final Random random;
  
  /**
   * Constructor for {@link Game}.
   * 
   * Initialises a list of players.
   * 
   * @param playerNames list of player names
   */
  public Game(List<String> playerNames) {
    this.players = new ArrayList<>();
    this.board = Board.getInstance();
    this.random = new SecureRandom();
    this.addPlayers(playerNames);
  }
  
  public Board getBoard() {
    return this.board;
  }
  
  /** Spins the dices. */
  public int spin() {
    return this.random.nextInt(6) + 1;
  }
  
  public void addPlayers(List<String> playerNames) {
    for (String playerName : playerNames) {
      this.players.add(new Player(playerName));
    }
  }
  
  public List<Player> getPlayers() {
    return Collections.unmodifiableList(this.players);
  }
  
  public boolean isGameOver(Player player, int position, int numberOfTurns) {
    if (position == 99) {
      System.out.println("The winner is " + player.getName() + "!");
      return true;
    }
    return false;
  }
  
  /**
   * Plays the game and returns the winner.
   */
  public void playTheGame() {
    boolean isGameOver = false;
    int numberOfTurns = 0;
    while (!isGameOver) {
      for (Player player : this.players) {
        numberOfTurns ++;
        int die = this.spin();
        int currentPosition = player.getCurrentPosition();
        int newPosition = currentPosition + die;
        // 99th index is the 100th position.
        if (newPosition > 99) {
          System.out.println(
              (newPosition + 1) + " is greater than 100. Player " + player.getName() +
              " stays at " + (currentPosition + 1));
          continue;
        }
        if (isGameOver(player, newPosition, numberOfTurns)) {
          isGameOver = true;
          break;
        }
        Square[] squares = board.getSquares();
        StringBuilder sb = new StringBuilder();
        sb.append(numberOfTurns).append(": ").append(player.getName()).append(": ").
            append(player.getCurrentPosition() + 1);
        Ladder ladder = squares[newPosition].getLadder();
        if (ladder != null) {
          sb.append(" --> ").append(newPosition + 1).append(" --").append("LADDER").append("--> ").append(
              ladder.getEndPosition() + 1);
          player.setCurrentPosition(ladder.getEndPosition());
        } else if (squares[newPosition].getChute() != null) {
          Chute chute = squares[newPosition].getChute();
          sb.append(" --> ").append(newPosition + 1).append(" --").append("CHUTE").append("--> ").append(
              chute.getBottomPosition() + 1);
          player.setCurrentPosition(chute.getBottomPosition());
        } else {
          sb.append(" --> ").append(newPosition + 1);
          player.setCurrentPosition(newPosition);
        }
        System.out.println(sb.toString());
        if (isGameOver(player, player.getCurrentPosition(), numberOfTurns)) {
          isGameOver = true;
          break;
        }
      }
    }
  }

}
