package com.myGatling.APITests

import io.gatling.core.Predef._
import io.gatling.http.Predef._

 class GetSingleUserRequest extends Simulation{

  //protocol
  val httpProtocol = http
    .baseUrl("https://reqres.in/api/users")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36")

  val scn = scenario("Get API Request demo")
    .exec(
     http("Get Single User")
    .get("/2")
    .check(
      status.is(200),
      jsonPath("$.data.first_name").is("Janet"))
    )
    .pause(1)

  setUp(
  scn.inject(atOnceUsers(1)))
    .protocols(httpProtocol)
}
