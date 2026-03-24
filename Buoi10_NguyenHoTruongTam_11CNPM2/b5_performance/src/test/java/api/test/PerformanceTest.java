package api.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Step;
import static io.restassured.RestAssured.given;

public class PerformanceTest {

    
    @DataProvider(name = "apiData")
    public Object[][] apiData() {
        return new Object[][]{
                {"GET", "https://jsonplaceholder.typicode.com/users", 2000},
                {"GET", "https://jsonplaceholder.typicode.com/users/2", 1500},
                {"POST", "https://jsonplaceholder.typicode.com/users", 3000},
                {"POST", "https://jsonplaceholder.typicode.com/posts", 2000},
                {"DELETE", "https://jsonplaceholder.typicode.com/users/2", 1000},
        };
    }

    
    @Test(dataProvider = "apiData")
    public void testSLA(String method, String url, int maxMs) {

        long start = System.currentTimeMillis();

        int status =
                given()
                        .header("Content-Type", "application/json")
                        .body("{\"name\":\"test\"}")
                .when()
                        .request(method, url)
                .then()
                        .extract()
                        .statusCode();

        long time = System.currentTimeMillis() - start;

        // Allure step
        logStep(method, url, maxMs);

        // Log console
        System.out.println("=================================");
        System.out.println("API: " + method + " " + url);
        System.out.println("Status: " + status);
        System.out.println("Response time: " + time + " ms");

        // SLA assertion
        if (time > maxMs) {
            throw new AssertionError("SLA FAIL: " + time + "ms > " + maxMs + "ms");
        }
    }

    // 🔥 Allure Step
    @Step("Gọi {method} {url} - SLA: {maxMs}ms")
    public void logStep(String method, String url, int maxMs) {
    }

    // 🔥 MONITORING TEST
    @Test
    public void testMonitoring() {

        int runs = 10;
        long total = 0;
        long min = Long.MAX_VALUE;
        long max = 0;

        for (int i = 0; i < runs; i++) {

            long start = System.currentTimeMillis();

            given()
            .when()
                    .get("https://jsonplaceholder.typicode.com/users")
            .then()
                    .statusCode(200);

            long time = System.currentTimeMillis() - start;

            total += time;
            if (time < min) min = time;
            if (time > max) max = time;

            System.out.println("Run " + (i + 1) + ": " + time + " ms");
        }

        long avg = total / runs;

        System.out.println("========== MONITORING ==========");
        System.out.println("Average: " + avg + " ms");
        System.out.println("Min: " + min + " ms");
        System.out.println("Max: " + max + " ms");
    }
}