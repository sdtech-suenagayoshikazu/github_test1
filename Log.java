import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import org.apache.commons.lang.time.DurationFormatUtils;

public class Log {

    /**
     * 処理の開始時刻
     */
    private Date startTime;

    /**
     * 処理の終了時刻
     */
    private Date endTime;

    /**
     * 処理時間
     */
    private String diffTime;

    /**
     * 処理内容
     */
    private String work;

    /**
     * 処理数
     */
    private int count;

    /**
     * 備考
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
     * 開始時刻をセットする
     */
    public void setStartTime() {
        this.startTime = new Date();
    }

    /**
     * 終了時刻をセットする
     * 処理時間を計算する
     */
    public void setEndTime() {
        this.endTime = new Date();
        this.setDiffTime();
    }

    /**
     * 処理時間を計算する
     */
    public void setDiffTime() {
        this.diffTime = DurationFormatUtils.formatPeriod(
                this.startTime.getTime(), this.endTime.getTime(), "HH:mm:ss");
    }

    /**
     * 処理内容をセットする
     */
    public void setWork(String str) {
        this.work = str;
    }

    /**
     * 処理数をセットする
     */
    public void setCount(int i) {
        this.count = i;
    }

    /**
     * 備考をセットする
     */
    public void setRemark(String str) {
        this.remark = str;
    }

    /**
     * 開始時刻を返す
     */
    public String getStartTime() {
        String ret = this.dateFormat.format(this.startTime);
        return ret;
    }

    /**
     * 終了時刻を返す
     */
    public String getEndTime() {
        String ret = this.dateFormat.format(this.endTime);
        return ret;
    }

    /**
     * 処理時間を返す
     */
    public String getDiffTime() {
        return this.diffTime;
    }

    public String getDiffTime2() {
        return this.diffTime;
    }

    /**
     * ログを書き込む
     */
    public void writeLog() {
        DB db = new DB();   // 自作クラスです。
                            // 詳細は関連エントリーを参照してください

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
     * ログを書き込む SQL
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