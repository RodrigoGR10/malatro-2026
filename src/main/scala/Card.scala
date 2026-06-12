package cl.uchile.dcc
import pinta.Pinta
import rango.Rank
import joker.Joker

class Card(private var _rank: Rank, private var _suit: Pinta) {
  def rank: Rank = _rank
  def rank_=(value: Rank): Unit = _rank = value

  def suit: Pinta = _suit
  def suit_=(value: Pinta): Unit = _suit = value

  /**
   * Applies this card's contribution to the given score.
   *
   * For each joker in the list, applies both the rank's and the
   * suit's score interaction with that joker.
   *
   * @param score  the current score to update
   * @param jokers the list of active jokers
   * @return the updated score
   */
  def applyScore(score: Score, jokers: List[Joker]): Score = {
    var current = score
    for joker <- jokers do
      current = rank.applyScore(current, joker)
      current = suit.applyScore(current, joker)
    current
  }

  override def equals(obj: Any): Boolean = {
    if !obj.isInstanceOf[Card] then false
    else {
      val other = obj.asInstanceOf[Card]
      this._rank == other._rank && this._suit == other._suit
    }
  }
}