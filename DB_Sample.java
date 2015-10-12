import java.sql.*;

public class DB_Sample {

    /**
     * �f�[�^�x�[�X�̃��[�UID
     */
    private final String USER_ID = "inouetakuya";

    /**
     * �f�[�^�x�[�X�̃p�X���[�h
     */
    private final String PASSWORD = "hogehoge";

    /**
     * JDBC�h���C�o
     */
    private final String DRIVER = "com.mysql.jdbc.Driver";

    /**
     * �f�[�^�x�[�X��URL
     */
    private final String URL = "jdbc:mysql://localhost/sample";

    /**
     * �f�[�^�x�[�X�̐ڑ�
     */
    private Connection connection;

    /**
     * �X�e�[�g�����g
     */
    private Statement statement;

    /**
     * �f�[�^�x�[�X�ɐڑ�����
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
     * �ڑ����N���[�Y����
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
     * SQL �����s����
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