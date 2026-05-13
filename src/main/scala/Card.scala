package cl.uchile.dcc
import pinta.Pinta
import rango.Rank

/**
 * Represents a playing card with a rank and a suit.
 *
 * @param rank the rank of the card
 * @param suit the suit of the card
 */
class Card(val rank: Rank, val suit: Pinta) {
  /**
   * Returns true if this card has the same rank and suit as another object.
   *
   * @param obj the object to compare with
   * @return true if obj is a Card with the same rank and suit
   */
  override def equals(obj: Any): Boolean = {
    if !obj.isInstanceOf[Card] then false
    else {
      val other = obj.asInstanceOf[Card]
      this.rank == other.rank && this.suit == other.suit
    }
  }
}