package services.diagnosis.diagnosis_result

import model.Question
import utils.QuestionAcronyms

object MalformationDiagnosis {

  @inline val none: String = ""
  @inline val M1: String = "M1"
  @inline val M2: String = "M2"
  @inline val M3: String = "M3"

  @inline
  private[diagnosis] def processMalformation(questionList: List[Question],
                                             AC: Boolean): String = {
    @inline val AD: Boolean = questionList.find(
      _.acronym.equals(QuestionAcronyms.AD)
    ) match {
      case Some(question) =>
        question.answer match {
          case Some(answer) => answer
          case _ => false
        }
      case _ => false
    }
    @inline val IE: Boolean = questionList.find(
      _.acronym.equals(QuestionAcronyms.IE)
    ) match {
      case Some(question) =>
        question.answer match {
          case Some(answer) => answer
          case _ => false
        }
      case _ => false
    }
    @inline val MRI: Boolean = questionList.find(
      _.acronym.equals(QuestionAcronyms.MRI)
    ) match {
      case Some(question) =>
        question.answer match {
          case Some(answer) => answer
          case _ => false
        }
      case _ => false
    }
    @inline val CS: Boolean = questionList.find(
      _.acronym.equals(QuestionAcronyms.CS)
    ) match {
      case Some(question) =>
        question.answer match {
          case Some(answer) => answer
          case _ => false
        }
      case _ => false
    }

    processMalformationDiagnosis(
      AD = AD,
      IE = IE,
      MRI = MRI,
      CS = CS,
      AC = AC
    )
  }

  @inline
  private[this] def processMalformationDiagnosis(AD: Boolean,
                                                 IE: Boolean,
                                                 MRI: Boolean,
                                                 CS: Boolean,
                                                 AC: Boolean): String = {
    if (AD) {
      if (!AC) M1
      else M2
    }
    else if (!AD && IE) M3
    else if (MRI && !CS) {
      if (!AC) M1
      else M2
    }
    else if (!MRI && CS) M3
    else none
  }
}
