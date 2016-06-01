package persistance

import model.Question
import persistance.local.LocalJsonDatabase
import utils.custom_exceptions.DatabaseRetrieveException

import scala.util.{Failure, Success, Try}

object ProxyDatabase extends Database {
  def questionList: Either[List[Question], DatabaseRetrieveException] = {
    localQuestionList()
//    Try {
//      GraphDatabase.questionList
//    } match {
//      case Success(questionList) => Left(questionList)
//      case Failure(exc) => localQuestionList()
//    }
  }

  private[this] def localQuestionList(): Either[List[Question], DatabaseRetrieveException] = {
    Try {
      LocalJsonDatabase.questionList
    } match {
      case Success(questionList) => questionList
      case Failure(exc) => Right(DatabaseRetrieveException(exc.getMessage))
    }
  }

  override def persistResults(questions: List[Question]): Boolean = true
}
