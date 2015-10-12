import java.sql.*;

public class DB_Sample {

    /**
     * データベースのユーザID
     */
    private final String USER_ID = "inouetakuya";

    /**
     * データベースのパスワード
     */
    private final String PASSWORD = "hogehoge";

    /**
     * JDBCドライバ
     */
    private final String DRIVER = "com.mysql.jdbc.Driver";

    /**
     * データベースのURL
     */
    private final String URL = "jdbc:mysql://localhost/sample";

    /**
     * データベースの接続
     */
    private Connection connection;

    /**
     * ステートメント
     */
    private Statement statement;

    /**
     * データベースに接続する
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void getConnection() throws ClassNotFoundException, SQLException {
        try {
            Class.forName(this.DRIVER);
            this.connection = DriverManager.getConnection(this.URL, this.USER_ID, this.PASSWORD);
            this.statement = connection.createStatement();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 接続をクローズする
     */
    public void close() {
        try {
            this.statement.close();
            this.connection.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    /**
     * SQL を実行する
     *
     * @throws SQLException
     */
    public void excuteUpdate(String sql) throws SQLException {
        try {
            this.statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}