package com.myGatling.APITests

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.language.postfixOps

 class DeleteUserRequest extends Simulation{

  //protocol
  val httpProtocol = http
   .baseUrl("https://reqres.in/api")

  val createScn = scenario("Post API Request demo")
    .exec(
     http("Post Create User")
       .post("https://reqres.in/api/users")
       .header("content-type","application/json")
       .asJson
       .body(RawFileBody("data/deleteUser.json")).asJson
    .check(
      status.is(201),
      jsonPath("$.name").is("Hard3"))
    )
    .pause(1)

   val deleteScn = scenario("Delete API Request demo")
     .exec(
       http("Delete Create User")
         .delete("/users/2")
         .header("content-type", "application/json")
         .check(
           status.is(204)
         ))
     .pause(1)

  setUp(
    createScn.inject(atOnceUsers(1)),
    deleteScn.inject(atOnceUsers(1)
    ).protocols(httpProtocol))
 }

