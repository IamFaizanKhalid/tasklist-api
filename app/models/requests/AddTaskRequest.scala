package models.requests

import play.api.libs.json.{Json, JsonConfiguration}
import play.api.libs.json.JsonNaming.SnakeCase
import repository.rows.TaskRow

case class AddTaskRequest(name: String, description: Option[String]) {
  require(
    !name.isEmpty && name.length <= 100,
    "name must be non-empty and no more than 100 characters long."
  )
}

object AddTaskRequest {
  implicit val fontCase = JsonConfiguration(SnakeCase)
  implicit val format = Json.format[AddTaskRequest]

  def toTaskRow(taskRequest: AddTaskRequest): TaskRow = {
    TaskRow(
      id = 0L,
      name = taskRequest.name,
      description = taskRequest.description
    )
  }
}
