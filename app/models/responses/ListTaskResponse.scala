package models.responses

import play.api.libs.json.{Json, JsonConfiguration}
import play.api.libs.json.JsonNaming.SnakeCase
import repository.rows.TaskRow

case class ListTaskResponse(id: Long, name: String, description: String)

object ListTaskResponse {
  implicit val fontCase = JsonConfiguration(SnakeCase)
  implicit val format = Json.format[ListTaskResponse]

  def fromTaskRow(taskRow: TaskRow): ListTaskResponse = {
    ListTaskResponse(
      id = taskRow.id,
      name = taskRow.name,
      description = taskRow.description.getOrElse("")
    )
  }
}
