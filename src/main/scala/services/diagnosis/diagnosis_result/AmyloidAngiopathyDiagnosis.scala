package services.diagnosis.diagnosis_result

import model.Question
import utils.QuestionAcronyms

object AmyloidAngiopathyDiagnosis {

  @inline val none: String = ""
  @inline val A1: String = "A1"
  @inline val A2: String = "A2"
  @inline val A3: String = "A3"

  @inline
  private[diagnosis] def processAmyloidAngiopathy(questionList: List[Question],
                                                  AC: Boolean): String = {

    @inline val OT55: Boolean = questionList.find(
      _.acronym.equals(QuestionAcronyms.OT55)
    ) match {
      case Some(question) =>
        question.answer match {
          case Some(answer) => answer
          case _ => false
        }
      case _ => false
    }
    @inline val FLH: Boolean = questionList.find(
      _.acronym.equals(QuestionAcronyms.FLH)
    ) match {
      case Some(question) =>
        question.answer match {
          case Some(answer) => answer
          case _ => false
        }
      case _ => false
    }
    @inline val PMS: Boolean = questionList.find(
      _.acronym.equals(QuestionAcronyms.PMS)
    ) match {
      case Some(question) =>
        question.answer match {
          case Some(answer) => answer
          case _ => false
        }
      case _ => false
    }
    processAmyloidAngiopathyDiagnosis(
      OT55 = OT55,
      FLH = FLH,
      PMS = PMS,
      AC = AC
    )
  }

  @inline
  private[this] def processAmyloidAngiopathyDiagnosis(OT55: Boolean,
                                                      FLH: Boolean,
                                                      PMS: Boolean,
                                                      AC: Boolean): String = {
    if (PMS) {
      if (AC) {
        A2
      } else {
        A1
      }
    } else if (OT55 && FLH && !AC) {
      A3
    }
    else {
      none
    }
  }

}
