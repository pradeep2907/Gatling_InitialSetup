package com.myGatlingTest

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import scala.concurrent.duration._

class OrangeHRLoginScenario extends Simulation {

  val httpProtocol = http
    .baseUrl("https://opensource-demo.orangehrmlive.com")
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())

  val scn = scenario("OrangeHRLoginScenario")
    .exec(http("InvokeApplication")
      .get("/web/index.php/auth/login")
      .resources(http("LaunchApp")
        .get("/web/dist/css/app.css?v=1683010990518")
        ,
        http("Launch_Still_On")
          .get("/web/dist/css/chunk-vendors.css?v=1683010990518")
        ,
        http("Launch_Still_On1")
          .get("/web/dist/js/chunk-vendors.js?v=1683010990518")
        ,
        http("Launch_Still_On2")
          .get("/web/dist/js/app.js?v=1683010990518")
        ,
        http("Launch_Still_On3")
          .get("/web/dist/favicon.ico?v=1683010990518")
        ,
        http("Launch_Still_On4")
          .get("/web/index.php/core/i18n/messages")
        ,
        http("Launch_Still_On5")
          .get("/web/dist/img/blob.svg")
        ,
        http("Launch_Still_On6")
          .get("/web/index.php/admin/theme/image/loginBanner?v=1683010990518")
      ))
    .pause(12)
    .exec(http("Auth_Session")
      .post("/web/index.php/auth/validate")
      .formParam("_token", "e1.QZ006DEVuLLjFHwDAM79uRPrt39qKdL_otjlQS8LGP8.EPdupF8t-ouXWC1HcbmL5iun2QYAaKK-26qVGF9gepc12mW9YFzo6JZmDw")
      .formParam("username", "Admin")
      .formParam("password", "admin123")
      .resources(http("Login_Session")
        .get("/web/dist/js/chunk-vendors.js?v=1683010990518")
        ,
        http("PostLogin_Session1")
          .get("/web/dist/js/app.js?v=1683010990518")
        ,
        http("PostLogin_Session2")
          .get("/web/dist/css/app.css?v=1683010990518"),
        http("PostLogin_Session3")
          .get("/web/dist/css/chunk-vendors.css?v=1683010990518")))
    .pause(1)
    .exec(http("PostLogin_Session4")
      .get("/web/dist/favicon.ico?v=1683010990518")
      .resources(http("PostLogin_Session5")
        .get("/web/index.php/core/i18n/messages")
        ,
        http("PostLogin_Session6")
          .get("/web/images/orangehrm-logo.png?v=1683010990518")
        ,
        http("PostLogin_Session7")
          .get("/web/images/orange.png?v=1683010990518")
        ,
        http("PostLogin_Session8")
          .get("/web/index.php/pim/viewPhoto/empNumber/7")
        ,
        http("PostLogin_Session9")
          .get("/web/index.php/api/v2/dashboard/employees/time-at-work?timezoneOffset=5.5&currentDate=2023-05-24&currentTime=18:11")
        ,
        http("View_Dashboard")
          .get("/web/index.php/api/v2/dashboard/employees/action-summary")
        ,
        http("request_21")
          .get("/web/index.php/api/v2/dashboard/shortcuts")
        ,
        http("request_22")
          .get("/web/index.php/api/v2/buzz/feed?limit=5&offset=0&sortOrder=DESC&sortField=share.createdAtUtc")
        ,
        http("request_23")
          .get("/web/index.php/api/v2/dashboard/employees/leaves?date=2023-05-24")
        ,
        http("request_24")
          .post("/web/index.php/events/push")
        ,
        http("request_25")
          .get("/web/index.php/api/v2/dashboard/employees/subunit")
        ,
        http("request_26")
          .get("/web/index.php/api/v2/dashboard/employees/locations")
      ))
    .pause(7)
    .exec(http("request_27")
      .get("/web/index.php/admin/viewAdminModule")

      .resources(http("request_28")
        .get("/web/dist/js/chunk-vendors.js?v=1683010990518")
        ,
        http("request_29")
          .get("/web/dist/js/app.js?v=1683010990518")
        ,
        http("request_30")
          .get("/web/dist/css/app.css?v=1683010990518"),
        http("request_31")
          .get("/web/dist/css/chunk-vendors.css?v=1683010990518")))
    .pause(1)
    .exec(http("request_32")
      .get("/web/dist/favicon.ico?v=1683010990518")
      .resources(http("request_33")
        .get("/web/index.php/core/i18n/messages")
        ,
        http("request_34")
          .get("/web/images/orange.png?v=1683010990518")
        ,
        http("request_35")
          .get("/web/images/orangehrm-logo.png?v=1683010990518")
        ,
        http("request_36")
          .get("/web/index.php/pim/viewPhoto/empNumber/7")
        ,
        http("request_37")
          .get("/web/index.php/api/v2/admin/users?limit=50&offset=0&sortField=u.userName&sortOrder=ASC")
      ))
    .pause(5)
    .exec(http("request_38")
      .get("/web/index.php/auth/logout")
    )
    .pause(1)
    .exec(http("request_39")
      .get("/web/dist/favicon.ico?v=1683010990518")
      .resources(http("request_40")
        .get("/web/index.php/core/i18n/messages")
        ,
        http("request_41")
          .get("/web/dist/img/blob.svg"),
        http("request_42")
          .get("/web/index.php/admin/theme/image/loginBanner?v=1683010990518")
      ))

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}