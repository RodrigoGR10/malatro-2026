package cl.uchile.dcc

object ThreeOfAKind extends PokerCombination {
  val name: String = "Three of a Kind"
  val baseScore: Score = Score(30, 3)

  def matches(cards: List[Card]): Boolean =
    PokerHelpers.validHand(cards) && PokerHelpers.isThreeOfAKind(cards)
}