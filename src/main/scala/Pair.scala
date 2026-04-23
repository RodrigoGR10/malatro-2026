package cl.uchile.dcc

object Pair extends PokerCombination {
  val name: String = "Pair"
  val baseScore: Score = Score(10, 2)

  def matches(cards: List[Card]): Boolean =
    PokerHelpers.validHand(cards) &&
      PokerHelpers.rankCounts(cards).values.exists(_ >= 2)
}