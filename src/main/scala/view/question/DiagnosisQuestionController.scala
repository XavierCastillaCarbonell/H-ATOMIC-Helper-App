package view.question

import javafx.event.{ActionEvent, EventHandler}

import model.Question
import services.diagnosis.diagnosis_process.DiagnosisService

import scalafx.scene.control.{Button, Label}

case class DiagnosisQuestionController(lbl_question: Label,
                                       lbl_questionTag: Label,
                                       btn_YES: Button,
                                       btn_NO: Button,
                                       btn_PASS: Button,
                                       lbl_graphicInfo: Label,
                                       lbl_hatomicCode: Label,
                                       btn_redo: Button) {

  //TODO: Delete this var
  var actualScreenQuestion: Question = DiagnosisService.startDiagnosis()

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
  btn_redo.onAction = new EventHandler[ActionEvent] {
    override def handle(event: ActionEvent) {
      DiagnosisService.questionAnswerList = List[Question]()
      lbl_hatomicCode.text = "H-ATOMIC code (redo button)"
      startDiagnosis()
    }
  }

  @inline
  def response(answer: Option[Boolean] = None) {
    DiagnosisService.nextQuestion(actualScreenQuestion, answer) match {
      case Left(question) =>
        actualScreenQuestion = question
        writeQuestionScreen(question)
      case Right(noMoreQuestions) =>
        println()
        updateDataInformation()
        checkUnsawerQuestions()
    }
  }

  @inline
  def checkUnsawerQuestions() = {
    DiagnosisService.nextUnansweredQuestion() match {
      case Left(question) =>
        actualScreenQuestion = question
        writeQuestionScreen(question)
      case Right(noMoreQuestions) =>
        updateDataInformation()
        writeEndOfQuestions()
    }
  }

  @inline
  def startDiagnosis() = {
    writeQuestionScreen(actualScreenQuestion)
  }

  @inline
  private[this] def writeQuestionScreen(question: Question) = {
    lbl_question.text = question.question
    lbl_questionTag.text = question.blockName.toString
  }

  @inline
  private[this] def writeEndOfQuestions() = {
    lbl_question.text = "No more questions"
    lbl_questionTag.text = ""
    btn_YES.disable
    btn_NO.disable
    btn_PASS.disable
  }

  @inline
  def updateDataInformation() = {
    lbl_hatomicCode.text = DiagnosisService.intracerebralHemorrhageDiagnosis()
  }
}
