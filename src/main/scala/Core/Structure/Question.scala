package Core.Structure

case class Question(id: Float,
                    question: String,
                    frequence: Int,
                    questionBloc: String
                   ) {
  var asked: Boolean = false
  var answer: Option[Boolean] = None
}
