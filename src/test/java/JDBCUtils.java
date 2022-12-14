import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtils {
    private static Connection connection;
    private static  Statement statement;

    public static Connection connectToDatabase(String hostName, String dataBaseNAme, String userName, String password){
        //1. Step:Registration to the Driver

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //2. Step: Create connection with database

        try {
            connection= DriverManager.getConnection("jdbc:postgresql://"+ hostName+":5433/"+ dataBaseNAme, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Connection is successful!");
        return connection;

    }
    //3 Step:Create statement
    public static Statement createStatement(){

        try {
            statement=connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Statement created!");

        return statement;
    }
    //4.Execute the query

    public static boolean execute(String query){

        boolean isExecuted;

        try {
            isExecuted= statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Command executed successfully");
        return isExecuted;

    }
    //5.Step: Close the connection and statement
    public static void closeConnectionAndStatement() {

        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        try {
            if (connection.isClosed() && statement.isClosed()) {
                System.out.println("Connection and statement closed");
            } else {
                System.out.println("Connection and statement not closed!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        public static void dropTable(String tableName){

            try {

                statement.execute("DROP TABLE " + tableName);
                System.out.println("Table " + tableName + " dropped!");

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }


        public static void createTable(String tableName, String... columnName_DataType){

        StringBuilder columnName_DataTypeStringBuilder= new StringBuilder("");

        for(String w: columnName_DataType) {

            columnName_DataTypeStringBuilder.append(w).append(",");

        }

        columnName_DataTypeStringBuilder.deleteCharAt(columnName_DataTypeStringBuilder.lastIndexOf(","));

        //Create Table xxxx ==> tableName

            try {
                statement.execute("CREATE TABLE "+ tableName+ "(" +columnName_DataTypeStringBuilder+")");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Table created successfully!");

        }

        //INSERT INTO tablename  (columnName1, columnName2 ...) VALUES (value1, value2 ...)

        public static void insertDataIntoTable(String tablename, String... columnName_Values){

        StringBuilder columnName= new StringBuilder("");
        StringBuilder value= new StringBuilder("");

            for(String w:columnName_Values) {

                int firstSpace = w.indexOf(" ");

                columnName.append(w.substring(0, firstSpace)).append(",");  //1
                value.append(w.substring(firstSpace)).append(",");  //2
            }


        //"INSERT INTO "+ tablename +  "(id smallint(3), name varchar(50), address varchar(80)) VALUES(456, 'Tom Hanks', 'Bakers Street New York')";

        columnName.deleteCharAt(columnName.lastIndexOf(","));
        value.deleteCharAt(value.lastIndexOf(","));

        String command="INSERT INTO "+ tablename +  "("+ columnName+")"+ "VALUES ("+value+")";

            try {
                statement.execute(command);
                System.out.println("Data inserted successfully into " + tablename);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }

        public static List<Object> getColumnList(String columnName, String tableName){

        ResultSet resultset=null;

        List<Object> columnData= new ArrayList<>();

        String query = "SELECT "+ columnName +" FROM " + tableName;

            try {
                resultset= statement.executeQuery(query);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            while (true){
                try {
                    if (!resultset.next()) break;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                try {
                    columnData.add(resultset.getObject(1));

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
            return columnData;

        }






    }












