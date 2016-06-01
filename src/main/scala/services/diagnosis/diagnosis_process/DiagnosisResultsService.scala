package services.diagnosis.diagnosis_process

import model.Question
import services.diagnosis.diagnosis_result._
import utils.QuestionBlockNames

object DiagnosisResultsService {

  @inline
  private[services] def processAnswer(questionList: List[Question]): String = {
    h_atomicCode(questionList)
  }

  private[this] def h_atomicCode(questionList: List[Question]): String = {
    val hatomicCode: String = hatomicSequence(
      questionList = questionList,
      alternativeCause = false
    ) mkString ""

    hatomicCode.length match {
      case 0 => "cryptogenic"
      case 2 => hatomicCode
      case _ =>
        hatomicSequence(
          questionList = questionList,
          alternativeCause = true
        ) mkString ""
    }
  }

  @inline
  private[this] def hatomicSequence(questionList: List[Question],
                                    alternativeCause: Boolean): List[String] ={
    List(
      HypertensionDiagnosis.processHypertension(
        questionList.
          filter(
            _.blockName.equals(QuestionBlockNames.hypertension)
          ),
        AC = alternativeCause
      ),
      AmyloidAngiopathyDiagnosis.processAmyloidAngiopathy(
        questionList.
          filter(
            _.blockName.equals(QuestionBlockNames.amyloidAngiopathy)
          ),
        AC = alternativeCause
      ),
      TumorDiagnosis.processTumor(
        questionList.
          filter(blockName =>
            blockName.blockName.equals(QuestionBlockNames.primaryTumor)
              ||
              blockName.blockName.equals(QuestionBlockNames.metastaticTumor)
          ),
        AC = alternativeCause
      ),
      OralAnticoagulantDiagnosis.processOralAnticoagulant(
        questionList.
          filter(blockName =>
            blockName.blockName.equals(QuestionBlockNames.oralAnticoagulantsAVK)
              ||
              blockName.blockName.equals(QuestionBlockNames.oralAnticoagulantsNOAC)
          ),
        AC = alternativeCause
      ),
      MalformationDiagnosis.processMalformation(
        questionList.
          filter(blockName =>
            blockName.blockName.equals(QuestionBlockNames.arteriovenousMalformation)
              ||
              blockName.blockName.equals(QuestionBlockNames.cavernoma)
          ),
        AC = alternativeCause
      ),
      InfrequentDiagnosis.processInfrequent(
        questionList.
          filter(
            _.blockName.equals(QuestionBlockNames.infrequent)
          ),
        AC = alternativeCause
      )
    )
  }
}
