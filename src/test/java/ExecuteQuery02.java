import java.sql.*;

public class ExecuteQuery02 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. Step:Registration to the Driver
        Class.forName("org.postgresql.Driver");
        //2. Step: Create connection with database
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "12345");
        //3 Step:Create statement
        Statement st= con.createStatement();

        //1.Example: Find the company and number_of_employees whose
        // number_of_employees is the second highest from the companies table.

        //1.way:

        String sql1= "SELECT company, number_of_employees FROM companies ORDER BY number_of_employees DESC OFFSET 1 ROW FETCH NEXT 1 ROW ONLY";
        ResultSet resultSet1= st.executeQuery(sql1);

        while (resultSet1.next()){
            System.out.println(resultSet1.getString("company") +" ->"+ resultSet1.getInt("number_of_employees"));
            //GOOGLE ->18000
        }

        //2.way:By using sub-query
        String sql2= "SELECT company, number_of_employees\n" +
                "FROM companies\n" +
                "WHERE number_of_employees = (SELECT MAX(number_of_employees)\n" +
                "\t\t\t\t\t\t\t FROM companies \n" +
                "\t\t\t\t\t\t\t WHERE number_of_employees <(SELECT MAX (number_of_employees) FROM companies))";

        ResultSet resultSet2= st.executeQuery(sql2);

        while (resultSet2.next()){
            System.out.println(resultSet2.getString("company")+ "->" + resultSet2.getInt("number_of_employees"));
            //GOOGLE->18000
        }
        //2.Example:Find the company names and number_of_employees whose number is less than average number
        String sql3="SELECT company, number_of_employees\n" +
                "FROM companies\n" +
                "WHERE number_of_employees < (SELECT AVG (number_of_employees)\n" +
                "                            FROM companies)";

        ResultSet resultSet3= st.executeQuery(sql3);

        while (resultSet3.next()){
            System.out.println(resultSet3.getString(1)+ "->" + resultSet3.getInt(2));
        }

        con.close();
        st.close();


    }
}
