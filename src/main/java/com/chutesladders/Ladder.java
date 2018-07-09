package com.chutesladders;

// Copyright 2018. All Rights Reserved.

/**
 * An instance of this class encapsulates the ladder with starting positions and ending positions.
 * 
 * @author krishnanand (Kartik Krishnanand)
 */
public class Ladder {
  
  private final int startingPosition;
  
  private final int endPosition;
  
  public Ladder(int startPosition, int endPosition) {
    this.startingPosition = startPosition;
    this.endPosition = endPosition;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + endPosition;
    result = prime * result + startingPosition;
    return result;
  }

  public int getStartingPosition() {
    return startingPosition;
  }

  public int getEndPosition() {
    return endPosition;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!Ladder.class.isAssignableFrom(obj.getClass())) {
      return false;
    }
    Ladder other = (Ladder) obj;
    return endPosition == other.getEndPosition() &&
        this.startingPosition == other.getStartingPosition();
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Ladder [startingPosition=");
    builder.append(startingPosition);
    builder.append(", endPosition=");
    builder.append(endPosition);
    builder.append("]");
    return builder.toString();
  }
  

}
