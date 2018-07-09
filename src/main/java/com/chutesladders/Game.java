// Copyright 2018. All Rights Reserved.
package com.chutesladders;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Initialises the game for the ladders.
 * @author krishnanand (Kartik Krishnanand)
 */
public class Game {
  
  private final List<Player> players;
  
  private final Board board;
  
  private final Random random;
  
  public Game(List<String> playerNames) {
    this.players = new ArrayList<>();
    this.board = Board.getInstance();
    this.random = new SecureRandom();
    this.addPlayers(playerNames);
  }
  
  public Board getBoard() {
    return board;
  }
  
  public int spin() {
    return this.random.nextInt(6) + 1;
  }
  
  public void addPlayers(List<String> playerNames) {
    for (String playerName : playerNames) {
       this.addPlayer(new Player(playerName));
    }
  }

  private void addPlayer(Player player) {
    this.players.add(player);
  }
  
  public List<Player> getPlayers() {
    return Collections.unmodifiableList(this.players);
  }
  
  /**
   * Returns the set of leaders representing the user that are closest to the goal.
   */
  public Set<Player> getLeaders() {
    if (this.players.isEmpty()) {
      return null;
    }
    Player leader = this.players.get(0);
    Set<Player> leaders = new LinkedHashSet<>();
    leaders.add(leader);
    for (int i = 1; i < this.players.size(); i++) {
      if (leader.getCurrentPosition() < this.players.get(i).getCurrentPosition()) {
          leaders.clear();
          leaders.add(this.players.get(i));
          leader = this.players.get(i);
      } else if (leader.getCurrentPosition() == this.players.get(i).getCurrentPosition()) {
          leaders.add(this.players.get(i));
      }
    }
    return leaders;
  }
  
  public boolean isGameOver(Player player, int position, int numberOfTurns) {
    if (position == 99) {
      System.out.println("The winner is " + player.getName() + "!");
      return true;
    }
    return false;
  }
  
  public Player getWinner() {
    for (Player player : this.players) {
      if (player.getCurrentPosition() == 100) {
        return player;
      }
    }
    return null;
  }
  
  /**
   * Plays the game and returns the winner.
   */
  public Player playGame() {
    if (this.players.isEmpty()) {
      return null;
    }
    boolean isGameOver = false;
    int numberOfTurns = 0;
    Player winner = null;
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
          winner = player;
          isGameOver = true;
          break;
        }
        Square[] squares = board.getSquares();
        StringBuilder sb = new StringBuilder();
        sb.append(numberOfTurns).append(": ").append(player.getName()).append(": ").
            append(player.getCurrentPosition() + 1);
        Ladder ladder = squares[newPosition].getLadder();
        if (ladder != null) {
          sb.append(" --> ").append(newPosition + 1).append("--").append("LADDER").append("--> ").append(
              ladder.getEndPosition() + 1);
          player.setCurrentPosition(ladder.getEndPosition());
        } else if (squares[newPosition].getChute() != null) {
          Chute chute = squares[newPosition].getChute();
          sb.append(" --> ").append(newPosition + 1).append("--").append("CHUTE").append("--> ").append(
              chute.getBottomPosition() + 1);
          player.setCurrentPosition(chute.getBottomPosition());
        } else {
          sb.append(" --> ").append(newPosition + 1);
          player.setCurrentPosition(newPosition);
        }
        System.out.println(sb.toString());
        if (isGameOver(player, player.getCurrentPosition(), numberOfTurns)) {
          winner = player;
          isGameOver = true;
          break;
        }
      }
    }
    return winner;
  }

}
