package controllers

import implicits.ControllerImplicits
import javax.inject._
import play.api.libs.json.Json
import play.api.mvc._
import models.Task
import models.requests.{AddTaskRequest, UpdateTaskRequest}
import models.responses.ListTaskResponse
import repository.TaskRepository

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class TaskController @Inject()(
  cc: ControllerComponents,
  taskRepository: TaskRepository
)(implicit assetsFinder: AssetsFinder, executionContext: ExecutionContext)
    extends AbstractController(cc)
    with ControllerImplicits {

  def listAll: Action[AnyContent] = Action.async { implicit request =>
    taskRepository.getAll.map { tasks =>
      Ok(Json.toJson(tasks.map {
        ListTaskResponse.fromTaskRow
      }))
    }
  }

  def get(id: Long): Action[AnyContent] = Action.async { implicit request =>
    taskRepository.get(id).map {
      case Some(task) =>
        Ok(Json.toJson(ListTaskResponse.fromTaskRow(task)))
      case None =>
        NotFound(s"Task with id $id not found.")
    }
  }

  def add: Action[AnyContent] = Action.async { implicit request =>
    postRequestBody[AddTaskRequest] { body =>
      taskRepository
        .add(AddTaskRequest.toTaskRow(body))
        .map { id =>
          Ok(s"Task added with id $id.")
        }
        .recover {
          case e: IllegalArgumentException =>
            BadRequest(e.getMessage)
          case _: Throwable =>
            Conflict(s"Task with name `${body.name}` already exists.")
        }
    }
  }

  def update(id: Long): Action[AnyContent] = Action.async { implicit request =>
    postRequestBody[UpdateTaskRequest] { body =>
      taskRepository
        .update(UpdateTaskRequest.toTaskRow(id, body))
        .map { n =>
          if (n > 0)
            Ok(s"Task with id $id updated.")
          else
            NotFound(s"Task with id $id not found.")
        }
        .recover {
          case e: IllegalArgumentException =>
            BadRequest(e.getMessage)
          case _: Throwable =>
            Conflict(s"Task with name `${body.name}` already exists.")
        }
    }
  }

  def delete(id: Long): Action[AnyContent] = Action.async { implicit request =>
    taskRepository
      .delete(id)
      .map { rows =>
        if (rows > 0)
          Ok(s"Task with id $id deleted.")
        else
          NotFound(s"Task with id $id not found.")
      }
  }
}
