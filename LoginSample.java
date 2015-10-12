package sample;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

public class LoginSample {

    /**
     * ログイン
     */
    public void login() {
        String url = "https://trading1.sbisec.co.jp/ETGate/";

        // HttpClient のインスタンスを使いまわすことで，
        // ログイン後のクッキーを維持する
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);

        method.addParameter("JS_FLG", "1");
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
            client.executeMethod(method);

        } catch (HttpException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            method.releaseConnection();
        }
    }
}