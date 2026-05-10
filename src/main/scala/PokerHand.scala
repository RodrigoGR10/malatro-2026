package cl.uchile.dcc

// Determina la mejor combinación posible para una mano de cartas
object PokerHand {
  val orderedCombinations: List[PokerCombination] =
    List(StraightFlush, Flush, Straight, ThreeOfAKind, Pair, HighCard)
  
  def bestCombination(hand: Hand): PokerCombination = {
    var result: PokerCombination = HighCard
    var found = false
    for combination <- orderedCombinations do
      if !found && combination.matches(hand.cards) then
        result = combination
        found = true
    result
  }
}