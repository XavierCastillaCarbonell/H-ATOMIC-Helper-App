package services.diagnosis.diagnosis_result

import model.Question
import utils.QuestionAcronyms

object InfrequentDiagnosis {

  @inline val none: String = ""
  @inline val I1: String = "I1"
  @inline val I2: String = "I2"
  @inline val I3: String = "I3"

  @inline
  private[diagnosis] def processInfrequent(questionList: List[Question],
                                           AC: Boolean): String = {
    @inline val infrequent: Boolean = questionList.find(
      _.acronym.equals(QuestionAcronyms.infrequent)
    ) match {
      case Some(question) =>
        question.answer match {
          case Some(answer) => answer
          case _ => false
        }
      case _ => false
    }
    processInfrequentDiagnosis(
      infrequent = infrequent,
      AC = AC
    )
  }

  @inline
  private[this] def processInfrequentDiagnosis(infrequent: Boolean,
                                               AC: Boolean): String = {
    if (infrequent) {
      if (AC) I3
      else I1
    }
    else none
  }
}
