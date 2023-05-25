package com.myGatlingTest

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import scala.concurrent.duration._

class ComputerDatabase extends Simulation {

  val httpProtocol = http
    .baseUrl("https://computer-database.gatling.io")
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.9")
    .upgradeInsecureRequestsHeader("1")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36")

  val scn = scenario("ComputerDatabase")
    .exec(http("ComputerDatabasePage")
      .get("/computers")
    )
    .pause(1)
    .exec(http("NewComputerPage")
      .get("/computers/new")
    )
    .pause(1)
    .exec(http("CreateNewComputer")
      .post("/computers")
      .formParam("name", "Tester")
      .formParam("introduced", "2022-02-20")
      .formParam("discontinued", "2023-02-20")
      .formParam("company", "1"))
    .pause(1)
    .exec(http("FilterComputer")
      .get("/computers?f=Tester")
    )

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}