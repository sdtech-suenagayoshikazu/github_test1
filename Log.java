import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import org.apache.commons.lang.time.DurationFormatUtils;

public class Log {

    /**
     * �����̊J�n����
     */
    private Date startTime;

    /**
     * �����̏I������
     */
    private Date endTime;

    /**
     * ��������
     */
    private String diffTime;

    /**
     * �������e
     */
    private String work;

    /**
     * ������
     */
    private int count;

    /**
     * ���l
     */
    private String remark;

    /**
     * DateFormat
     *
     * yyyy/MM/dd HH:mm;ss
     */
    private DateFormat dateFormat =
        DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);

    /**
     * �J�n�������Z�b�g����
     */
    public void setStartTime() {
        this.startTime = new Date();
    }

    /**
     * �I���������Z�b�g����
     * �������Ԃ��v�Z����
     */
    public void setEndTime() {
        this.endTime = new Date();
        this.setDiffTime();
    }

    /**
     * �������Ԃ��v�Z����
     */
    public void setDiffTime() {
        this.diffTime = DurationFormatUtils.formatPeriod(
                this.startTime.getTime(), this.endTime.getTime(), "HH:mm:ss");
    }

    /**
     * �������e���Z�b�g����
     */
    public void setWork(String str) {
        this.work = str;
    }

    /**
     * ���������Z�b�g����
     */
    public void setCount(int i) {
        this.count = i;
    }

    /**
     * ���l���Z�b�g����
     */
    public void setRemark(String str) {
        this.remark = str;
    }

    /**
     * �J�n������Ԃ�
     */
    public String getStartTime() {
        String ret = this.dateFormat.format(this.startTime);
        return ret;
    }

    /**
     * �I��������Ԃ�
     */
    public String getEndTime() {
        String ret = this.dateFormat.format(this.endTime);
        return ret;
    }

    /**
     * �������Ԃ�Ԃ�
     */
    public String getDiffTime() {
        return this.diffTime;
    }

    public String getDiffTime2() {
        return this.diffTime;
    }

    /**
     * ���O����������
     */
    public void writeLog() {
        DB db = new DB();   // ����N���X�ł��B
                            // �ڍׂ͊֘A�G���g���[���Q�Ƃ��Ă�������

        try {
            db.getConnection();
            String sql = this.writeLogSQL();
            db.excuteUpdate(sql);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * ���O���������� SQL
     */
    private String writeLogSQL() {
        String sql = "INSERT INTO logtbl("
            + "start_time,"
            + "end_time,"
            + "diff_time,"
            + "work,"
            + "count,"
            + "remark"
        + ")"
        + "VALUES("
            + "'" + this.dateFormat.format(this.startTime) + "',"
            + "'" + this.dateFormat.format(this.endTime) + "',"
            + "'" + this.diffTime + "',"
            + "'" + this.work + "',"
            + "'" + this.count + "',"
            + "'" + remark + "'"
        + ")";

        return sql;
    }
}