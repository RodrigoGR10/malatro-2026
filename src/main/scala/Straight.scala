package cl.uchile.dcc
import rango.Ace

// Combinación: 5 cartas consecutivas sin importar la pinta
object Straight extends PokerCombination {
  val name: String = "Straight"
  val baseScore: Score = Score(30, 4)

  // Válida si las cartas son consecutivas
  def matches(cards: List[Card]): Boolean = {
    if (!PokerHelpers.validHand(cards) || cards.size != 5) false
    else {
      val normal = cards.map(_.rank.orden).distinct.sorted
      val aceLow = cards.map(c => if (c.rank == Ace) 1 else c.rank.orden).distinct.sorted
      (normal.size == 5 && PokerHelpers.isConsecutive(normal)) || (aceLow.size == 5 && PokerHelpers.isConsecutive(aceLow))
    }
  }
}