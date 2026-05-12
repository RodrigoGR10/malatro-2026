package cl.uchile.dcc

object StraightFlush extends PokerCombination {
  val name: String = "Straight Flush"
  val baseScore: Score = Score(100, 8)

  def matches(cards: List[Card]): Boolean =
    PokerHelpers.validHand(cards) && PokerHelpers.isStraightFlush(cards)
}