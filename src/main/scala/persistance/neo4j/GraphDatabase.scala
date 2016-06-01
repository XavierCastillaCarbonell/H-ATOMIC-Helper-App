package persistance.neo4j

import gremlin.scala.ScalaGraph
import model.Question
import org.apache.tinkerpop.gremlin.neo4j.structure.Neo4jGraph
import persistance.Database
import utils.QuestionBlockNames.{apply => _}
import utils.custom_exceptions.DatabaseRetrieveException

import scala.util.{Failure, Success, Try}

private[persistance] class GraphDatabase/* extends Database with GraphDatabaseHelper*/ {

//  @inline val graph: ScalaGraph[Neo4jGraph] = GraphDatabaseHelper.connect()
//
//  def questionList: Either[List[Question], DatabaseRetrieveException] = {
//    Try {
//      graph.V.toList().
//        foldLeft(List[Question]())(
//          (acc, vertex) => {
//            val graphQuestion =
//              List(
//                Question(
//                  question = vertex.value(keyQuestion.value),
//                  acronym = vertex.value(keyAcronym.value),
//                  frequency = vertex.value(keyAcronym.value),
//                  blockName = vertex.value(keyBlockName.value)
//                )
//              )
//            acc ::: graphQuestion
//          }
//        )
//    } match {
//      case Success(questionList) => Left(questionList)
//      case Failure(exc) => Right(DatabaseRetrieveException(exc.getMessage))
//    }
//  }
//
//  override def persistResults(questions: List[Question]): Boolean = {
//    //TODO: Implement
//    true
//  }
}
