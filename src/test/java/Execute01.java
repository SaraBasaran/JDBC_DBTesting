import java.sql.*;

public class Execute01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1. Step:Registration to the Driver
        Class.forName("org.postgresql.Driver");

        //2. Step: Create connection with database
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "12345");

        //3 Step:Create statement
        Statement st= con.createStatement();

        //4 Step:Execute query
        //1.Example:
        String sql= "CREATE TABLE workers(worker_id VARCHAR(10), worker_name VARCHAR(50), worker_salary INT)";
        boolean sqlResult= st.execute(sql);
        System.out.println(sqlResult);

        /*
        execute() can be used in DDL(Table creation, dropping table, altering the table) and DQL(Select)
        1) If you use execute() in DDL you will get "false" all the time
        2)If you use execute() in DQL you will get false or true
          When you use execute() in DQL, if you get ResultSet Object in return you will get true otherwise you will get
          false.
         */


       // --2.Example: Alter the table by adding worker_address column into the workers table
        //ALTER TABLE workers ADD worker_address VARCHAR(80);

        String sql1="ALTER TABLE workers ADD worker_address VARCHAR(80)";
        st.execute(sql1);

        //3.Example: Drop the table
//        String sql2= "DROP TABLE workers";
//        st.execute(sql2);

        //5. Step: Close the connection and statement

        con.close();
        st.close();





    }


}
