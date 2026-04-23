package cl.uchile.dcc

// Combinación: 3 cartas del mismo rango
object ThreeOfAKind extends PokerCombination {
  val name: String = "Three of a Kind"
  val baseScore: Score = Score(30, 3)

  // Válida si existe algún rango con 3 o más cartas
  def matches(cards: List[Card]): Boolean =
    PokerHelpers.validHand(cards) &&
      PokerHelpers.rankCounts(cards).values.exists(_ >= 3)
}