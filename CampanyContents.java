import java.io.BufferedReader;

public class CampanyContents {

    /**
     * 企業コード
     */
    private String campanyCd;

    /**
     * 市場コード JPN,TKY,OSK...
     */
    private String market3 = "JPN";

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
     * 【連結事業】または【単独事業】は除く
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
     * 情報提供元
     */
    private String source = "会社四季報";

    /**
     * 備考
     */
    private String remark = "特になし";

    /**
     * コンストラクタ
     */
    public CampanyContents(String cd) {
        this.campanyCd = cd;
    }

    /**
     * 市場コードをセットする JPN,TKY,OSK...
     */
    public void setMarket3(String str) {
        this.market3 = str;
    }

    /**
     * 企業概要ページの内容をセットする
     */
    public void setCampanyContents(BufferedReader reader) {
        this.campanyContents = reader;
    }

    /**
     * 企業名をセットする
     */
    public void setCampany(String str) {
        this.campany = str;
    }

    /**
     * 優先市場をセットする
     */
    public void setMarket(String str) {
        this.market = str;
    }

    /**
     * 会社四季報の記事作成日をセットする
     */
    public void setMemoDay(String str) {
        this.memoDay = str;
    }

    /**
     * 企業サイトのURLをセットする
     */
    public void setCampanyUrl(String str) {
        this.campanyUrl = str;
    }

    /**
     * 会社四季報に掲載されている業種をセットする
     */
    public void setGyosyu(String str) {
        this.gyosyu = str;
    }

    /**
     * 決算月をセットする 数字のみ
     */
    public void setKessanMonth(int i) {
        this.kessanMonth = i;
    }

    /**
     * 特色をセットする 【特色】は除く
     */
    public void setTokusyoku(String str) {
        this.tokusyoku = str;
    }

    /**
     * 連結事業または単独事業の売上構成をセットする
     * 【連結事業】または【単独事業】は除く
     */
    public void setJigyo(String str) {
        this.jigyo = str;
    }

    /**
     * 会社四季報のコメントをセットする
     */
    public void setMemo(String str) {
        this.memo = str;
    }

    /**
     * 会社四季報のコメント2をセットする
     */
    public void setMemo2(String str) {
        this.memo2 = str;
    }

    /**
     * 情報提供元をセットする
     */
    public void setSource(String str) {
        this.source = str;
    }

    /**
     * 備考をセットする
     */
    public void setRemark(String str) {
        this.remark = str;
    }

    /**
     * 企業コードを返す
     */
    public String getCampanyCd() {
        return this.campanyCd;
    }

    /**
     * 市場コードを返す JPN,TKY,OSK...
     */
    public String getMarket3() {
        return this.market3;
    }

    /**
     * 企業概要ページの内容を返す
     */
    public BufferedReader getCampanyContents() {
        return this.campanyContents;
    }

    /**
     * 企業名を返す
     */
    public String getCampany() {
        return this.campany;
    }

    /**
     * 優先市場を返す
     */
    public String getMarket() {
        return this.market;
    }

    /**
     * 会社四季報の記事作成日を返す
     */
    public String getMemoDay() {
        return this.memoDay;
    }

    /**
     * 企業サイトのURLを返す
     */
    public String getCampanyUrl() {
        return this.campanyUrl;
    }

    /**
     * 会社四季報に掲載されている業種を返す
     */
    public String getGyosyu() {
        return this.gyosyu;
    }

    /**
     * 決算月を返す 数字のみ
     */
    public int getKessanMonth() {
        return this.kessanMonth;
    }

    /**
     * 特色を返す 【特色】は除く
     */
    public String getTokusyoku() {
        return this.tokusyoku;
    }

    /**
     * 連結事業または単独事業の売上構成を返す
     * 【連結事業】または【単独事業】は除く
     */
    public String getJigyo() {
        return this.jigyo;
    }

    /**
     * 会社四季報のコメントを返す
     */
    public String getMemo() {
        return this.memo;
    }

    /**
     * 会社四季報のコメント2を返す
     */
    public String getMemo2() {
        return this.memo2;
    }

    /**
     * 情報提供元を返す
     */
    public String getSource() {
        return this.source;
    }

    /**
     * 備考を返す
     */
    public String getRemark() {
        return this.remark;
    }
}