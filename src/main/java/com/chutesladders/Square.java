package com.chutesladders;


/**
 * An instance of this class encapsulates the square of a chute and ladder board.
 * 
 * @author krishnanand (Kartik Krishnanand)
 */
public class Square {
  
  private final int position;
  
  private Ladder ladder;
  
  private Chute chute;
  
  /**
   * Constructor for {@link Square}.
   * 
   * @param position position
   */
  public Square(int position) {
    this.position = position;
  }

  public void setLadder(Ladder ladder) {
    this.ladder = ladder;
  }

  public void setChute(Chute chute) {
    this.chute = chute;
  }

  public int getPosition() {
    return position;
  }

  public Ladder getLadder() {
    return ladder;
  }

  public Chute getChute() {
    return chute;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((chute == null) ? 0 : chute.hashCode());
    result = prime * result + ((ladder == null) ? 0 : ladder.hashCode());
    result = prime * result + position;
    return result;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Square [position=");
    builder.append(position);
    builder.append(", ladder=");
    builder.append(ladder);
    builder.append(", chute=");
    builder.append(chute);
    builder.append("]");
    return builder.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!Square.class.isAssignableFrom(this.getClass())) {
      return false;
    }
    Square other = (Square) obj;
    return (this.chute == other.getChute() || 
        (this.chute != null && this.chute.equals(other.getChute())))
        && (this.ladder == other.getLadder() ||
        (this.ladder != null && this.ladder.equals(other.getLadder()))) &&
        position != other.position;
  }

}
