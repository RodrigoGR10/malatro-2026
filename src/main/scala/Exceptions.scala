package cl.uchile.dcc

class TooManyCardsException(message: String) extends Exception(message)

class TooManyJokersException(message: String) extends Exception(message)

class InvalidCardIndexException(message: String) extends Exception(message)

class InvalidJokerIndexException(message: String) extends Exception(message)

class TooManyPlaysException(message: String) extends Exception(message)

class TooManyDiscardsException(message: String) extends Exception(message)

class TooManyCardsToPlayException(message: String) extends Exception(message)

class TooFewCardsToPlayException(message: String) extends Exception(message)

class InvalidPlayIndexException(message: String) extends Exception(message)
