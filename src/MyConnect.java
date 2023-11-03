import java.sql.*;
import java.util.Properties;

public class MyConnect {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "76odudad";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet table = statement.executeQuery("SELECT * FROM public.training_hw_5");

            table.first();
            for (int i = 1; i <= table.getMetaData().getColumnCount(); i++) {
                System.out.printf("%-20s",table.getMetaData().getColumnName(i));
            }
            System.out.println();

            table.beforeFirst();
            while(table.next()) {
                for (int i = 1; i <= table.getMetaData().getColumnCount(); i++) {
                    System.out.printf("%-20s", table.getString(i));
                }
                System.out.println();
            }
        } catch (SQLException ex) {
            System.out.println("Cannot connect to DB: " + ex.getMessage());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}