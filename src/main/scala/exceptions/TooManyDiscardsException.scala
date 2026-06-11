package cl.uchile.dcc
package exceptions

/** Thrown when trying to discard more than 3 times in a round. */
class TooManyDiscardsException(message: String) extends Exception(message)