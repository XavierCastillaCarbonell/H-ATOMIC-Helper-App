package persistance

import gremlin.scala._
import org.apache.tinkerpop.gremlin.neo4j.structure.Neo4jGraph
import persistance.local.LocalJsonDatabase
import persistance.neo4j.GraphDatabaseHelper
import utils.FileUtils

object GraphDbPopulationHelper {

  private val DB_PATH = "/home/xavi/neo4j-community-3.0.1/data/hatomicGraph.db"
  private val USERNAME_KEY = "neo4j"

  FileUtils.removeAll(DB_PATH) //removes previous graph files
  val graph: ScalaGraph[Neo4jGraph] = Neo4jGraph.open(DB_PATH).asScala //opens the graph

//  LocalJsonDatabase.questionList.left.foreach(
//    question => {
//      graph +(question.acronym.toString,
//        GraphDatabaseHelper.keyQuestion -> question,
//        GraphDatabaseHelper.keyFrequency -> question.frequency,
//        GraphDatabaseHelper.keyBlockName -> question.blockName.toString
//        )
//    }
//  )
  val names: List[String] = graph.V.value(GraphDatabaseHelper.keyBlockName).toList()
  println(names.size)
  names foreach {
    println(_)
  }
  graph.close
}