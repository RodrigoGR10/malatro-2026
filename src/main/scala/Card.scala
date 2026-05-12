package cl.uchile.dcc
import pinta.Pinta
import rango.Rank

/**
 * Represents a playing card with a rank and a suit.
 *
 * @param rank the rank of this card (e.g. Ace, Two, King)
 * @param suit the suit of this card (e.g. Hearts, Diamonds)
 */
class Card(val rank: Rank, val suit: Pinta) {
  /**
   * Returns true if the given object is a Card with the same rank and suit.
   *
   * @param obj the object to compare with
   * @return true if obj is a Card with equal rank and suit
   */
  override def equals(obj: Any): Boolean = {
    if !obj.isInstanceOf[Card] then false
    else {
      val other = obj.asInstanceOf[Card]
      this.rank == other.rank && this.suit == other.suit
    }
  }
}