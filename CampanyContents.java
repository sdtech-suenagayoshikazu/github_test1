import java.io.BufferedReader;

public class CampanyContents {

    /**
     * ��ƃR�[�h
     */
    private String campanyCd;

    /**
     * �s��R�[�h JPN,TKY,OSK...
     */
    private String market3 = "JPN";

    /**
     * ��ƊT�v�y�[�W�̓��e
     */
    private BufferedReader campanyContents;

    /**
     * ��Ɩ�
     */
    private String campany;

    /**
     * �D��s��
     */
    private String market;

    /**
     * ��Ўl�G��̋L���쐬��
     */
    private String memoDay;

    /**
     * ��ƃT�C�g��URL
     */
    private String campanyUrl;

    /**
     * ��Ўl�G��Ɍf�ڂ���Ă���Ǝ�
     */
    private String gyosyu;

    /**
     * ���Z�� �����̂�
     */
    private int kessanMonth;

    /**
     * ���F �y���F�z�͏���
     */
    private String tokusyoku;

    /**
     * �A�����Ƃ܂��͒P�Ǝ��Ƃ̔���\��
     * �y�A�����Ɓz�܂��́y�P�Ǝ��Ɓz�͏���
     */
    private String jigyo;

    /**
     * ��Ўl�G��̃R�����g
     */
    private String memo;

    /**
     * ��Ўl�G��̃R�����g2
     */
    private String memo2;

    /**
     * ���񋟌�
     */
    private String source = "��Ўl�G��";

    /**
     * ���l
     */
    private String remark = "���ɂȂ�";

    /**
     * �R���X�g���N�^
     */
    public CampanyContents(String cd) {
        this.campanyCd = cd;
    }

    /**
     * �s��R�[�h���Z�b�g���� JPN,TKY,OSK...
     */
    public void setMarket3(String str) {
        this.market3 = str;
    }

    /**
     * ��ƊT�v�y�[�W�̓��e���Z�b�g����
     */
    public void setCampanyContents(BufferedReader reader) {
        this.campanyContents = reader;
    }

    /**
     * ��Ɩ����Z�b�g����
     */
    public void setCampany(String str) {
        this.campany = str;
    }

    /**
     * �D��s����Z�b�g����
     */
    public void setMarket(String str) {
        this.market = str;
    }

    /**
     * ��Ўl�G��̋L���쐬�����Z�b�g����
     */
    public void setMemoDay(String str) {
        this.memoDay = str;
    }

    /**
     * ��ƃT�C�g��URL���Z�b�g����
     */
    public void setCampanyUrl(String str) {
        this.campanyUrl = str;
    }

    /**
     * ��Ўl�G��Ɍf�ڂ���Ă���Ǝ���Z�b�g����
     */
    public void setGyosyu(String str) {
        this.gyosyu = str;
    }

    /**
     * ���Z�����Z�b�g���� �����̂�
     */
    public void setKessanMonth(int i) {
        this.kessanMonth = i;
    }

    /**
     * ���F���Z�b�g���� �y���F�z�͏���
     */
    public void setTokusyoku(String str) {
        this.tokusyoku = str;
    }

    /**
     * �A�����Ƃ܂��͒P�Ǝ��Ƃ̔���\�����Z�b�g����
     * �y�A�����Ɓz�܂��́y�P�Ǝ��Ɓz�͏���
     */
    public void setJigyo(String str) {
        this.jigyo = str;
    }

    /**
     * ��Ўl�G��̃R�����g���Z�b�g����
     */
    public void setMemo(String str) {
        this.memo = str;
    }

    /**
     * ��Ўl�G��̃R�����g2���Z�b�g����
     */
    public void setMemo2(String str) {
        this.memo2 = str;
    }

    /**
     * ���񋟌����Z�b�g����
     */
    public void setSource(String str) {
        this.source = str;
    }

    /**
     * ���l���Z�b�g����
     */
    public void setRemark(String str) {
        this.remark = str;
    }

    /**
     * ��ƃR�[�h��Ԃ�
     */
    public String getCampanyCd() {
        return this.campanyCd;
    }

    /**
     * �s��R�[�h��Ԃ� JPN,TKY,OSK...
     */
    public String getMarket3() {
        return this.market3;
    }

    /**
     * ��ƊT�v�y�[�W�̓��e��Ԃ�
     */
    public BufferedReader getCampanyContents() {
        return this.campanyContents;
    }

    /**
     * ��Ɩ���Ԃ�
     */
    public String getCampany() {
        return this.campany;
    }

    /**
     * �D��s���Ԃ�
     */
    public String getMarket() {
        return this.market;
    }

    /**
     * ��Ўl�G��̋L���쐬����Ԃ�
     */
    public String getMemoDay() {
        return this.memoDay;
    }

    /**
     * ��ƃT�C�g��URL��Ԃ�
     */
    public String getCampanyUrl() {
        return this.campanyUrl;
    }

    /**
     * ��Ўl�G��Ɍf�ڂ���Ă���Ǝ��Ԃ�
     */
    public String getGyosyu() {
        return this.gyosyu;
    }

    /**
     * ���Z����Ԃ� �����̂�
     */
    public int getKessanMonth() {
        return this.kessanMonth;
    }

    /**
     * ���F��Ԃ� �y���F�z�͏���
     */
    public String getTokusyoku() {
        return this.tokusyoku;
    }

    /**
     * �A�����Ƃ܂��͒P�Ǝ��Ƃ̔���\����Ԃ�
     * �y�A�����Ɓz�܂��́y�P�Ǝ��Ɓz�͏���
     */
    public String getJigyo() {
        return this.jigyo;
    }

    /**
     * ��Ўl�G��̃R�����g��Ԃ�
     */
    public String getMemo() {
        return this.memo;
    }

    /**
     * ��Ўl�G��̃R�����g2��Ԃ�
     */
    public String getMemo2() {
        return this.memo2;
    }

    /**
     * ���񋟌���Ԃ�
     */
    public String getSource() {
        return this.source;
    }

    /**
     * ���l��Ԃ�
     */
    public String getRemark() {
        return this.remark;
    }
}