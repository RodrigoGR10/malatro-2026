package cl.uchile.dcc

object HighCard extends PokerCombination {
  val name: String = "High Card"
  val baseScore: Score = Score(5, 1)

  def matches(cards: List[Card]): Boolean =
    PokerHelpers.validHand(cards)
}
