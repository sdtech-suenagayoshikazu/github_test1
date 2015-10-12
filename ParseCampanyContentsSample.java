import java.io.*;
import java.util.regex.*;

public class ParseCampanyContentsSample {

    /**
     * 企業コード
     */
    private String campanyCd;

    /**
     * 企業概要ページの内容
     */
    private BufferedReader campanyContents;

    /**
     * 企業名
     */
    private String campany;

    /**
     * 優先市場
     */
    private String market;

    /**
     * 会社四季報の記事作成日
     */
    private String memoDay;

    /**
     * 企業サイトのURL
     */
    private String campanyUrl;

    /**
     * 会社四季報に掲載されている業種
     */
    private String gyosyu;

    /**
     * 決算月 数字のみ
     */
    private int kessanMonth;

    /**
     * 特色 【特色】は除く
     */
    private String tokusyoku;

    /**
     * 連結事業または単独事業の売上構成
     * 【連結事業】または【単独事業】という文言は除く
     */
    private String jigyo;

    /**
     * 会社四季報のコメント
     */
    private String memo;

    /**
     * 会社四季報のコメント2
     */
    private String memo2;

    /**
     * 会社四季報の企業概要ページの内容から企業データを取得する
     * 
     * @throws SBI_Exception
     */
    public void perseCampanyContents() {

        Pattern pattern;
        Matcher matcher;

        int i = 0;
        boolean gotJigyo = false;
        boolean gotMemo = false;
        boolean gotMemo2 = false;

        int campanyCdRow = 0;
        int campanyRow = 0;
        int gyosyuRow = 0;
        int jigyoRow = 0;
        int memoRow = 0;

        String line;
        try {
            while ((line = this.campanyContents.readLine()) != null) {
                i++;

                // 当社優先市場
                // なぜか次の正規表現では引っかからなかった
                // pattern =
                // Pattern.compile("<div class=\"margin-1\" style=\"margin-top:2px\">&nbsp;(.+)&nbsp;<font class=\"stext-gray\">（当社優先市場）</font>");

                // 不本意ながら，次の正規表現で拾う
                pattern = Pattern
                        .compile(".*<div class=\"margin-1\" style=\"margin-top:2px\">&nbsp;(.+)&nbsp;<font class=\"stext-gray\">（当社優先市場）</font>.*");
                matcher = pattern.matcher(line);
                if (matcher.matches() == true) {
                    this.market = matcher.group(1);
                }

                // 作成日
                pattern = Pattern
                        .compile("作成日：([0-9]{4})年([0-9]{1,2})月([0-9]{1,2})日");
                matcher = pattern.matcher(line);
                if (matcher.matches() == true) {
                    this.memoDay = matcher.group(1) + "/" + matcher.group(2)
                            + "/" + matcher.group(3);
                    continue;
                }

                // 企業コードから対象行を取得
                // なぜか次のif文だとうまくいかない
　　　　　　　　// →　原因が分かり次第，修正します
                // if(line == this.campanyCd) {

                // 不本意ながら，無駄に正規表現を使う
                pattern = Pattern.compile("^" + this.campanyCd);
                matcher = pattern.matcher(line);
                if (matcher.matches() == true) {
                    campanyCdRow = i;
                    campanyRow = campanyCdRow + 2;
                    gyosyuRow = campanyRow + 4;
                    continue;
                }

                // 企業名
                if (i == campanyRow) {
                    this.campany = line.replace("(株)", "");
                    continue;
                }

                // 業種
                if (i == gyosyuRow) {
                    this.gyosyu = line;
                    continue;
                }

                // URL
                pattern = Pattern.compile("【ＵＲＬ】<a href=\"(.+)");
                matcher = pattern.matcher(line);
                if (matcher.matches() == true) {
                    this.campanyUrl = matcher.group(1);
                    continue;
                }

                // 決算月
                // 3月 のパターンと，3.14 のパターンがある
                pattern = Pattern.compile("【決算】([0-9]{1,2})[月.][0-9]{0,2}");
                matcher = pattern.matcher(line);
                if (matcher.matches() == true) {
                    this.kessanMonth = Integer.parseInt(matcher.group(1));
                    continue;
                }

                // 特色
                pattern = Pattern.compile("【特色】(.+)");
                matcher = pattern.matcher(line);
                if (matcher.matches() == true) {
                    this.tokusyoku = matcher.group(1);
                    continue;
                }

                // 事業
                pattern = Pattern.compile("(?:【連結事業】|【単独事業】)(.+)");
                matcher = pattern.matcher(line);
                if (matcher.matches() == true) {
                    this.jigyo = matcher.group(1);
                    jigyoRow = i;
                    gotJigyo = true;
                    memoRow = jigyoRow + 9;
                    continue;
                }

                // メモ
                // なぜか次の正規表現では引っかからなかった
                // →　原因が分かり次第，修正します
                // pattern = Pattern.compile("^【");

                // 不本意ながら次の正規表現で
                pattern = Pattern.compile("【(.+)");
                matcher = pattern.matcher(line);
                if (i >= memoRow && matcher.matches() == true
                        && gotJigyo == true && gotMemo == false) {
                    this.memo = matcher.group(0);
                    memoRow = i;
                    gotMemo = true;
                    continue;
                }

                // メモ2
                pattern = Pattern.compile("【(.+)");
                matcher = pattern.matcher(line);
                if (i >= memoRow && matcher.matches() == true
                        && gotMemo == true && gotMemo2 == false) {
                    this.memo2 = matcher.group(0);
                    gotMemo2 = true;
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}