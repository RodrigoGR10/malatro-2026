package cl.uchile.dcc

// Combinación: 5 cartas de la misma pinta
object Flush extends PokerCombination {
  val name: String = "Flush"
  val baseScore: Score = Score(35, 4)

  // Válida si todas las cartas tienen la misma pinta
  def matches(cards: List[Card]): Boolean =
    PokerHelpers.isFlush(cards)
}