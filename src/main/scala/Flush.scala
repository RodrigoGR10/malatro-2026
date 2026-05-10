package cl.uchile.dcc

// Combinación: 5 cartas de la misma pinta
object Flush extends PokerCombination {
  val name: String = "Flush"
  val baseScore: Score = Score(35, 4)

  // Valida si todas las cartas tienen la misma pinta
  def matches(cards: List[Card]): Boolean =
    PokerHelpers.validHand(cards) && cards.size == 5 && cards.map(_.suit).distinct.size == 1
}