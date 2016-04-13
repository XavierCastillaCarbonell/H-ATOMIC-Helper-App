package Core.Structure

import Persistance.LocalDatabase

import scala.util.{Failure, Success, Try}

object ControllerApplication {

  @inline val initalSet: ControllerQuestionBloc =
    LocalDatabase.getInitialSet()

  @inline
  val questionBlocList: List[ControllerQuestionBloc] = LocalDatabase.listBlocs

  @inline
  var mutableQuestionBlocList: List[ControllerQuestionBloc] =
    questionBlocList.sortBy(_.priority).reverse


  @inline
  def getFirstQuestion: Question = {
    initalSet.getInitialQuestion
  }

  def getNextQuestion(question: Question): Question = {
    @inline val selectedQuestionBloc: ControllerQuestionBloc = searchBloc(question)
    selectedQuestionBloc.processAnswer(question)
    initalSet.ended match {
      case true => selectNextQuestion()
      case false => initalSet.getNextQuestion() match {
        case Left(_) => _
        case Right(exc) => selectNextQuestion()
      }
    }
  }

  private[this] def selectNextQuestion(): Question = {
    Try {
      mutableQuestionBlocList.head.getNextQuestion() match {
        case Left(_) => _
        case Right(exc) =>
          mutableQuestionBlocList = mutableQuestionBlocList.filter(_.ended.equals(false))
          selectNextQuestion()
      }
    } match {
      case Success(_) => _
      case Failure(exc) =>
        rescanBlocs
        selectNextQuestion()
    }
  }

  private[this] def searchBloc(question: Question): ControllerQuestionBloc =
    questionBlocList.filter(_.blocName.equals(question.questionBloc)).head

  private[this] def rescanBlocs = {
    questionBlocList.foreach(questionBloc =>
      questionBloc.percentageCompleted match {
        case percentage if percentage < 100 => questionBloc.rescan
      }
    )
    questionBlocList.filter(_.percentageCompleted.equals(100f)).size match {
      case 0f => throw NoMoreQuestionsException("All questions have been done")
    }
    mutableQuestionBlocList = questionBlocList
  }
}
