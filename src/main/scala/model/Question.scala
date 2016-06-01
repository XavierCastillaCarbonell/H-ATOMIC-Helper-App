package model

import org.json4s.ext.EnumNameSerializer
import org.json4s.jackson.Serialization._
import org.json4s.{DefaultFormats, Formats}
import utils.QuestionAcronyms.QuestionAcronym
import utils.QuestionBlockNames.QuestionBlockName
import utils.{QuestionAcronyms, QuestionBlockNames}

case class Question(question: String,
                    acronym: QuestionAcronym,
                    frequency: Int,
                    blockName: QuestionBlockName,
                    answer: Option[Boolean] = None) {
  require(
    requirement = frequency >= 0,
    message = "Frequency cannot be negative"
  )

  implicit val formats: Formats = DefaultFormats + new EnumNameSerializer(QuestionBlockNames) + new EnumNameSerializer(QuestionAcronyms)

  lazy val json: String = {
    write(this)
  }

  override def equals(that: Any): Boolean =
    that match {
      case that: Question => that.acronym.equals(this.acronym)
      case _ => false
    }

  override def hashCode: Int = {
    frequency * 12 * frequency
  }
}

case class ListQuestion(questions: List[Question]) {
  implicit val formats: Formats = DefaultFormats + new EnumNameSerializer(QuestionBlockNames) + new EnumNameSerializer(QuestionAcronyms)

  lazy val json: String = {
    write(this)
  }
}

object ListQuestion {
  implicit val formats: Formats = DefaultFormats + new EnumNameSerializer(QuestionBlockNames) + new EnumNameSerializer(QuestionAcronyms)

  def parseJson(json: String): ListQuestion = {
    read[ListQuestion](json)
  }
}