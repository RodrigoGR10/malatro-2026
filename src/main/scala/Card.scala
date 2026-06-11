package cl.uchile.dcc
import pinta.Pinta
import rango.Rank
import joker._

class Card(private var _rank: Rank, private var _suit: Pinta) {
  def rank: Rank = _rank
  def rank_=(value: Rank): Unit = _rank = value

  def suit: Pinta = _suit
  def suit_=(value: Pinta): Unit = _suit = value

  override def equals(obj: Any): Boolean = {
    if !obj.isInstanceOf[Card] then false
    else {
      val other = obj.asInstanceOf[Card]
      this._rank == other._rank && this._suit == other._suit
    }
  }
}