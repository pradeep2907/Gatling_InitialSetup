package com.myGatling.APITests

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.language.postfixOps

 class dataReadcsvFeeder extends Simulation{

  //protocol
   val httpProtocol = http.baseUrl("https://computer-database.gatling.io")

  //scn
   val feeder = csv("data/data1.csv").circular

   val scn = scenario("Feeds Learn")
     .repeat(1){
     feed(feeder)
     .exec { session =>
       println("Name: "+session("name").as[String])
       println("Job: "+session("job").as[String])
       println("Page: "+session("page").as[String])
       session
     }
     .pause(1)
     .exec(http("Goto ${page}")
      .get("/#{page}"))
   //    .get("/computers"))
     }
  //setUp
     setUp(scn.inject(atOnceUsers(4))).protocols(httpProtocol)

//  val httpProtocol = http
//    .baseUrl("https://reqres.in/api")
//
//  val scn = scenario("Post API Request demo")
//    .exec(
//     http("Post Create User")
//       .post("/users")
//       .header("content-type","application/json")
//       .asJson
//       .body(RawFileBody("data/user.json")).asJson
//    .check(
//      status.is(201),
//      jsonPath("$.name").is("Hard1"))
//    )
//    .pause(1)
//
//  setUp(
//  scn.inject(atOnceUsers(1)))
//    .protocols(httpProtocol)
}
