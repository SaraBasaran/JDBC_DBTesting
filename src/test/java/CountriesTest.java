import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountriesTest {

     /*
        Given
          User connects to the database

        When
          User sends the query to get the region ids from "countries" table

        Then
          Verify that the number of region ids greater than 1 is 17.

        And
          User closes the connection
       */

    @Test
    public void countryTest(){

        //1.step:User connects to the database
        JDBCUtils.connectToDatabase("localhost", "postgres", "postgres", "12345");

        //2.step:   User sends the query to get the region ids from "countries" table
        JDBCUtils.createStatement();
        List<Object> region_ids=JDBCUtils.getColumnList("region_id", "countries");
        System.out.println("region ids "+region_ids);

        // 3.Step: Verify that the number of region ids greater than 1 is 17.

        int idsGreaterThan1= region_ids.stream().filter(t-> (Integer)t>1).collect(Collectors.toList()).size();
        System.out.println("idsGreaterThan1 = " + idsGreaterThan1);
        assertEquals(17, idsGreaterThan1);

        //User closes the connection

        JDBCUtils.closeConnectionAndStatement();











    }



}
