// Classe DAO para gerenciar comentários
class CommentDAO {
    private Connection connection;

    public CommentDAO(Connection connection) {
        this.connection = connection;
    }

    // Adiciona um comentário ao banco de dados
    public void addComment(String content, int userId, Date commentDate, int postId) throws SQLException {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Content cannot be empty.");
        }
        String sql = "INSERT INTO comments (content, user_id, comment_date, post_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, content);
            stmt.setInt(2, userId);
            stmt.setDate(3, commentDate);
            stmt.setInt(4, postId);
            stmt.executeUpdate();
        }
    }

    // Lista todos os comentários de um usuário
    public void listCommentsByUser(int userId) throws SQLException {
        String sql = "SELECT * FROM comments WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.printf("ID: %d | Content: %s | Date: %s | Post ID: %d\n",
                            rs.getInt("id"), rs.getString("content"), rs.getDate("comment_date"), rs.getInt("post_id"));
                }
            }
        }
    }

    // Exclui um comentário pelo ID
    public void deleteComment(int commentId) throws SQLException {
        String sql = "DELETE FROM comments WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, commentId);
            stmt.executeUpdate();
        }
    }
}
