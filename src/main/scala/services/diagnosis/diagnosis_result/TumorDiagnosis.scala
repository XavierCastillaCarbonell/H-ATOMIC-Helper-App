package services.diagnosis.diagnosis_result

import model.Question
import utils.QuestionAcronyms

object TumorDiagnosis {

  @inline val none: String = ""
  @inline val T1: String = "T1"
  @inline val T2: String = "T2"
  @inline val T3: String = "T3"

  @inline
  private[diagnosis] def processTumor(questionList: List[Question],
                                      AC: Boolean): String = {
    @inline val PB: Boolean = questionList.find(
      _.acronym.equals(QuestionAcronyms.PB)
    ) match {
      case Some(question) =>
        question.answer match {
          case Some(answer) => answer
          case _ => false
        }
      case _ => false
    }
    //primaryTumor
    @inline val SL: Boolean = questionList.find(
      _.acronym.equals(QuestionAcronyms.SL)
    ) match {
      case Some(question) =>
        question.answer match {
          case Some(answer) => answer
          case _ => false
        }
      case _ => false
    }
    //metastatic Tumor
    @inline val KPC: Boolean = questionList.find(
      _.acronym.equals(QuestionAcronyms.KPC)
    ) match {
      case Some(question) =>
        question.answer match {
          case Some(answer) => answer
          case _ => false
        }
      case _ => false
    }
    @inline val OSL: Boolean = questionList.find(
      _.acronym.equals(QuestionAcronyms.OSL)
    ) match {
      case Some(question) =>
        question.answer match {
          case Some(answer) => answer
          case _ => false
        }
      case _ => false
    }
    @inline val ML: Boolean = questionList.find(
      _.acronym.equals(QuestionAcronyms.ML)
    ) match {
      case Some(question) =>
        question.answer match {
          case Some(answer) => answer
          case _ => false
        }
      case _ => false
    }

    processTumorDiagnosis(
      PB = PB,
      SL = SL,
      KPC = KPC,
      OSL = OSL,
      ML = ML,
      AC = AC
    )
  }

  @inline
  private[this] def processTumorDiagnosis(PB: Boolean,
                                          SL: Boolean,
                                          KPC: Boolean,
                                          OSL: Boolean,
                                          ML: Boolean,
                                          AC: Boolean): String = {
    if (SL && PB && !AC) T1
    else if (SL && !PB && !AC) T2
    else if (SL && !PB && AC) T3
    else if (KPC) {
      if (ML && !PB) {
        if (AC) T1
        else T2
      }
      else if (OSL) {
        if (PB && !AC) T1
        else if (!PB && !AC) T2
        else if (!PB && AC) T3
        else none
      }
      else none
    }
    else if (!KPC && ML && !PB && !AC) T3
    else none
  }
}
