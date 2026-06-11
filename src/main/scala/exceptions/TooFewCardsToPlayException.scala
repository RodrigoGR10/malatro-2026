package cl.uchile.dcc
package exceptions

/** Thrown when trying to play or discard fewer than 1 card. */
class TooFewCardsToPlayException(message: String) extends Exception(message)