// Classe principal que executa o programa
public class Main {
    public static void main(String[] args) {
        // Estabelece a conexão com o banco de dados
        try (Connection connection = DatabaseConfig.getConnection()) {
            // Inicializa as tabelas no banco de dados
            TableInitializer.createTables(connection);
            // Instancia os objetos DAO para usuários e comentários
            UserDAO userDAO = new UserDAO(connection);
            CommentDAO commentDAO = new CommentDAO(connection);
            Scanner scanner = new Scanner(System.in);

            // Loop principal para o menu
            while (true) {
                System.out.println("1. Add User\n2. List Users\n3. Update User\n4. Delete User\n5. Authenticate\n6. Add Comment\n7. List Comments by User\n8. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha

                try {
                    switch (choice) {
                        case 1:
                            // Adiciona um novo usuário
                            System.out.print("Enter username: ");
                            String username = scanner.nextLine();
                            System.out.print("Enter password: ");
                            String password = scanner.nextLine();
                            System.out.print("Enter phone1: ");
                            String phone1 = scanner.nextLine();
                            System.out.print("Enter phone2: ");
                            String phone2 = scanner.nextLine();
                            userDAO.addUser(username, password, phone1, phone2);
                            break;
                        case 2:
                            // Lista todos os usuários
                            userDAO.listUsers();
                            break;
                        case 3:
                            // Atualiza um usuário existente
                            System.out.print("Enter user ID: ");
                            int userId = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter username: ");
                            username = scanner.nextLine();
                            System.out.print("Enter password: ");
                            password = scanner.nextLine();
                            System.out.print("Enter phone1: ");
                            phone1 = scanner.nextLine();
                            System.out.print("Enter phone2: ");
                            phone2 = scanner.nextLine();
                            userDAO.updateUser(userId, username, password, phone1, phone2);
                            break;
                        case 4:
                            // Exclui um usuário
                            System.out.print("Enter user ID: ");
                            userId = scanner.nextInt();
                            userDAO.deleteUser(userId);
                            break;
                        case 5:
                            // Autentica um usuário com base no nome de usuário e senha
                            System.out.print("Enter username: ");
                            username = scanner.nextLine();
                            System.out.print("Enter password: ");
                            password = scanner.nextLine();
                            if (userDAO.authenticate(username, password)) {
                                System.out.println("Authentication successful.");
                            } else {
                                System.out.println("Invalid credentials.");
                            }
                            break;
                        case 6:
                            // Adiciona um comentário
                            System.out.print("Enter comment content: ");
                            String content = scanner.nextLine();
                            System.out.print("Enter user ID: ");
                            userId = scanner.nextInt();
                            System.out.print("Enter post ID: ");
                            int postId = scanner.nextInt();
                            commentDAO.addComment(content, userId, new Date(System.currentTimeMillis()), postId);
                            break;
                        case 7:
                            // Lista os comentários de um usuário
                            System.out.print("Enter user ID: ");
                            userId = scanner.nextInt();
                            commentDAO.listCommentsByUser(userId);
                            break;
                        case 8:
                            // Sai do programa
                            return;
                        default:
                            System.out.println("Invalid choice. Try again.");
                    }
                } catch (SQLException ex) {
                    System.out.println("Database error: " + ex.getMessage());
                } catch (Exception ex) {
                    System.out.println("Unexpected error: " + ex.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database: " + e.getMessage());
        }
    }
}
