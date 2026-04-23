package cl.uchile.dcc

object Flush extends PokerCombination {
  val name: String = "Flush"
  val baseScore: Score = Score(35, 4)

  def matches(cards: List[Card]): Boolean =
    PokerHelpers.isFlush(cards)
}