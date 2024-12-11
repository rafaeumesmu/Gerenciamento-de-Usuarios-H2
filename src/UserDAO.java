// Classe DAO para gerenciar usuários
class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    // Adiciona um novo usuário ao banco de dados
    public void addUser(String username, String password, String phone1, String phone2) throws SQLException {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            throw new IllegalArgumentException("Username and password cannot be empty.");
        }
        String sql = "INSERT INTO users (username, password, phone1, phone2) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, phone1);
            stmt.setString(4, phone2);
            stmt.executeUpdate();
        }
    }

    // Lista todos os usuários
    public void listUsers() throws SQLException {
        String sql = "SELECT * FROM users";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("ID: %d | Username: %s | Phone1: %s | Phone2: %s\n",
                        rs.getInt("id"), rs.getString("username"), rs.getString("phone1"), rs.getString("phone2"));
            }
        }
    }

    // Atualiza um usuário existente
    public void updateUser(int userId, String username, String password, String phone1, String phone2) throws SQLException {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            throw new IllegalArgumentException("Username and password cannot be empty.");
        }
        String sql = "UPDATE users SET username = ?, password = ?, phone1 = ?, phone2 = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, phone1);
            stmt.setString(4, phone2);
            stmt.setInt(5, userId);
            stmt.executeUpdate();
        }
    }

    // Exclui um usuário pelo ID
    public void deleteUser(int userId) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }

    // Autentica um usuário verificando nome e senha
    public boolean authenticate(String username, String password) throws SQLException {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            throw new IllegalArgumentException("Username and password cannot be empty.");
        }
        String sql = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}