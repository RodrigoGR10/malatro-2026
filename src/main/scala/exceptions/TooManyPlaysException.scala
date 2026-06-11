package cl.uchile.dcc
package exceptions

/** Thrown when trying to play more than 3 times in a round. */
class TooManyPlaysException(message: String) extends Exception(message)