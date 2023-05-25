package com.myGatling.APITests

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.language.postfixOps

 class PutUpdateUserRequest extends Simulation{

  //protocol
  val httpProtocol = http
    .baseUrl("https://reqres.in/api")

   val Scn = scenario("Post API Request demo")
     .exec(
       http("Post Create User")
         .post("/users")
         .header("content-type", "application/json")
         .asJson
         .body(RawFileBody("data/user.json")).asJson
         .check(
           status.is(201),
           jsonPath("$.name").is("Hard1"))
     )
     .pause(1)

  setUp(
    Scn.inject(atOnceUsers(1)).protocols(httpProtocol))
 }

