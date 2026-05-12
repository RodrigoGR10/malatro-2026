package cl.uchile.dcc
import pinta.Pinta
import rango.Rank

class Card(val rank: Rank, val suit: Pinta) {
  override def equals(obj: Any): Boolean = {
    if !obj.isInstanceOf[Card] then false
    else {
      val other = obj.asInstanceOf[Card]
      this.rank == other.rank && this.suit == other.suit
    }
  }
}