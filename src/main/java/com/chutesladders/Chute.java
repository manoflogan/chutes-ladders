package com.chutesladders;

// Copyright 2018. All Rights Reserved.

/**
 * An instance of this class encapsulates the chute with starting positions and ending positions.
 * 
 * @author krishnanand (Kartik Krishnanand)
 */
public class Chute {
  
  private final int topPosition;
  
  private final int bottomPosition;
  
  public Chute(int startPosition, int endPosition) {
    this.topPosition = startPosition;
    this.bottomPosition = endPosition;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + bottomPosition;
    result = prime * result + topPosition;
    return result;
  }

  public int getTopPosition() {
    return topPosition;
  }

  public int getBottomPosition() {
    return bottomPosition;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!Chute.class.isAssignableFrom(obj.getClass())) {
      return false;
    }
    Chute other = (Chute) obj;
    return bottomPosition == other.getBottomPosition() &&
        this.topPosition == other.getTopPosition();
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Chuter [startingPosition=");
    builder.append(topPosition);
    builder.append(", endPosition=");
    builder.append(bottomPosition);
    builder.append("]");
    return builder.toString();
  }
  

}
