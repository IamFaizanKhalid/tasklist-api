package repository.rows

import play.api.libs.json.Json

case class TaskRow(id: Long, name: String, description: Option[String])

object TaskRow {
  implicit val personFormat = Json.format[TaskRow]
}
