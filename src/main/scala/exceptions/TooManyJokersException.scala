package cl.uchile.dcc
package exceptions

/** Thrown when trying to add more than 2 jokers to a hand. */
class TooManyJokersException(message: String) extends Exception(message)