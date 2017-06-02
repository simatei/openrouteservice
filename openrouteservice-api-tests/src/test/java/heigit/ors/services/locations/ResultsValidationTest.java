package heigit.ors.services.locations;

import static io.restassured.RestAssured.*;

import org.junit.Test;

import static org.hamcrest.Matchers.is;

import org.json.JSONArray;
import org.json.JSONObject;

import heigit.ors.services.common.EndPointAnnotation;
import heigit.ors.services.common.ServiceTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

@EndPointAnnotation(name="locations")
public class ResultsValidationTest extends ServiceTest {
	public ResultsValidationTest() {
	}

	public RequestSpecification createGetRequest(String geometry, String id)
	{
		return given()
				.param("geometry", geometry)
				.param("id", id)
				.when();
	}

	@Test
	public void requestCategoryListTest() {
		Response response = given()
				.param("request", "category_list")
				.when()
				.get(getEndPointName());

		Assert.assertEquals(response.getStatusCode(), 200);
		JSONObject jResponse = new JSONObject(response.body().asString());

		JSONObject jCategories = jResponse.getJSONObject("categories");
		String[] categories = new String[] {"education", "historic", "natural", "financial", "leisure_and_entertainment", "sustenance", "tourism", "transport", "arts_and_culture", "healthcare", "service", "accomodation", "shops", "animals", "facilities", "public_places"};
		for(String cat : categories)
			Assert.assertNotNull(jCategories.get(cat));
	}
	
	@Test
	public void pointSearchWithoutNameFilterTest() {
		Response response = given()
		.param("request", "pois")
		.param("geometry", "{\"type\":\"Point\", \"coordinates\": [8.676826, 49.418675]}")
		.param("radius", "5000")
		.param("category_ids", "518")
		.param("limit", "200")
		.when()
		.get(getEndPointName());
		
		Assert.assertEquals(response.getStatusCode(), 200);
		JSONObject jResponse = new JSONObject(response.body().asString());
		
	    Assert.assertEquals(jResponse.getJSONArray("features").length(), 52);
	}
	
	@Test
	public void pointSearchWithNameFilterTest() {
		Response response = given()
		.param("request", "pois")
		.param("geometry", "{\"type\":\"Point\", \"coordinates\": [8.676826, 49.418675]}")
		.param("radius", "5000")
		.param("name", "Rewe*")
		.param("category_ids", "518")
		.param("limit", "200")
		.when()
		.get(getEndPointName());
		
		Assert.assertEquals(response.getStatusCode(), 200);
		JSONObject jResponse = new JSONObject(response.body().asString());
		
	    Assert.assertEquals(jResponse.getJSONArray("features").length(), 9);
	}

