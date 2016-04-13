package Persistance

import Core.Structure.{ControllerQuestionBloc, QuestionBlocNames}

import scala.io.Source

object LocalDatabase {

  @inline
  lazy val listBlocs: List[ControllerQuestionBloc] = {
    @inline val QBN = QuestionBlocNames
    List(
      ControllerQuestionBloc.
        parseJson(Source.fromFile(QBN.hypertension).mkString),
      ControllerQuestionBloc.
        parseJson(Source.fromFile(QBN.amyloidAngiopathy).mkString),
      ControllerQuestionBloc.
        parseJson(Source.fromFile(QBN.primaryTumor).mkString),
      ControllerQuestionBloc.
        parseJson(Source.fromFile(QBN.metasticTumor).mkString),
      ControllerQuestionBloc.
        parseJson(Source.fromFile(QBN.oralAnticoagulantsAVK).mkString),
      ControllerQuestionBloc.
        parseJson(Source.fromFile(QBN.oralAnticoagulantsNOAC).mkString),
      ControllerQuestionBloc.
        parseJson(Source.fromFile(QBN.arteriovenousMalformation).mkString),
      ControllerQuestionBloc.
        parseJson(Source.fromFile(QBN.cavernoma).mkString),
      ControllerQuestionBloc.
        parseJson(Source.fromFile(QBN.infrequent).mkString)
    )
  }

  def getInitialSet(): ControllerQuestionBloc = {
    ControllerQuestionBloc(
      blocName = "initialSet",
      questions = {

      },
      priority = 0
    )
  }

}
