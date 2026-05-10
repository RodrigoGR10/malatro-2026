package cl.uchile.dcc

// Combinación: 3 cartas del mismo rango
object ThreeOfAKind extends PokerCombination {
  val name: String = "Three of a Kind"
  val baseScore: Score = Score(30, 3)

  def matches(cards: List[Card]): Boolean = {
    if !PokerHelpers.validHand(cards) then false
    else {
      val counts = PokerHelpers.rankCounts(cards)
      var found = false
      for (_, count) <- counts do
        if count >= 3 then found = true
      found
    }
  }
}