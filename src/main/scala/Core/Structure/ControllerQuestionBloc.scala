package Core.Structure

import scala.util.{Failure, Success, Try}
import org.json4s._
import org.json4s.jackson.Serialization._

/**
  * Class used to control a question bloc and their questions.
  *
  * @param  questions sequence of [[Question]] that would be returned when the [[ControllerQuestionBloc]] decides.
  * @param blocName   name of the bloc.
  * @param priority   priority of [[ControllerQuestionBloc]].
  */
case class ControllerQuestionBloc(blocName: String,
                                  questions: List[Question],
                                  priority: Int) {
  implicit val formats: Formats = DefaultFormats
  lazy val json: String = {
    write(this)
  }
  @inline var questionsOrdered: List[Question] = questions.sortBy(_.frequence).reverse
  @inline var ended: Boolean = false
  @inline var percentageCompleted: Float = 0f

  /**
    * Get the first [[Question]].
    *
    * @return the initial [[Question]].
    */
  def getInitialQuestion: Question = {
    questionsOrdered.head
  }

  /**
    * Gets the next unanswered question.
    *
    * @return [[Either]] a [[Question]] or a [[NoMoreQuestionsException]].
    */
  def getNextQuestion(): Either[Question, NoMoreQuestionsException] = {
    Try {
      questionsOrdered.filter(_.asked.equals(false)).head
    } match {
      case Success(value) => Left(value)
      case Failure(exc) =>
        ended = true
        Right(NoMoreQuestionsException("No more questions available"))
    }
  }

  def processAnswer(question: Question) {
    questionsOrdered.filter(_.id.equals(question.id)).head.answer = question.answer
    percentageCompleted = (questionsOrdered.filter(_.answer.isDefined).size / questions.size) * 100
  }

  def rescan() {
    questionsOrdered.foreach(
      question => question.answer match {
        case None => question.asked = false
      }
    )
  }
}

object ControllerQuestionBloc {
  implicit val formats: Formats = DefaultFormats

  def parseJson(json: String): ControllerQuestionBloc = {
    read[ControllerQuestionBloc](json)
  }
}

/**
  * Custom Exception used when there are no more questions.
  *
  * @param message message sent.
  */
sealed case class NoMoreQuestionsException(message: String) extends RuntimeException


