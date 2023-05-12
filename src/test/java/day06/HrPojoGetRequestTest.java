package day06;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import pojo.Employee;
import pojo.Region2;
import utilities.HrTestBase;

import static io.restassured.RestAssured.get;

public class HrPojoGetRequestTest extends HrTestBase {

    @Test
    public void test1(){
        JsonPath jsonPath=get("/regions").then().statusCode(200).extract().jsonPath();
//we want to store second region under items in Region class object

        Region2 region = jsonPath.getObject("items[1]", Region2.class);

        System.out.println("region.getId() = " + region.getRegionId());
        System.out.println("region.getRegion_name() = " + region.getRegionName());
      //  System.out.println("region.getLinks().get().getHref() = " + region.getLinks().get(0).getHref());


    }
    @Test
    public void test2(){
        JsonPath jsonPath=get("/employees").then().statusCode(200).extract().jsonPath();

        Employee emp1=jsonPath.getObject("items[0]", Employee.class);
        System.out.println("emp1.getJob_id() = " + emp1.getJob_id());
        System.out.println("emp1.getFirstName() = " + emp1.getFirstName());
        System.out.println("emp1.getLastName() = " + emp1.getLastName());
        System.out.println("emp1.getSalary() = " + emp1.getSalary());
    }
}
