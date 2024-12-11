import java.sql.*;
import java.util.Scanner;

// Classe para configurar a conexão com o banco de dados
class DatabaseConfig {
    public static Connection getConnection() throws SQLException {
        // Retorna a conexão com o banco de dados H2. Altere a string de conexão se necessário.
        return DriverManager.getConnection("jdbc:h2:./test", "sa", "");
    }
}
