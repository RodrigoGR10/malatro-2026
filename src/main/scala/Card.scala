package cl.uchile.dcc
import suit.Suit
import rank.Rank
import joker.Joker

/**
 * Represents a single playing card with a rank and a suit.
 *
 * @param _rank the rank of the card
 * @param _suit the suit of the card
 */
class Card(private var _rank: Rank, private var _suit: Suit) {
  /** Returns the rank of this card. */
  def rank: Rank = _rank
  /** Sets the rank of this card. */
  def rank_=(value: Rank): Unit = _rank = value

  /** Returns the suit of this card. */
  def suit: Suit = _suit
  /** Sets the suit of this card. */
  def suit_=(value: Suit): Unit = _suit = value

  /**
   * Applies this card's contribution to the given score.
   *
   * For each joker in the list, applies both the rank's and the
   * suit's score interaction with that joker.
   *
   * @param score the current score to update
   * @param jokers the list of active jokers
   * @return the updated score
   */
  def applyScore(score: Score, jokers: List[Joker]): Score = {
    score.chips = score.chips + rank.chipValue
    for joker <- jokers do
      joker.affectRank(rank, score)
      suit.applyScore(score, joker)
    score
  }

  override def equals(obj: Any): Boolean = {
    if !obj.isInstanceOf[Card] then false
    else {
      val other = obj.asInstanceOf[Card]
      this._rank == other._rank && this._suit == other._suit
    }
  }
}