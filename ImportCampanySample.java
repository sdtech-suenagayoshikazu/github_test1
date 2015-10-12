import java.io.*;
import java.util.regex.*;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

public class ImportCampanySample {

    /**
     * ログイン時のユーザID
     */
    private final String USER_ID = "inouetakuya";

    /**
     * ログイン時のパスワード
     */
    private final String PASSWORD = "hogehoge";

    /**
     * SBI証券サイトの文字コード
     */
    private final String SBI_CHARACTER_CD = "SJIS";

    /**
     * HttpClient
     * このクラス内で使いまわすことで，
     * ログインによって取得したクッキーを維持する
     */
    private HttpClient client;

    /**
     * ログイン
     *
     * @throws IOException
     * @throws HttpException
     */
    public void login() throws HttpException, IOException {
        // 以前書いたので，省略
    }

    /**
     * 会社四季報の「企業概要」を imp_campany に取り込む
     *
     * @param minCd 取り込む企業コードの最小値
     * @param maxCd 取り込む企業コードの最大値
     * @return imp_campany に取り込んだ企業数.例外発生時には，0
     */
    public int importCampany(String minCd, String maxCd) {
        int minNum = Integer.parseInt(minCd);
        int maxNum = Integer.parseInt(maxCd);
        int campanyCnt = 0;
        boolean retrying = false;   // 意図したデータが取得できずに，
                                    // リトライ中のとき true

        try {
            DB db = new DB();   // これは自作クラスです
                                // 詳細は以前のエントリーを参考にしてください

            db.getConnection();
            db.excuteUpdate("TRUNCATE TABLE imp_campany");

            for (int i = minNum; i <= maxNum; ) {
                String cd = Integer.toString(i);
                CampanyContents contents = new CampanyContents(cd);

                try {
                    this.setCampanyContents(contents);
                    this.perseCampanyContents(contents);


                    // 意図したデータが取得できなかったらときは，
　　　　　　　　　　// ログインし直してもう一度トライ
                    if (this.varidateCampanyContents(contents) == false) {

                        if (retrying == false) {
                            this.login();
                            retrying = true;
                            continue;

                         // リトライしてもダメだったときは諦めて処理を中断する
                        } else {
                            System.out.println("企業コード: " + i + " について企業概要データを取得できませんでした。");
                            return 0;
                        }
                    }

                    String sql = this.importCampanySQL(contents);
                    db.excuteUpdate(sql);

                    campanyCnt++;

                    System.out.println("---次の企業内容を取込みました。---");
                    this.showField(contents);

                    i++;
                    retrying = false;

                // NoCampanyException のときのみ，Forループを止めない
                } catch (NoCampanyException e) {
                    e.printStackTrace();
                    i++;
                    retrying = false;

                // リトライするときは i をインクリメントしないので，
                // finally を使用しない
                }
            }

            db.close();
            return campanyCnt;

        //NoCampanyException 以外の例外は，Forループを止める
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 会社四季報の「企業概要」ページの内容をセットする
     *
     * @throws IOException
     * @throws HttpException
     */
    private void setCampanyContents(CampanyContents contents) throws HttpException, IOException {
        // 以前書いたので，省略
    }

    /**
     * 会社四季報の企業概要ページの内容から企業データを取得する
     *
     * @throws SBI_Exception
     * @throws IOException
     * @throws NumberFormatException
     */
    private void perseCampanyContents(CampanyContents contents)
            throws SBI_Exception, NoCampanyException, NumberFormatException, IOException {
        // 以前書いたので，省略
        // ただし，今回から，取得した値を別クラスのフィールドにセットする用にしている（後述）
    }
    /**
     * 取得した企業内容を確認する
     *
     * @return 企業内容のうち，必須項目にひとつでも null があれば false
     */
    private boolean varidateCampanyContents(CampanyContents contents) {

        // 必須項目
        if(contents.getCampanyCd()  == null) return false;
        if(contents.getCampany()    == null) return false;
        if(contents.getMemoDay()    == null) return false;

        String errorMessage = null;

        // 以下，任意項目
        if(contents.getMarket3() == null) {
            errorMessage += "市場コードを取得できませんでした。";
        }

        if(contents.getMarket() == null) {
            errorMessage += "市場を取得できませんでした。";
        }

        if(contents.getCampanyUrl() == null) {
            errorMessage += "URLを取得できませんでした。";
        }

        if(contents.getGyosyu() == null) {
            errorMessage += "業種を取得できませんでした。";
        }

        if(contents.getKessanMonth() == 0) {
            errorMessage += "決算月を取得できませんでした。";
        }

        if(contents.getTokusyoku() == null) {
            errorMessage += "特色を取得できませんでした。";
        }

        if(contents.getJigyo() == null) {
            errorMessage += "事業を取得できませんでした。";
        }

        if(contents.getMemo() == null) {
            errorMessage += "メモを取得できませんでした。";
        }

        if(contents.getMemo2() == null) {
            errorMessage += "メモ2を取得できませんでした。";
        }

        if(contents.getSource() == null) {
            errorMessage += "情報提供元を取得できませんでした。";
        }

        // エラーメッセージを備考に書き込む
        if(errorMessage != null) {
            contents.setRemark(errorMessage);
        }

        return true;
    }

    /**
     * 取得した企業内容を確認する
     */
    private void showField(CampanyContents contents) {
        System.out.println("campanyCd:   " + contents.getCampanyCd());
        System.out.println("market3:     " + contents.getMarket3());
        System.out.println("campany:     " + contents.getCampany());
        System.out.println("market:      " + contents.getMarket());
        System.out.println("memoDay:     " + contents.getMemoDay());
        System.out.println("campanyUrl:  " + contents.getCampanyUrl());
        System.out.println("gyosyu:      " + contents.getGyosyu());
        System.out.println("kessanMonth: " + contents.getKessanMonth());
        System.out.println("tokusyoku:   " + contents.getTokusyoku());
        System.out.println("jigyo:       " + contents.getJigyo());
        System.out.println("memo:        " + contents.getMemo());
        System.out.println("memo2:       " + contents.getMemo2());
        System.out.println("source:      " + contents.getSource());
        System.out.println("remark:      " + contents.getRemark());
    }

    /**
     * 会社四季報の「企業概要」を imp_campany に取り込むSQLを返す
     */
    private String importCampanySQL(CampanyContents contents) {
        String sql = "INSERT INTO imp_campany("
            + "campany_cd,"
            + "campany,"
            + "market,"
            + "gyosyu,"
            + "campany_url,"
            + "kessan_month,"
            + "tokusyoku,"
            + "jigyo,"
            + "memo_day,"
            + "memo,"
            + "memo2,"
            + "source,"
            + "remark"
        + ")"
        + " VALUES("
            + "'" + contents.getCampanyCd()     + "',"
            + "'" + contents.getCampany()       + "',"
            + "'" + contents.getMarket()        + "',"
            + "'" + contents.getGyosyu()        + "',"
            + "'" + contents.getCampanyUrl()    + "',"
            + contents.getKessanMonth()         + ","
            + "'" + contents.getTokusyoku()     + "',"
            + "'" + contents.getJigyo()         + "',"
            + "'" + contents.getMemoDay()       + "',"
            + "'" + contents.getMemo()          + "',"
            + "'" + contents.getMemo2()         + "',"
            + "'" + contents.getSource()        + "',"
            + "'" + contents.getRemark()        + "'"
        + ")";

        return sql;
    }
}

