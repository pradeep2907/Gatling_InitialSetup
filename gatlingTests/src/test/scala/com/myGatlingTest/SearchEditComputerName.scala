package com.myGatlingTest

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class SearchEditComputerName extends Simulation {

	val httpProtocol = http
		.baseUrl("https://computer-database.gatling.io")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.9")
		.upgradeInsecureRequestsHeader("1")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36")

	val navigate = exec(http("NavigatetoPage")
			.get("/computers"))
		.pause(5)

val search = exec(http("SearchByAcE")
			.get("/computers?f=ACE"))
		.pause(4)

		val edit = exec(http("SelectCompanyName")
			.get("/computers/381"))
		.pause(5)
		.exec(http("EditCompanyName")
			.post("/computers/381")
			.formParam("name", "ACE")
			.formParam("introduced", "")
			.formParam("discontinued", "")
			.formParam("company", "2"))

	//val scn = scenario("SearchEditComputerName").exec(navigate, search, edit)

	val users = scenario("Users").exec(navigate, search)

	val admin = scenario("admin").exec(navigate, search, edit)

	setUp(
		admin.inject(rampUsers(4).during(10)),
		users.inject(rampUsers(10).during(10))
	).protocols(httpProtocol)
}