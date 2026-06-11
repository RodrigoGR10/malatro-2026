package cl.uchile.dcc
package exceptions

/** Thrown when trying to remove a card at an index that does not exist. */
class InvalidCardIndexException(message: String) extends Exception(message)