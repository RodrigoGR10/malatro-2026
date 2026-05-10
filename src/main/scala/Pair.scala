package cl.uchile.dcc

// Combinación: 2 cartas del mismo rango
object Pair extends PokerCombination {
  val name: String = "Pair"
  val baseScore: Score = Score(10, 2)

  def matches(cards: List[Card]): Boolean = {
    if !PokerHelpers.validHand(cards) then false
    else {
      val counts = PokerHelpers.rankCounts(cards)
      var found = false
      for (_, count) <- counts do
        if count >= 2 then found = true
      found
    }
  }
}