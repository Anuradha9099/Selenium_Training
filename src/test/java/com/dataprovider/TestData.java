package com.dataprovider;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TestData {

    @DataProvider
    public Object[][] userCredentials() {
        return new Object[][]{
                {"", "", "Epic sadface: Username is required"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"standard_user", "invalidPWD", "Epic sadface: Username and password do not match any user in this service"},
        };
    }

    // DataProvider that reads data from CSV
    @DataProvider(name = "csvUserCredentials")
    public Object[][] readFromCSV() throws IOException {

        // Relative path to the CSV file
        String relativePath = "src/test/java/com/dataprovider/testdata.csv";

        // Construct the absolute path
        File file = new File(relativePath);
        String filePath = file.getAbsolutePath();

        List<Object[]> data = new ArrayList<>();

        // Read the CSV file
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true; // To skip the header line
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip header
                }
                // Split each line by comma and add to data list
                String[] values = line.split(",", -1);
                data.add(new Object[]{values[0], values[1], values[2]});
            }
        }
        // Convert List to Object[][]
        return data.toArray(new Object[0][0]);
    }

    // Define a POJO class to map JSON objects
    static class TestDataCredentials {
        String username;
        String password;
        String expectedMessage;
    }

    // DataProvider that reads test data from a JSON file
    @DataProvider(name = "jsonUserCredentials")
    public Object[][] readFromJSON() throws IOException {
        // Path to the JSON file
        String relativePath = "src/test/java/com/dataprovider/testdata.json";
        String absolutePath = new File(relativePath).getAbsolutePath();

        // Use Gson to parse the JSON file into a List of TestData objects
        Gson gson = new Gson();
        Type listType = new TypeToken<List<TestDataCredentials>>() {
        }.getType();
        List<TestDataCredentials> testDataCredentialsList;

        // Read and parse the JSON file
        try (FileReader reader = new FileReader(absolutePath)) {
            testDataCredentialsList = gson.fromJson(reader, listType);
        }

        // Convert List<TestData> to Object[][] for TestNG DataProvider
        Object[][] data = new Object[testDataCredentialsList.size()][3];
        for (int i = 0; i < testDataCredentialsList.size(); i++) {
            TestDataCredentials testDataCredentials = testDataCredentialsList.get(i);
            data[i] = new Object[]{testDataCredentials.username, testDataCredentials.password, testDataCredentials.expectedMessage};
        }
        return data;
    }

    // DataProvider to read test data from XML file
    @DataProvider(name = "xmlUserCredentials")
    public Object[][] readFromXML() throws Exception {
        // Path to the XML file
        String relativePath = "src/test/java/com/dataprovider/testdata.xml";
        String absolutePath = new File(relativePath).getAbsolutePath();

        // Parse the XML file
        File xmlFile = new File(absolutePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        // Get all <testcase> elements
        NodeList testCaseNodes = doc.getElementsByTagName("testcase");

        // Prepare data for the DataProvider
        Object[][] data = new Object[testCaseNodes.getLength()][3]; // 3 fields: username, password, expectedMessage

        // Extract data from each <testcase> element
        for (int i = 0; i < testCaseNodes.getLength(); i++) {
            String username = testCaseNodes.item(i).getChildNodes().item(1).getTextContent();
            String password = testCaseNodes.item(i).getChildNodes().item(3).getTextContent();
            String expectedMessage = testCaseNodes.item(i).getChildNodes().item(5).getTextContent();

            data[i] = new Object[]{username, password, expectedMessage};
        }
        return data;
    }
}
