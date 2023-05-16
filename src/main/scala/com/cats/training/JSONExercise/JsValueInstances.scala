package com.cats.training.JSONExercise

object JsValueInstances {

  implicit val Anonymous: JSWriter[Anonymous] = (anon: Anonymous) =>
    JsObject(
      Map(
        "id" -> JsString(s"${anon.id}"),
        "created at" -> JsString(s"${anon.createdAt}")
      )
    )

  implicit val User: JSWriter[User] = new JSWriter[User] {
    override def write(a: User): JsValue = JsObject(
      Map(
        "id" -> JsString(a.id),
        "email" -> JsString(a.email),
        "createdAt" -> JsString(s"${a.createdAt}")
      )
    )
  }
}
