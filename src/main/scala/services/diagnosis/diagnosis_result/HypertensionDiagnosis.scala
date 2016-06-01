package services.diagnosis.diagnosis_result

import model.Question
import utils.QuestionAcronyms

object HypertensionDiagnosis {

  @inline val none: String = ""
  @inline val H1: String = "H1"
  @inline val H2: String = "H2"
  @inline val H3: String = "H3"

  @inline
  private[diagnosis] def processHypertension(questionList: List[Question],
                                             AC: Boolean): String = {

    @inline val PAH: Boolean = questionList.find(
      _.acronym.equals(QuestionAcronyms.PAH)
    ) match {
      case Some(question) =>
        question.answer match {
          case Some(answer) => answer
          case _ => false
        }
      case _ => false
    }

    @inline val EAH: Boolean = questionList.find(
      _.acronym.equals(QuestionAcronyms.EAH)
    ) match {
      case Some(question) =>
        question.answer match {
          case Some(answer) => answer
          case _ => false
        }
      case _ => false
    }

    @inline val ABP: Boolean = questionList.find(
      _.acronym.equals(QuestionAcronyms.ABP)
    ) match {
      case Some(question) =>
        question.answer match {
          case Some(answer) => answer
          case _ => false
        }
      case _ => false
    }

    @inline val DL: Boolean = questionList.find(
      _.acronym.equals(QuestionAcronyms.DL)
    ) match {
      case Some(question) =>
        question.answer match {
          case Some(answer) => answer
          case _ => false
        }
      case _ => false
    }

    processHypertensionDiagnosis(
      PAH = PAH,
      EAH = EAH,
      ABP = ABP,
      DL = DL,
      AC = AC
    )
  }

  @inline
  private[this] def processHypertensionDiagnosis(PAH: Boolean,
                                                 EAH: Boolean,
                                                 ABP: Boolean,
                                                 DL: Boolean,
                                                 AC: Boolean): String = {
    if (ABP && DL && (PAH || EAH)) {
      if (AC) {
        H2
      }
      else {
        H1
      }
    }
    else if ((PAH || EAH) && (ABP || DL)) {
      if (AC) {
        H3
      }
      else {
        H2
      }
    }
    else if ((PAH || EAH) && !AC) {
      H3
    }
    else if (ABP && DL) {
      H3
    }
    else {
      none
    }
  }

}
