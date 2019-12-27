
import scala.concurrent.duration._
import scala.io.Source

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class TestSimulation extends Simulation {


	var requestCounter:Int = 0

	object emailGenerator {
		def getemail() : String = {
			requestCounter += 1
			return "diego.machado" + "%06d".format(requestCounter) + "@qaninja.com"
		}
	}

	val RequestModel = Source.fromFile("./src/gatling/resources/request.json").getLines.mkString
	var RequestFeed = Iterator.continually(Map("createUserRequest" -> ( RequestModel.replace("%EMAILNOVO%", emailGenerator.getemail()))))

	val httpProtocol = http.baseURL("https://reqres.in/api")

  /* CASO TENHA READERS A PASSAR
	val headers_0 = Map(
		"Content-Type" -> "application/json")*/

	val scn1 = scenario("CreateUserTest")
		.feed(RequestFeed)
		.exec(
			http("CreateUserTest")
			.post("/register")
			.headers(headers_0)
			.body(StringBody("""${createUserRequest}"""))
			.check(jsonPath("$..status").is("200"))
			//.check(jsonPath("$.id").saveAs("id"))
			//.check(jsonPath("$.token").saveAs("token"))
			//.check(status.saveAs("httpStatus"))
		)

	setUp(
		scn1.inject(constantUsersPerSec(5) during (2 seconds))
	).protocols(httpProtocol)
}
