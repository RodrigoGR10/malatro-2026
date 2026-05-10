package cl.uchile.dcc

// Combinación: 2 cartas del mismo rango
object Pair extends PokerCombination {
  val name: String = "Pair"
  val baseScore: Score = Score(10, 2)

  // Válida si existe algún rango con 2 o más cartas
  def matches(cards: List[Card]): Boolean =
    PokerHelpers.validHand(cards) && PokerHelpers.rankCounts(cards).values.exists(_ >= 2)
}