package utils.custom_exceptions

/**
  * Custom Exception to inform an error on getting data from Database
  *
  * @param message message sent.
  */
sealed case class DatabaseRetrieveException(message: String) extends RuntimeException
