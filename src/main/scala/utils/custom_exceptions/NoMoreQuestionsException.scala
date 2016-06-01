package utils.custom_exceptions

/**
  * Custom Exception used when there are no more questions.
  *
  * @param message message sent.
  */
sealed case class NoMoreQuestionsException(message: String) extends RuntimeException