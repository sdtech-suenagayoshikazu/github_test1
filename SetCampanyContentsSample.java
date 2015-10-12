import java.io.*;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

public class SetCampanyContentsSample {

    /**
     * HttpClient このクラス内で使い回すことで，
     * ログインによって取得したクッキーを維持する
     */
    private HttpClient client;

    /**
     * 企業概要ページの内容
     */
    private BufferedReader campanyContents;

    /**
     * ログイン
     */
    public void login() {
        String url = "https://trading1.sbisec.co.jp/ETGate/";

        // HttpClient のインスタンスを使い回すことで，
        // ログイン後のクッキーを維持する
        this.client = new HttpClient();
        PostMethod method = new PostMethod(url);

        method.setParameter("JS_FLG", "1");
        method.addParameter("_ControlID", "WPLETlgR001Control");
        method.addParameter("_DataStoreID", "DSWPLETlgR001Control");
        method.addParameter("_PageID", "WPLETlgR001Rlgn10");
        method.addParameter("_ActionID", "loginHome");
        method.addParameter("getFlg", "on");

        // ユーザID
        method.addParameter("user_id", "inouetakuya");

        // パスワード
        method.addParameter("user_password", "hogehoge");

        try {
            this.client.executeMethod(method);

        } catch (HttpException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            method.releaseConnection(); // メソッドを解放
        }
    }
    
    /**
     * 会社四季報の「企業概要」ページの内容をセットする
     */
    public void setCampanyContents() {
        String url = "https://trading1.sbisec.co.jp/ETGate/";
        PostMethod method = new PostMethod(url);

        method.setParameter("_ControlID", "WPLETsiR001Control");
        method.addParameter("_PageID", "WPLETsiR001Idtl50");
        method.addParameter("_DataStoreID", "DSWPLETsiR001Control");
        method.addParameter("_ActionID", "DefaultAID");
        method.addParameter("s_rkbn", "2");
        method.addParameter("i_stock_sec", "9984"); // 企業コード
        method.addParameter("i_dom_flg", "1");
        method.addParameter("i_exchange_code", "JPN"); // 通常は当社優先市場. JPN
        method.addParameter("i_output_type", "4");
        method.addParameter("exchange_code", "JPN"); // 通常は当社優先市場. JPN
        method.addParameter("stock_sec_code_mul", "9984"); // 企業コード
        method.addParameter("ref_from", "1");
        method.addParameter("ret_to", "20");
        method.addParameter("getFlg", "on");
        method.addParameter("JS_FLG", "1");

        try {
            // HttpClientのインスタンスは，使い回す
            // （ログイン後のクッキーが保持される）
            this.client.executeMethod(method);

            // ボリュームがあるので，String型よりもBufferedReader型で
            InputStream is = method.getResponseBodyAsStream();

            // 文字コードの変換が必要
            this.campanyContents = new BufferedReader(new InputStreamReader(is, "SJIS"));

        } catch (HttpException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            //ここでメソッドを解放してしまうと，
            //取得した企業概要データまで消えてしまうことが判明
            //method.releaseConnection();
        }
    }
}