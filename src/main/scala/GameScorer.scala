package cl.uchile.dcc

object GameScorer {
  def calculate(playedCards: List[Card], jokers: List[Joker]): Int = {
    val combination = PokerHand.bestCombination(Hand(playedCards))
    var chips = combination.baseScore.chips
    var mult  = combination.baseScore.mult

    for card <- playedCards do
      chips += card.rank.valor

    for joker <- jokers do
      val (newChips, newMult) = joker.apply(playedCards, chips, mult)
      chips = newChips
      mult  = newMult

    chips * mult
  }
}