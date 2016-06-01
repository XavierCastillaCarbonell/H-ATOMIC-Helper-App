package utils

object QuestionAcronyms extends Enumeration {
  type QuestionAcronym = Value

  val AC, PAH, EAH, ABP, DL, //1_hypertension.json
  OT55, FLH, PMS, //2_amyloidAngiopathy.json
  PB, SL, //3_primaryTumor.json
  KPC, OSL, ML, //4_metastaticTumor.json
  AVK, INRGT2, //5_oralAnticoagulantsAVK.json
  NOAC, BCTA, //6_  oralAnticoagulantsNOAC.json
  AD, IE, //7_arteriovenousMalformation.json
  MRI, CS, //8_cavernoma.json
  infrequent //9_infrequent.json
  : QuestionAcronym = Value
}
