package exception

class ParseException(message: String) extends RuntimeException(message)
case object NotMatchesForPattern extends ParseException("Input doesn't satisfy required 'for' operator pattern")
