package com.qa.restAssured;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.testng.annotations.Test;

import java.util.Iterator;

import static com.qa.util.LoggerUtil.log;

public class CreateJSONArrayUsingJacksonAPI {

  @Test
  private void createJsonArray() throws JsonProcessingException {

    ObjectMapper objectMapper = new ObjectMapper();

    // Create an array that will hold 2 JSON objects
    ArrayNode parentArray = objectMapper.createArrayNode();

    // Create Node that maps to JSON Objects structures in JSON content
    ObjectNode firstBookingDetails = objectMapper.createObjectNode();

    // It is similar to map put method. put method is overloaded to accept different
    // types of data
    // String as field value
    firstBookingDetails.put("firstName", "Jim");
    firstBookingDetails.put("lastName", "Brown");
    // integer as field value
    firstBookingDetails.put("totalPrice", 111);
    // boolean as field value
    firstBookingDetails.put("depositPaid", true);
    firstBookingDetails.put("additionalNeeds", "Breakfast");

    // Since requirement is to create nested JSON object structures in JSON content
    ObjectNode firstBookingDateDetails = objectMapper.createObjectNode();
    firstBookingDateDetails.put("checkIn", "2021-07-01");
    firstBookingDateDetails.put("checkout", "2021-07-10");

    firstBookingDetails.set("bookingDates", firstBookingDateDetails);

    // Create Node that maps to JSON Objects structures in JSON content
    ObjectNode secondBookingDetails = objectMapper.createObjectNode();

    secondBookingDetails.put("firstName", "Deepak");
    secondBookingDetails.put("lastName", "Rai");
    // integer as field value
    secondBookingDetails.put("totalPrice", 111);
    // boolean as field value
    secondBookingDetails.put("depositPaid", true);
    secondBookingDetails.put("additionalNeeds", "Breakfast");

    // Since requirement is to create nested JSON object structures in JSON content
    ObjectNode secondBookingDateDetails = objectMapper.createObjectNode();
    secondBookingDateDetails.put("checkIn", "2021-07-01");
    secondBookingDateDetails.put("checkout", "2021-08-10");

    secondBookingDetails.set("bookingDates", secondBookingDateDetails);

    // Add JSON Objects to JSON Array
    parentArray.add(firstBookingDetails);
    parentArray.add(secondBookingDetails);

    // OR
    // parentArray.addAll(Arrays.asList(firstBookingDateDetails,secondBookingDateDetails));

    String jsonArrayAsString =
        objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(parentArray);
    log("Created JSON Array is: ");
    log(jsonArrayAsString);

    System.out.println(
        "=======================================================================================");

    // To get JSON array element using index
    JsonNode firstElement = parentArray.get(0);
    log(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(firstElement));

    System.out.println(
        "=======================================================================================");

    // To get size of JSON array
    int sizeOfArray = parentArray.size();
    log("Size of array is " + sizeOfArray);

    log("=======================================================================================");

    // To iterate JSON array
    Iterator<JsonNode> iterator = parentArray.iterator();
    log("Printing Json Node using iterator");
    while (iterator.hasNext()) {
      JsonNode currentJsonNode = iterator.next();
      log(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(currentJsonNode));
    }

    System.out.println(
        "=======================================================================================");

    // To remove an element from array
    parentArray.remove(0);
    log(
        "After removing first element from array : "
            + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(parentArray));

    log("=======================================================================================");

    // To empty JSON Array
    parentArray.removeAll();
    log(
        "After removing all elements from array : "
            + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(parentArray));
  }
}
