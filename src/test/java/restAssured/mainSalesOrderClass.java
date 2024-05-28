package restAssured;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static restAssured.OrderConstants.*;
import static restAssured.orderPayload.*;

public class mainSalesOrderClass {
    public static void main(String[] args) {
        RestAssured.baseURI = BaseURL;
        Response createOrderResponse = given()
                .queryParam(ContentType, ContentTypeValue)
                .queryParam(HOST, HOST_VALUE)
                .queryParam(INTEROPAPINAME, CREATE_ORDER)
                .queryParam(FLOW, FLOW_PARAMTER_VALUE)
                .queryParam(SERVICE_NAME, "")
                .queryParam(USERID, ADMIN)
                .queryParam(PASSWORD, PASSWORD_VALUE)
                .queryParam(INTEROPAPIDATA, createOrderPayload())
                .when()
                .post(ResourceURL)
                .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract().response();

        XmlPath xmlPath = createOrderResponse.xmlPath();
        String orderNo = xmlPath.getString("Order.@OrderNo");
        String orderHeaderKey = xmlPath.getString("Order.@OrderHeaderKey");

        Response resolveOrderResponse = given()
                .queryParam(ContentType, ContentTypeValue)
                .queryParam(HOST, HOST_VALUE)
                .queryParam(INTEROPAPINAME, CHANGE_ORDER)
                .queryParam(FLOW, FLOW_PARAMTER_VALUE)
                .queryParam(SERVICE_NAME, "")
                .queryParam(USERID, ADMIN)
                .queryParam(PASSWORD, PASSWORD_VALUE)
                .queryParam(INTEROPAPIDATA, resolveHolds(orderNo))
                .when()
                .post(ResourceURL)
                .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract().response();
        assertEquals(200, resolveOrderResponse.getStatusCode());

        Response scheduleOrderResponse = given()
                .queryParam(ContentType, ContentTypeValue)
                .queryParam(HOST, HOST_VALUE)
                .queryParam(INTEROPAPINAME, SCHEDULE_ORDER)
                .queryParam(FLOW, FLOW_PARAMTER_VALUE)
                .queryParam(SERVICE_NAME, "")
                .queryParam(USERID, ADMIN)
                .queryParam(PASSWORD, PASSWORD_VALUE)
                .queryParam(INTEROPAPIDATA, scheduleOrder(orderHeaderKey))
                .when()
                .post(ResourceURL)
                .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract().response();
        assertEquals(200, scheduleOrderResponse.getStatusCode());

        Response releaseShipmentResponse = given()
                .queryParam(ContentType, ContentTypeValue)
                .queryParam(HOST, HOST_VALUE)
                .queryParam(INTEROPAPINAME, RELEASE_ORDER)
                .queryParam(FLOW, FLOW_PARAMTER_VALUE)
                .queryParam(SERVICE_NAME, "")
                .queryParam(USERID, ADMIN)
                .queryParam(PASSWORD, PASSWORD_VALUE)
                .queryParam(INTEROPAPIDATA, releaseOrder(orderHeaderKey,orderNo))
                .when()
                .post(ResourceURL)
                .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract().response();
        assertEquals(200, releaseShipmentResponse.getStatusCode());

        Response createShipmentResponse = given().log().all()
                .queryParam(ContentType, ContentTypeValue)
                .queryParam(HOST, HOST_VALUE)
                .queryParam(INTEROPAPINAME, CREATE_SHIPMENT)
                .queryParam(FLOW, FLOW_PARAMTER_VALUE)
                .queryParam(SERVICE_NAME, "")
                .queryParam(USERID, ADMIN)
                .queryParam(PASSWORD, PASSWORD_VALUE)
                .queryParam(INTEROPAPIDATA, createShipment(orderNo))
                .queryParam(INTEROPAPIDATA, releaseOrder(null,orderNo))
                .when()
                .post(ResourceURL)
                .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract().response();
        assertEquals(200, createShipmentResponse.getStatusCode());

        XmlPath xmlPath3 = createShipmentResponse.xmlPath();
        String shipmentNo = ((XmlPath) xmlPath3).getString("Shipment.@ShipmentNo");
        String shipmentKey = xmlPath3.getString("Shipment.@ShipmentKey");

        Response confirmShipmentResponse = given().log().all()
                .queryParam(ContentType, ContentTypeValue)
                .queryParam(HOST, HOST_VALUE)
                .queryParam(INTEROPAPINAME, CONFIRM_SHIPMENT)
                .queryParam(FLOW, FLOW_PARAMTER_VALUE)
                .queryParam(SERVICE_NAME, "")
                .queryParam(USERID, ADMIN)
                .queryParam(PASSWORD, PASSWORD_VALUE)
                .queryParam(INTEROPAPIDATA, confirmShipment(shipmentNo,shipmentKey))
                .when()
                .post(ResourceURL)
                .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract().response();

        assertEquals(200, confirmShipmentResponse.getStatusCode());

        System.out.println("Order Number: " + orderNo);
        System.out.println("Order Header Key: " + orderHeaderKey);
        System.out.println("Shipment Number: " + shipmentNo);
        System.out.println("Shipment Key: " + shipmentKey);
    }
}
