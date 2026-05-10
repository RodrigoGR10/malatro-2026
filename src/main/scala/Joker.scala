package cl.uchile.dcc

import pinta.Diamonds
import rango.{Par, Figura}
//Jokers con sus tipos
trait Joker {
  def apply(cards: List[Card], chips: Int, mult: Int): (Int, Int)
}
case object GreedyJoker extends Joker {
  def apply(cards: List[Card], chips: Int, mult: Int): (Int, Int) = {
    var diamonds = 0
    for card <- cards do
      if card.suit == Diamonds then diamonds += 1
    (chips, mult + diamonds * 3)
  }
}

case object DeviousJoker extends Joker {
  def apply(cards: List[Card], chips: Int, mult: Int): (Int, Int) =
    if Straight.matches(cards) then (chips + 100, mult)
    else (chips, mult)
}

case object EvenSteven extends Joker {
  def apply(cards: List[Card], chips: Int, mult: Int): (Int, Int) = {
    var evens = 0
    for card <- cards do
      if card.rank.clasificacion == Par then evens += 1
    (chips, mult + evens * 4)
  }
}

case object ScaryFace extends Joker {
  def apply(cards: List[Card], chips: Int, mult: Int): (Int, Int) = {
    var figures = 0
    for card <- cards do
      if card.rank.clasificacion == Figura then figures += 1
    (chips + figures * 30, mult)
  }
}