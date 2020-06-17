package models

import play.api.libs.json.Json

case class Task(id: Long, taskName: String, taskDesription: String)

object Task {
  implicit val personFormat = Json.format[Task]
}
