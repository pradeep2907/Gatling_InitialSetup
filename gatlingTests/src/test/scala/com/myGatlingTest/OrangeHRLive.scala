package com.myGatlingTest

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import scala.concurrent.duration._

class OrangeHRLive extends Simulation {

  val httpProtocol = http
    .baseUrl("https://opensource-demo.orangehrmlive.com")
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())

  val scn = scenario("OrangeHRLive")
    .exec(http("request_0")
      .get("/web/index.php/auth/login")
      .resources(http("request_1")
        .get("/web/dist/js/chunk-vendors.js?v=1683010990518")
        ,
        http("request_2")
          .get("/web/dist/js/app.js?v=1683010990518")
      ))
    .pause(1)
    .exec(http("request_3")
      .get("/web/dist/favicon.ico?v=1683010990518")
      .resources(http("request_4")
        .get("/web/dist/favicon.ico?v=1683010990518"),
        http("request_5")
          .get("/web/index.php/core/i18n/messages")
        ,
        http("request_6")
          .get("/web/images/ohrm_branding.png?v=1683010990518")
        ,
        http("request_7")
          .get("/web/dist/img/blob.svg")
      ))
    .pause(1)
    .exec(http("request_8")
      .post("/web/index.php/auth/validate")

      .formParam("_token", "788102184b977ef0b399137da84.2gWqX9HXgbK2pQTn2stk9AB1CPVpFrinz677F-tn0bw.tkLNG_z6svjykVyz46kAxXMxYaoNX-_Um8CsX4QMus2sVfoXkK_T5-nuQQ")
      .formParam("username", "Admin")
      .formParam("password", "admin123"))
    .pause(1)
    .exec(http("request_9")
      .get("/web/dist/favicon.ico?v=1683010990518")
      .resources(http("request_10")
        .get("/web/index.php/core/i18n/messages")
        ,
        http("request_11")
          .get("/web/images/orange.png?v=1683010990518")
        ,
        http("request_12")
          .get("/web/index.php/api/v2/dashboard/employees/time-at-work?timezoneOffset=5.5&currentDate=2023-05-23&currentTime=19:29")
        ,
        http("request_13")
          .get("/web/index.php/api/v2/dashboard/employees/action-summary")
        ,
        http("request_14")
          .get("/web/index.php/api/v2/dashboard/shortcuts")
        ,
        http("request_15")
          .get("/web/index.php/api/v2/buzz/feed?limit=5&offset=0&sortOrder=DESC&sortField=share.createdAtUtc")
        ,
        http("request_16")
          .get("/web/index.php/api/v2/dashboard/employees/leaves?date=2023-05-23")
        ,
        http("request_17")
          .get("/web/index.php/api/v2/dashboard/employees/subunit")
        ,
        http("request_18")
          .get("/web/index.php/api/v2/dashboard/employees/locations")
        ,
        http("request_19")
          .get("/web/images/orangehrm-logo.png?v=1683010990518")
        ,
        http("request_20")
          .post("/web/index.php/events/push")
				,
        http("request_21")
          .get("/web/index.php/pim/viewPhoto/empNumber/7")
        ,
        http("request_22")
          .get("/web/index.php/pim/viewPhoto/empNumber/15")
        ,
        http("request_23")
          .get("/web/index.php/pim/viewPhoto/empNumber/2")
        ,
        http("request_24")
          .get("/web/index.php/pim/viewPhoto/empNumber/50")
        ,
        http("request_25")
          .get("/web/index.php/buzz/photo/12")
        ,
        http("request_26")
          .get("/web/index.php/buzz/photo/11")
        ,
        http("request_27")
          .get("/web/index.php/pim/viewPhoto/empNumber/5")
      ))

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}