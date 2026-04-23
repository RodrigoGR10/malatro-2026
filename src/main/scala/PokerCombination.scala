package cl.uchile.dcc

trait PokerCombination {
  val name: String
  val baseScore: Score
  def matches(cards: List[Card]): Boolean
}
