package persistance.neo4j

import gremlin.scala._
import org.apache.commons.configuration.BaseConfiguration
import org.apache.tinkerpop.gremlin.neo4j.structure.Neo4jGraph

object GraphDatabaseHelper extends GraphDatabaseHelper

trait GraphDatabaseHelper {

  @inline val keyQuestion: Key[String] = Key[String]("question")
  @inline val keyFrequency: Key[Int] = Key[Int]("question")
  @inline val keyBlockName: Key[String] = Key[String]("blockName")
  @inline val keyAcronym: Key[String] = Key[String]("acronym")

//  private[neo4j] lazy val hosts: Seq[String] = {
//    ConfigFactory.
//      load("user_connections.conf").
//      getStringList("cassandra_user_connections_configuration.hosts").
//      toList
//  }
//  private[neo4j] lazy val keyspace: Seq[String] = {
//    ConfigFactory.
//      load("user_connections.conf").
//      getString("cassandra_user_connections_configuration.keyspace")
//  }
//  private[neo4j] lazy val hostKeyString: Seq[String] = {
//    val sb = new StringBuilder()
//    sb ++= hosts.head
//    hosts.drop(1).map(host => sb ++= s",$host")
//    sb.toString()
//  }
//
//  def connect(): ScalaGraph[Neo4jGraph] = {
//    val configuration: BaseConfiguration = new BaseConfiguration()
//    configuration.setProperty()
//    Neo4jGraph.open(configuration).asScala
//  }
}
