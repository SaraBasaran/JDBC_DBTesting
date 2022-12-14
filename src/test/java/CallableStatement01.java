import java.lang.reflect.Type;
import java.sql.*;

public class CallableStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. Step:Registration to the Driver
        Class.forName("org.postgresql.Driver");
        //2. Step: Create connection with database
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "12345");
        //3 Step:Create statement
        Statement st= con.createStatement();

        //1.Example: Create a function which uses 2 parameters and return the sum of the parameters
        //1.Step: Type the code to create the function
        String sql1= "CREATE OR REPLACE FUNCTION additionF(x NUMERIC, y NUMERIC) RETURNS NUMERIC LANGUAGE plpgsql AS $$ BEGIN RETURN x+y; END $$";

        //2.Step: Execute the function
        st.execute(sql1);

        //3.Step: Call the function
        CallableStatement cst1= con.prepareCall("{ ? = call additionF(?, ?)}");

        //4.Step:

        cst1.registerOutParameter(1, Types.NUMERIC);

        cst1.setInt(2, 6);
        cst1.setInt(3, 4);

        cst1.execute();

        System.out.println(cst1.getObject(1)); //10

        con.close();
        st.close();


    }
}
