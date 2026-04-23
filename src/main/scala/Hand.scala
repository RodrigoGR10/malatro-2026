package cl.uchile.dcc
import pinta.Pinta
import rango.Rank
import cl.uchile.dcc.Joker
//Representa la mano del jugador (cartas y jokers)
case class Hand(val cards: List[Card] = List.empty, val jokers: List[Joker] = List.empty) {

}
