package cl.uchile.dcc
package exceptions

/** Thrown when trying to play or discard more than 5 cards at once. */
class TooManyCardsToPlayException(message: String) extends Exception(message)