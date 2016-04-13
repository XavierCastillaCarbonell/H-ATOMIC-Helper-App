package App.Desktop

import javafx.event.{ActionEvent, EventHandler}

import Core.Structure.{ControllerApplication, Question}

import scalafx.scene.control.{Button, Label}

case class WindowController(lbl_question: Label,
                            lbl_questionTag: Label,
                            btn_YES: Button,
                            btn_NO: Button,
                            btn_PASS: Button,
                            lbl_dataInfo: Label,
                            lbl_dataFirst: Label,
                            lbl_dataSecond: Label,
                            lbl_dataThird: Label) {

  //  @inline val totalQuestions: Int = ControllerApplication.getNumberTotalQuestions()

  var actualScreenQuestion: Question = setFirstQuestion()

  btn_YES.onAction = new EventHandler[ActionEvent] {
    override def handle(event: ActionEvent) {
      response(Some(true))
    }
  }

  btn_NO.onAction = new EventHandler[ActionEvent] {
    override def handle(event: ActionEvent) {
      response(Some(false))
    }
  }

  btn_PASS.onAction = new EventHandler[ActionEvent] {
    override def handle(event: ActionEvent) {
      response()
    }
  }

  @inline
  def response(answer: Option[Boolean] = None) {
    actualScreenQuestion.answer = answer
    nextQuestion()
  }

  @inline
  private[Desktop] def setFirstQuestion(): Question = {
    val firstQuestion: Question = ControllerApplication.getFirstQuestion
    lbl_question.text = firstQuestion.question
    lbl_questionTag.text = firstQuestion.questionBloc
    firstQuestion
  }

  @inline
  private[Desktop] def nextQuestion() {
    actualScreenQuestion = ControllerApplication.getNextQuestion(actualScreenQuestion)
    lbl_question.text = actualScreenQuestion.question
    lbl_questionTag.text = actualScreenQuestion.questionBloc
  }

  @inline
  private[Desktop] def updateDataInformation() {
    //    val mostProbableElements: Seq[String] = ControllerApplication.getProbableBlocs()
    //    lbl_dataFirst.text = mostProbableElements.head
    //    lbl_dataSecond.text = mostProbableElements.head
    //    lbl_dataThird.text = mostProbableElements.head
  }
}
