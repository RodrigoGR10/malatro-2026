package cl.uchile.dcc
import rango.Ace
import rango.Rank

// Métodos auxiliares para validar combinaciones de póker
object PokerHelpers {

  // Verifica que la mano tenga entre 1 y 5 cartas
  def validHand(cards: List[Card]): Boolean =
    cards.nonEmpty && cards.size <= 5

  // Cuenta cuántas cartas hay de cada rango
  def rankCounts(cards: List[Card]): Map[Rank, Int] =
    cards.groupBy(_.rank).map { case (rank, sameRankCards) =>
      rank -> sameRankCards.size
    }

  // Verifica que una lista de números ordenados sea consecutiva
  def isConsecutive(values: List[Int]): Boolean =
    values.zip(values.drop(1)).forall { case (a, b) => b == a + 1 }
}