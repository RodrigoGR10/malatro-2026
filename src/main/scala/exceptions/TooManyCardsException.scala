package cl.uchile.dcc
package exceptions

/** Thrown when trying to add more than 8 cards to a hand. */
class TooManyCardsException(message: String) extends Exception(message)