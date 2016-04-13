package Core.Structure

object QuestionBlocNames extends Enumeration {
  val hypertension: String = "hypertension"
  val amyloidAngiopathy: String = "amyloidAngiopathy"
  val primaryTumor: String = "primaryTumor"
  val metasticTumor: String = "metastaticTumor"
  val oralAnticoagulantsAVK: String = "oralAnticoagulantsAVK"
  val oralAnticoagulantsNOAC: String = "oralAnticoagulantsNOAC"
  val arteriovenousMalformation: String = "arteriovenousMalformation"
  val cavernoma: String = "cavernoma"
  val infrequent: String = "infrequent"

  val enums: Seq[String] = Seq(
    hypertension,
    amyloidAngiopathy,
    primaryTumor,
    metasticTumor,
    oralAnticoagulantsAVK,
    oralAnticoagulantsNOAC,
    arteriovenousMalformation,
    cavernoma,
    infrequent
  )
}
