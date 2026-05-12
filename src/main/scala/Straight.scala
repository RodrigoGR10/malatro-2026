package cl.uchile.dcc

object Straight extends PokerCombination {
  val name: String = "Straight"
  val baseScore: Score = Score(30, 4)

  def matches(cards: List[Card]): Boolean =
    PokerHelpers.validHand(cards) && PokerHelpers.isStraight(cards)
}