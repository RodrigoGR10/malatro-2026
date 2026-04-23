package cl.uchile.dcc

// Combinación: cualquier mano válida que no cumpla combinaciones superiores
object HighCard extends PokerCombination {
  val name: String = "High Card"
  val baseScore: Score = Score(5, 1)

  // Válida para cualquier mano con entre 1 y 5 cartas
  def matches(cards: List[Card]): Boolean =
    PokerHelpers.validHand(cards)
}
