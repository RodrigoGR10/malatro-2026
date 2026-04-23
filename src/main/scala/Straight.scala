package cl.uchile.dcc

// Combinación: 5 cartas consecutivas sin importar la pinta
object Straight extends PokerCombination {
  val name: String = "Straight"
  val baseScore: Score = Score(30, 4)

  // Válida si las cartas son consecutivas
  def matches(cards: List[Card]): Boolean =
    PokerHelpers.isStraight(cards)
}