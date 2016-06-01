package persistance.local

import model.{ListQuestion, Question}
import persistance.Database
import utils.custom_exceptions.DatabaseRetrieveException

import scala.io.{BufferedSource, Source}
import scala.util.{Failure, Success, Try}

private[persistance] object LocalJsonDatabase extends Database {

  //TODO: Change "questionFiles" for a function that return the list of files in runTime

  //  @inline private[this] val questionJsonFiles: List[File] = {
  //    val questionsDirectory = new File("./resources/local_questions")
  //    println(questionsDirectory.getName)
  //    println(questionsDirectory.exists)
  //    println(questionsDirectory.isDirectory)
  //    if (questionsDirectory.exists() && questionsDirectory.isDirectory) {
  //      questionsDirectory.listFiles.filter(_.isFile).toList
  //    } else {
  //      List[File]()
  //    }
  //  }

  @inline
  private[this] val questionsFiles: Seq[String] = {
    Seq(
      "/home/xavi/H-ATOMIC_HelperApp/src/main/" +
        "resources/local_questions/1_hypertension.json",

      "/home/xavi/H-ATOMIC_HelperApp/src/main/" +
        "resources/local_questions/2_amyloidAngiopathy.json",

      "/home/xavi/H-ATOMIC_HelperApp/src/main/" +
        "resources/local_questions/3_primaryTumor.json",

      "/home/xavi/H-ATOMIC_HelperApp/src/main/" +
        "resources/local_questions/4_metastaticTumor.json",

      "/home/xavi/H-ATOMIC_HelperApp/src/main/" +
        "resources/local_questions/5_oralAnticoagulantsAVK.json",

      "/home/xavi/H-ATOMIC_HelperApp/src/main/" +
        "resources/local_questions/6_oralAnticoagulantsNOAC.json",

      "/home/xavi/H-ATOMIC_HelperApp/src/main/" +
        "resources/local_questions/7_arteriovenousMalformation.json",

      "/home/xavi/H-ATOMIC_HelperApp/src/main/" +
        "resources/local_questions/8_cavernoma.json",

      "/home/xavi/H-ATOMIC_HelperApp/src/main/" +
        "resources/local_questions/9_infrequent.json"
    )
  }

  @inline
  override def questionList: Either[List[Question], DatabaseRetrieveException] = {
    Try {
      questionsFiles.foldLeft(List[Question]())(
        (acc, questionFile) => {
          val buf: BufferedSource = Source.fromFile(questionFile)
          val fileQuestions: List[Question] = {
            ListQuestion.parseJson(buf.mkString.stripMargin).questions
          }
          buf.close()
          acc ::: fileQuestions
        }
      )
    } match {
      case Success(questionList) => Left(questionList)
      case Failure(exc) => Right(DatabaseRetrieveException(exc.getMessage))
    }
  }

  override def persistResults(questions: List[Question]): Boolean = {
    //TODO: Implement
    true
  }
}
