package cl.uchile.dcc
package exceptions

/** Thrown when trying to remove a joker at an index that does not exist. */
class InvalidJokerIndexException(message: String) extends Exception(message)