public class Runner {

    public static void main(String[] args) {

        //1. Step:Registration to the Driver
        //2. Step: Create connection with database

        JDBCUtils.connectToDatabase("localhost", "postgres", "postgres","12345");

        //3.Create Statement
        JDBCUtils.createStatement();

        //4.Execute the query
       //JDBCUtils.createTable("School", "teachers VARCHAR(50)", "name VARCHAR(80)", "schoolId NUMERIC(3)");

       JDBCUtils.insertDataIntoTable("School", "teachers 'Tom'", "name 'Tom Hanks'","schoolId 234");


      //JDBCUtils.dropTable("School");


        //5.Step: Close the connection and statement

        JDBCUtils.closeConnectionAndStatement();



    }

}
