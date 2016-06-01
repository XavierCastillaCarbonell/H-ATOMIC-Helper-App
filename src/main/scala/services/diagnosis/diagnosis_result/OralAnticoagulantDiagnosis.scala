package services.diagnosis.diagnosis_result

import model.Question
import utils.QuestionAcronyms

object OralAnticoagulantDiagnosis {

  @inline val none: String = ""
  @inline val O1: String = "O1"
  @inline val O2: String = "O2"
  @inline val O3: String = "O3"

  @inline
  private[diagnosis] def processOralAnticoagulant(questionList: List[Question],
                                                  AC: Boolean): String = {
    @inline val AVK: Boolean = questionList.find(
      _.acronym.equals(QuestionAcronyms.AVK)
    ) match {
      case Some(question) =>
        question.answer match {
          case Some(answer) => answer
          case _ => false
        }
      case _ => false
    }
    @inline val NOAC: Boolean = questionList.find(
      _.acronym.equals(QuestionAcronyms.NOAC)
    ) match {
      case Some(question) =>
        question.answer match {
          case Some(answer) => answer
          case _ => false
        }
      case _ => false
    }
    @inline val INRGT2: Boolean = questionList.find(
      _.acronym.equals(QuestionAcronyms.INRGT2)
    ) match {
      case Some(question) =>
        question.answer match {
          case Some(answer) => answer
          case _ => false
        }
      case _ => false
    }
    @inline val BCTA: Boolean = questionList.find(
      _.acronym.equals(QuestionAcronyms.BCTA)
    ) match {
      case Some(question) =>
        question.answer match {
          case Some(answer) => answer
          case _ => false
        }
      case _ => false
    }

    processOralAnticoagulantDiagnosis(
      AVK = AVK,
      NOAC = NOAC,
      INRGT2 = INRGT2,
      BCTA = BCTA,
      AC = AC
    )
  }

  @inline
  private[this] def processOralAnticoagulantDiagnosis(AVK: Boolean,
                                                      NOAC: Boolean,
                                                      INRGT2: Boolean,
                                                      BCTA: Boolean,
                                                      AC: Boolean): String = {
    if (AVK) {
      if (INRGT2) {
        if (!AC) O1
        else O2
      }
      else {
        if (!AC) O2
        else O3
      }
    }
    else if (NOAC) {
      if (BCTA) {
        if (!AC) O1
        else O2
      }
      else {
        if (!AC) O2
        else O3
      }
    }
    else {
      none
    }
  }
}
