import java.io.*;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

public class SetCampanyContentsSample {

    /**
     * HttpClient ���̃N���X���Ŏg���񂷂��ƂŁC
     * ���O�C���ɂ���Ď擾�����N�b�L�[���ێ�����
     */
    private HttpClient client;

    /**
     * ��ƊT�v�y�[�W�̓��e
     */
    private BufferedReader campanyContents;

    /**
     * ���O�C��
     */
    public void login() {
        String url = "https://trading1.sbisec.co.jp/ETGate/";

        // HttpClient �̃C���X�^���X���g���񂷂��ƂŁC
        // ���O�C����̃N�b�L�[���ێ�����
        this.client = new HttpClient();
        PostMethod method = new PostMethod(url);

        method.setParameter("JS_FLG", "1");
        method.addParameter("_ControlID", "WPLETlgR001Control");
        method.addParameter("_DataStoreID", "DSWPLETlgR001Control");
        method.addParameter("_PageID", "WPLETlgR001Rlgn10");
        method.addParameter("_ActionID", "loginHome");
        method.addParameter("getFlg", "on");

        // ���[�UID
        method.addParameter("user_id", "inouetakuya");

        // �p�X���[�h
        method.addParameter("user_password", "hogehoge");

        try {
            this.client.executeMethod(method);

        } catch (HttpException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            method.releaseConnection(); // ���\�b�h�����
        }
    }
    
    /**
     * ��Ўl�G��́u��ƊT�v�v�y�[�W�̓��e���Z�b�g����
     */
    public void setCampanyContents() {
        String url = "https://trading1.sbisec.co.jp/ETGate/";
        PostMethod method = new PostMethod(url);

        method.setParameter("_ControlID", "WPLETsiR001Control");
        method.addParameter("_PageID", "WPLETsiR001Idtl50");
        method.addParameter("_DataStoreID", "DSWPLETsiR001Control");
        method.addParameter("_ActionID", "DefaultAID");
        method.addParameter("s_rkbn", "2");
        method.addParameter("i_stock_sec", "9984"); // ��ƃR�[�h
        method.addParameter("i_dom_flg", "1");
        method.addParameter("i_exchange_code", "JPN"); // �ʏ�͓��ЗD��s��. JPN
        method.addParameter("i_output_type", "4");
        method.addParameter("exchange_code", "JPN"); // �ʏ�͓��ЗD��s��. JPN
        method.addParameter("stock_sec_code_mul", "9984"); // ��ƃR�[�h
        method.addParameter("ref_from", "1");
        method.addParameter("ret_to", "20");
        method.addParameter("getFlg", "on");
        method.addParameter("JS_FLG", "1");

        try {
            // HttpClient�̃C���X�^���X�́C�g����
            // �i���O�C����̃N�b�L�[���ێ������j
            this.client.executeMethod(method);

            // �{�����[��������̂ŁCString�^����BufferedReader�^��
            InputStream is = method.getResponseBodyAsStream();

            // �����R�[�h�̕ϊ����K�v
            this.campanyContents = new BufferedReader(new InputStreamReader(is, "SJIS"));

        } catch (HttpException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            //�����Ń��\�b�h��������Ă��܂��ƁC
            //�擾������ƊT�v�f�[�^�܂ŏ����Ă��܂����Ƃ�����
            //method.releaseConnection();
        }
    }
}