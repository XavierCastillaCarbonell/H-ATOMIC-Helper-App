package utils

object QuestionBlockNames extends Enumeration {
  type QuestionBlockName = Value

  val hypertension, amyloidAngiopathy, primaryTumor,
  metastaticTumor, oralAnticoagulantsAVK, oralAnticoagulantsNOAC,
  arteriovenousMalformation, cavernoma, infrequent
  : QuestionBlockName = Value
}
