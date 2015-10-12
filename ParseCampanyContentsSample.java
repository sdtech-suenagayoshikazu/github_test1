import java.io.*;
import java.util.regex.*;

public class ParseCampanyContentsSample {

    /**
     * ��ƃR�[�h
     */
    private String campanyCd;

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
     * �y�A�����Ɓz�܂��́y�P�Ǝ��Ɓz�Ƃ��������͏���
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
     * ��Ўl�G��̊�ƊT�v�y�[�W�̓��e�����ƃf�[�^���擾����
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

                // ���ЗD��s��
                // �Ȃ������̐��K�\���ł͈���������Ȃ�����
                // pattern =
                // Pattern.compile("<div class=\"margin-1\" style=\"margin-top:2px\">&nbsp;(.+)&nbsp;<font class=\"stext-gray\">�i���ЗD��s��j</font>");

                // �s�{�ӂȂ���C���̐��K�\���ŏE��
                pattern = Pattern
                        .compile(".*<div class=\"margin-1\" style=\"margin-top:2px\">&nbsp;(.+)&nbsp;<font class=\"stext-gray\">�i���ЗD��s��j</font>.*");
                matcher = pattern.matcher(line);
                if (matcher.matches() == true) {
                    this.market = matcher.group(1);
                }

                // �쐬��
                pattern = Pattern
                        .compile("�쐬���F([0-9]{4})�N([0-9]{1,2})��([0-9]{1,2})��");
                matcher = pattern.matcher(line);
                if (matcher.matches() == true) {
                    this.memoDay = matcher.group(1) + "/" + matcher.group(2)
                            + "/" + matcher.group(3);
                    continue;
                }

                // ��ƃR�[�h����Ώۍs���擾
                // �Ȃ�������if�����Ƃ��܂������Ȃ�
�@�@�@�@�@�@�@�@// ���@�����������莟��C�C�����܂�
                // if(line == this.campanyCd) {

                // �s�{�ӂȂ���C���ʂɐ��K�\�����g��
                pattern = Pattern.compile("^" + this.campanyCd);
                matcher = pattern.matcher(line);
                if (matcher.matches() == true) {
                    campanyCdRow = i;
                    campanyRow = campanyCdRow + 2;
                    gyosyuRow = campanyRow + 4;
                    continue;
                }

                // ��Ɩ�
                if (i == campanyRow) {
                    this.campany = line.replace("(��)", "");
                    continue;
                }

                // �Ǝ�
                if (i == gyosyuRow) {
                    this.gyosyu = line;
                    continue;
                }

                // URL
                pattern = Pattern.compile("�y�t�q�k�z<a href=\"(.+)");
                matcher = pattern.matcher(line);
                if (matcher.matches() == true) {
                    this.campanyUrl = matcher.group(1);
                    continue;
                }

                // ���Z��
                // 3�� �̃p�^�[���ƁC3.14 �̃p�^�[��������
                pattern = Pattern.compile("�y���Z�z([0-9]{1,2})[��.][0-9]{0,2}");
                matcher = pattern.matcher(line);
                if (matcher.matches() == true) {
                    this.kessanMonth = Integer.parseInt(matcher.group(1));
                    continue;
                }

                // ���F
                pattern = Pattern.compile("�y���F�z(.+)");
                matcher = pattern.matcher(line);
                if (matcher.matches() == true) {
                    this.tokusyoku = matcher.group(1);
                    continue;
                }

                // ����
                pattern = Pattern.compile("(?:�y�A�����Ɓz|�y�P�Ǝ��Ɓz)(.+)");
                matcher = pattern.matcher(line);
                if (matcher.matches() == true) {
                    this.jigyo = matcher.group(1);
                    jigyoRow = i;
                    gotJigyo = true;
                    memoRow = jigyoRow + 9;
                    continue;
                }

                // ����
                // �Ȃ������̐��K�\���ł͈���������Ȃ�����
                // ���@�����������莟��C�C�����܂�
                // pattern = Pattern.compile("^�y");

                // �s�{�ӂȂ��玟�̐��K�\����
                pattern = Pattern.compile("�y(.+)");
                matcher = pattern.matcher(line);
                if (i >= memoRow && matcher.matches() == true
                        && gotJigyo == true && gotMemo == false) {
                    this.memo = matcher.group(0);
                    memoRow = i;
                    gotMemo = true;
                    continue;
                }

                // ����2
                pattern = Pattern.compile("�y(.+)");
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