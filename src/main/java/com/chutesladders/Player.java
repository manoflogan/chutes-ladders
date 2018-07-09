// Copyright 2018. All Rights Reserved.
package com.chutesladders;

/**
 * An instance of this class represents the player in the game of chutes and ladders.
 * 
 * @author krishnanand (Kartik Krishnanand)
 */
public class Player {
  
  private final String name;
  
  public Player(String name) {
    this.name = name;
  }
  
  private int currentPosition = -1;

  public int getCurrentPosition() {
    return currentPosition;
  }

  public void setCurrentPosition(int currentPosition) {
    this.currentPosition = currentPosition;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Player [name=");
    builder.append(this.name);
    builder.append(", currentPosition=");
    builder.append(currentPosition + 1);
    builder.append("]");
    return builder.toString();
  }
  
  @Override
  public int hashCode() {
    int hashCode = 31;
    hashCode += hashCode + this.currentPosition ^ 3 - 71;
    hashCode += this.name != null ? this.name.hashCode() ^ 5 : 82;
    return hashCode;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!Player.class.isAssignableFrom(this.getClass())) {
      return false;
    }
    Player other = (Player) obj;
    return (currentPosition == other.currentPosition &&
        this.name != null && this.name.equals(other.getName()));
  }

}
