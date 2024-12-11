// Classe para inicializar as tabelas
class TableInitializer {
    public static void createTables(Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            // Cria a tabela de usuários
            stmt.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "username VARCHAR(50) NOT NULL, " +
                    "password VARCHAR(50) NOT NULL, " +
                    "phone1 VARCHAR(15), " +
                    "phone2 VARCHAR(15))");

            // Cria a tabela de comentários
            stmt.execute("CREATE TABLE IF NOT EXISTS comments (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "content TEXT NOT NULL, " +
                    "user_id INT NOT NULL, " +
                    "comment_date DATE NOT NULL, " +
                    "post_id INT, " +
                    "FOREIGN KEY (user_id) REFERENCES users(id))");
        }
    }
}