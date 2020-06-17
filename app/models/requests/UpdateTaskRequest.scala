package models.requests

import play.api.libs.json.{Json, JsonConfiguration}
import play.api.libs.json.JsonNaming.SnakeCase
import repository.rows.TaskRow

case class UpdateTaskRequest(name: String, description: Option[String]) {
  require(
    !name.isEmpty && name.length <= 100,
    "name must be non-empty and no more than 100 characters long."
  )
}

object UpdateTaskRequest {
  implicit val fontCase = JsonConfiguration(SnakeCase)
  implicit val format = Json.format[UpdateTaskRequest]

  def toTaskRow(id: Long, taskRequest: UpdateTaskRequest): TaskRow = {
    TaskRow(
      id = id,
      name = taskRequest.name,
      description = taskRequest.description
    )
  }
}
