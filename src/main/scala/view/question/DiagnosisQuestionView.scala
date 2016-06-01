package view.question

import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout._
import scalafx.scene.paint.Color._
import scalafx.scene.text.Font

//TODO: create custom Labels and custom Buttons
object DiagnosisQuestionView {

  @inline val lbl_question: Label = new Label {
    font = Font.font("Arial", 18)
  }
  @inline val lbl_questionTag: Label = new Label {
    font = Font.font("Arial", 16)
  }
  @inline val topBox: VBox = new VBox {
    padding = Insets(10, 5, 0, 5) //The top,right,bottom,left padding around the region's content
    spacing = 10 //The amount of vertical space between each child in the vbox
    children = List(lbl_question, lbl_questionTag)
  }

  @inline val btn_YES: Button = new Button {
    text = "YES"
    prefWidth = 160
    prefHeight = 120
    style = "-fx-base: green"
  }
  @inline val btn_NO: Button = new Button {
    text = "NO"
    prefWidth = 160
    prefHeight = 120
    style = "-fx-base: red"
  }
  @inline val btn_PASS: Button = new Button {
    text = "PASS"
    prefWidth = 160
    prefHeight = 120
    style = "-fx-base: yellow"
  }
  @inline val centralBox: HBox = new HBox {
    padding = Insets(15, 5, 15, 5)
    spacing = 10 //The amount of horizontal space between each child in the hbox
    children = List(btn_YES, btn_NO, btn_PASS)
  }

  @inline val lbl_graphicInfo: Label = new Label("dataLabel")
  @inline val lbl_hatomicCode: Label = new Label("H-ATOMIC code") {
    font = Font.font("Arial", 22)
  }
  @inline val btn_redo: Button = new Button {
    text = "REDO"
    prefWidth = 75
    prefHeight = 50
  }
  @inline val bottomBox: HBox = new HBox() {
    spacing = 10
    children = List(lbl_graphicInfo, lbl_hatomicCode, btn_redo)
  }

  val columnConstraints: ColumnConstraints = new ColumnConstraints {
    percentWidth = 100
  }
  val row_questions = new RowConstraints {
    percentHeight = 25
  }
  val row_buttons = new RowConstraints {
    percentHeight = 40
  }
  val row_info = new RowConstraints {
    percentHeight = 35
  }

  val gridPane = new GridPane {
    prefWidth = 500
    prefHeight = 450
    padding = Insets(10, 5, 5, 5)
    add(topBox, 0, 0)
    add(centralBox, 0, 1)
    add(bottomBox, 0, 2)
    gridLinesVisible = true
  }
  gridPane.getRowConstraints.addAll(row_questions, row_buttons, row_info)
  gridPane.getColumnConstraints.add(columnConstraints)

  new PrimaryStage {
    title = "H-ATOMIC HELPER APPLICATION"
    centerOnScreen()
    scene = new Scene(500, 450) {
      fill = White
      content = gridPane
    }
  }
  DiagnosisQuestionController(
    lbl_question = lbl_question,
    lbl_questionTag = lbl_questionTag,
    btn_YES = btn_YES,
    btn_NO = btn_NO,
    btn_PASS = btn_PASS,
    lbl_graphicInfo = lbl_graphicInfo,
    lbl_hatomicCode = lbl_hatomicCode,
    btn_redo = btn_redo
  ).startDiagnosis()
}