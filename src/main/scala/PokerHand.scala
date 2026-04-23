package cl.uchile.dcc

// Determina la mejor combinación posible para una mano de cartas
object PokerHand {

  // Lista de combinaciones ordenadas de mayor a menor prioridad
  val orderedCombinations: List[PokerCombination] =
    List(StraightFlush, Flush, Straight, ThreeOfAKind, Pair, HighCard)

  // Retorna la combinación de mayor prioridad que cumple la mano
  def bestCombination(cards: List[Card]): PokerCombination =
    orderedCombinations.find(_.matches(cards)).getOrElse(HighCard)
}