	@Test
	public void linestringSearchWithNameFilterTest() {
		Response response = given()
				.param("request", "pois")
				.param("geometry", "{\"type\":\"LineString\",\"coordinates\":[[8.677901,49.380469],[8.677865,49.380475],[8.677866,49.380552],[8.677872,49.381213],[8.677877,49.381825],[8.677879,49.381978],[8.677882,49.382359],[8.677944,49.382442],[8.678035,49.382508],[8.678339,49.382709],[8.678688,49.382703],[8.678728,49.382702],[8.67919,49.382698],[8.679703,49.382717],[8.68006,49.382795],[8.680646,49.382796],[8.680754,49.382778],[8.681173,49.382697],[8.682201,49.382683],[8.682194,49.383081],[8.682191,49.383845],[8.682195,49.384122],[8.683183,49.384069],[8.683925,49.384014],[8.685149,49.383959],[8.686774,49.383921],[8.686414,49.386822],[8.686411,49.386847],[8.686272,49.388251],[8.686216,49.388812],[8.686135,49.389628],[8.686063,49.390322],[8.686054,49.390381],[8.685988,49.391359],[8.685985,49.391385],[8.685839,49.392431],[8.685754,49.393515],[8.685792,49.393631],[8.68557,49.396521],[8.685569,49.396576],[8.685568,49.396745],[8.685566,49.397062],[8.685559,49.397228],[8.685551,49.397447],[8.685547,49.397509],[8.68552,49.397828],[8.685468,49.398075],[8.685424,49.398228],[8.68538,49.398355],[8.685371,49.398383],[8.685419,49.398473],[8.685452,49.398524],[8.685543,49.398617],[8.685635,49.398675],[8.685875,49.398739],[8.68595,49.398771],[8.686024,49.398814],[8.6867,49.398898],[8.688366,49.399064],[8.688511,49.39908],[8.68866,49.3991],[8.688714,49.399108],[8.689469,49.3992],[8.689638,49.399204],[8.689727,49.399199],[8.689721,49.399179],[8.689737,49.399128],[8.689763,49.399105],[8.689777,49.399097],[8.689803,49.399086],[8.689841,49.399078],[8.689889,49.399079],[8.689928,49.399087],[8.68995,49.399095],[8.689998,49.399135],[8.690004,49.399196],[8.689992,49.399215],[8.690057,49.399244],[8.690178,49.399311],[8.690237,49.399351],[8.6903,49.399406],[8.690389,49.399512],[8.690427,49.39959],[8.69054,49.399869],[8.690642,49.400138],[8.690671,49.400212],[8.690702,49.400293],[8.691018,49.400937],[8.691324,49.401644],[8.691371,49.401765],[8.691579,49.402296],[8.691639,49.402444],[8.691897,49.403116],[8.692142,49.403876],[8.692444,49.404977],[8.692478,49.40511],[8.69276,49.406177],[8.692774,49.40622],[8.692778,49.406254],[8.692786,49.406362],[8.692797,49.406566],[8.692833,49.406644],[8.692853,49.40684],[8.692882,49.406884],[8.692955,49.406936],[8.693067,49.406998],[8.693255,49.406964],[8.693458,49.406936],[8.693521,49.40693],[8.693814,49.40691],[8.693877,49.406905],[8.694084,49.406971],[8.694197,49.407026],[8.694252,49.407094],[8.694278,49.407167],[8.694296,49.407248],[8.694321,49.407481],[8.694327,49.407586],[8.694281,49.407734],[8.694227,49.407893],[8.694192,49.407979],[8.694069,49.408264],[8.693861,49.408768],[8.693777,49.409005],[8.693651,49.409362],[8.693559,49.409604],[8.69339,49.410024],[8.693358,49.410108],[8.693319,49.410213],[8.693121,49.41071],[8.692988,49.411013],[8.69292,49.411171],[8.692903,49.411228],[8.69289,49.411313],[8.692883,49.411382],[8.69287,49.411507],[8.69287,49.411526],[8.692846,49.411667],[8.69236,49.413495],[8.692257,49.413687],[8.692221,49.413768],[8.692218,49.413849],[8.692189,49.414262],[8.692151,49.41467],[8.692147,49.414727],[8.692126,49.414726],[8.692064,49.414719],[8.691032,49.414596],[8.690746,49.415131],[8.690981,49.415136],[8.691816,49.41514]]}")
				.param("radius", "200")
				.param("name", "Rewe*")
				.param("category_ids", "518")
				.param("limit", "200")
				.when()
				.get(getEndPointName());

		Assert.assertEquals(response.getStatusCode(), 200);
		JSONObject jResponse = new JSONObject(response.body().asString());
		Assert.assertEquals(jResponse.getJSONArray("features").length(), 2);
	}

	@Test
	public void idParameterTest() {
		Response response = given()
				.param("request", "category_list")
				.param("id", "34629723410")
				.when()
				.get(getEndPointName());

		Assert.assertEquals(response.getStatusCode(), 200);
		JSONObject jResponse = new JSONObject(response.body().asString());
		Assert.assertEquals(jResponse.getJSONObject("info").getJSONObject("query").get("id"), "34629723410");
	}
}
