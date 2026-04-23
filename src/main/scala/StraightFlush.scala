package cl.uchile.dcc

// Combinación: 5 cartas consecutivas de la misma pinta
object StraightFlush extends PokerCombination {
  val name: String = "Straight Flush"
  val baseScore: Score = Score(100, 8)

  // Válida si es escalera Y color al mismo tiempo
  def matches(cards: List[Card]): Boolean =
    PokerHelpers.isStraight(cards) && PokerHelpers.isFlush(cards)
}