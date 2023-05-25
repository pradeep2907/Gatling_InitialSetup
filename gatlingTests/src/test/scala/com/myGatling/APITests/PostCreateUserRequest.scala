package com.myGatling.APITests

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.language.postfixOps

 class PostCreateUserRequest extends Simulation{

  //protocol
  val httpProtocol = http
    .baseUrl("https://reqres.in/api")

  val scn = scenario("Post API Request demo")
    .exec(
     http("Post Create User")
       .post("/users")
       .header("content-type","application/json")
       .asJson
       .body(RawFileBody("data/user.json")).asJson
//       .body(StringBody(
//         """
//           |{
//           |    "name": "Hard",
//           |    "job": "Rock"
//           |}
//           |""".stripMargin)).asJson
    .check(
      status.is(201),
      jsonPath("$.name").is("Hard1"))
    )
    .pause(1)

  setUp(
  scn.inject(atOnceUsers(1)))
    .protocols(httpProtocol)
}
