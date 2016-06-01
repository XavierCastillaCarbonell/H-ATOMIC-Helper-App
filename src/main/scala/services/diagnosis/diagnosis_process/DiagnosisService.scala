package services.diagnosis.diagnosis_process

import model.Question
import persistance.ProxyDatabase
import persistance.local.LocalJsonDatabase
import utils.custom_exceptions.NoMoreQuestionsException

import scala.util.{Failure, Success, Try}

object DiagnosisService {

  @inline val questionList: List[Question] = ProxyDatabase.questionList match {
    case Left(list) => list
  }//questionList

  @inline val questionListIterable: Iterator[Question] = questionList.iterator //questionList iterator

  //TODO: This will go out as soon as local graph database is implemented.
  @inline var questionAnswerList: List[Question] = List[Question]() //List of answered Questions

  def startDiagnosis(): Question = {
    questionListIterable.next()
  }

  @inline
  def nextQuestion(question: Question, answer: Option[Boolean]
                  ): Either[Question, NoMoreQuestionsException] = {
    insertAnswer(
      question = question,
      answer = answer
    )
    questionListIterable.hasNext match {
      case true => Left(questionListIterable.next())
      case false =>
       Right(NoMoreQuestionsException("There are no more questions"))
    }
  }

  @inline
  def nextUnansweredQuestion(): Either[Question, NoMoreQuestionsException] = {
    Try {
      //TODO: make this to not return always the head
      (questionList diff questionAnswerList).head
    } match {
      case Success(questionFound) => Left(questionFound)
      case Failure(exc) =>
        Right(NoMoreQuestionsException("There are no more questions"))
    }
  }

  @inline
  def intracerebralHemorrhageDiagnosis(): String = {
    DiagnosisResultsService.processAnswer(
      questionAnswerList.filter(_.answer.isDefined)
    )
  }

  @inline
  def insertAnswer(question: Question,
                   answer: Option[Boolean]) = {
    answer match {
      case Some(value) =>
        questionAnswerList = questionAnswerList :+ question.copy(answer = answer)
      case _ =>
    }
  }
}
