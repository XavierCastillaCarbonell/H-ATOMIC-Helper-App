package persistance

import model.Question
import utils.custom_exceptions.DatabaseRetrieveException

trait Database {
  def questionList: Either[List[Question], DatabaseRetrieveException]

  def persistResults(questions: List[Question]): Boolean
}
