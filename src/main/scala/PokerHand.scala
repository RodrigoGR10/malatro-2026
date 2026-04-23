package cl.uchile.dcc

object PokerHand {

  val orderedCombinations: List[PokerCombination] =
    List(
      StraightFlush,
      Flush,
      Straight,
      ThreeOfAKind,
      Pair,
      HighCard
    )

  def bestCombination(cards: List[Card]): PokerCombination =
    orderedCombinations.find(_.matches(cards)).getOrElse(HighCard)
}