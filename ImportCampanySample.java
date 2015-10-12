import java.io.*;
import java.util.regex.*;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

public class ImportCampanySample {

    /**
     * ���O�C�����̃��[�UID
     */
    private final String USER_ID = "inouetakuya";

    /**
     * ���O�C�����̃p�X���[�h
     */
    private final String PASSWORD = "hogehoge";

    /**
     * SBI�،��T�C�g�̕����R�[�h
     */
    private final String SBI_CHARACTER_CD = "SJIS";

    /**
     * HttpClient
     * ���̃N���X���Ŏg���܂킷���ƂŁC
     * ���O�C���ɂ���Ď擾�����N�b�L�[���ێ�����
     */
    private HttpClient client;

    /**
     * ���O�C��
     *
     * @throws IOException
     * @throws HttpException
     */
    public void login() throws HttpException, IOException {
        // �ȑO�������̂ŁC�ȗ�
    }

    /**
     * ��Ўl�G��́u��ƊT�v�v�� imp_campany �Ɏ�荞��
     *
     * @param minCd ��荞�ފ�ƃR�[�h�̍ŏ��l
     * @param maxCd ��荞�ފ�ƃR�[�h�̍ő�l
     * @return imp_campany �Ɏ�荞�񂾊�Ɛ�.��O�������ɂ́C0
     */
    public int importCampany(String minCd, String maxCd) {
        int minNum = Integer.parseInt(minCd);
        int maxNum = Integer.parseInt(maxCd);
        int campanyCnt = 0;
        boolean retrying = false;   // �Ӑ}�����f�[�^���擾�ł����ɁC
                                    // ���g���C���̂Ƃ� true

        try {
            DB db = new DB();   // ����͎���N���X�ł�
                                // �ڍׂ͈ȑO�̃G���g���[���Q�l�ɂ��Ă�������

            db.getConnection();
            db.excuteUpdate("TRUNCATE TABLE imp_campany");

            for (int i = minNum; i <= maxNum; ) {
                String cd = Integer.toString(i);
                CampanyContents contents = new CampanyContents(cd);

                try {
                    this.setCampanyContents(contents);
                    this.perseCampanyContents(contents);


                    // �Ӑ}�����f�[�^���擾�ł��Ȃ�������Ƃ��́C
�@�@�@�@�@�@�@�@�@�@// ���O�C���������Ă�����x�g���C
                    if (this.varidateCampanyContents(contents) == false) {

                        if (retrying == false) {
                            this.login();
                            retrying = true;
                            continue;

                         // ���g���C���Ă��_���������Ƃ��͒��߂ď����𒆒f����
                        } else {
                            System.out.println("��ƃR�[�h: " + i + " �ɂ��Ċ�ƊT�v�f�[�^���擾�ł��܂���ł����B");
                            return 0;
                        }
                    }

                    String sql = this.importCampanySQL(contents);
                    db.excuteUpdate(sql);

                    campanyCnt++;

                    System.out.println("---���̊�Ɠ��e���捞�݂܂����B---");
                    this.showField(contents);

                    i++;
                    retrying = false;

                // NoCampanyException �̂Ƃ��̂݁CFor���[�v���~�߂Ȃ�
                } catch (NoCampanyException e) {
                    e.printStackTrace();
                    i++;
                    retrying = false;

                // ���g���C����Ƃ��� i ���C���N�������g���Ȃ��̂ŁC
                // finally ���g�p���Ȃ�
                }
            }

            db.close();
            return campanyCnt;

        //NoCampanyException �ȊO�̗�O�́CFor���[�v���~�߂�
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * ��Ўl�G��́u��ƊT�v�v�y�[�W�̓��e���Z�b�g����
     *
     * @throws IOException
     * @throws HttpException
     */
    private void setCampanyContents(CampanyContents contents) throws HttpException, IOException {
        // �ȑO�������̂ŁC�ȗ�
    }

    /**
     * ��Ўl�G��̊�ƊT�v�y�[�W�̓��e�����ƃf�[�^���擾����
     *
     * @throws SBI_Exception
     * @throws IOException
     * @throws NumberFormatException
     */
    private void perseCampanyContents(CampanyContents contents)
            throws SBI_Exception, NoCampanyException, NumberFormatException, IOException {
        // �ȑO�������̂ŁC�ȗ�
        // �������C���񂩂�C�擾�����l��ʃN���X�̃t�B�[���h�ɃZ�b�g����p�ɂ��Ă���i��q�j
    }
    /**
     * �擾������Ɠ��e���m�F����
     *
     * @return ��Ɠ��e�̂����C�K�{���ڂɂЂƂł� null ������� false
     */
    private boolean varidateCampanyContents(CampanyContents contents) {

        // �K�{����
        if(contents.getCampanyCd()  == null) return false;
        if(contents.getCampany()    == null) return false;
        if(contents.getMemoDay()    == null) return false;

        String errorMessage = null;

        // �ȉ��C�C�Ӎ���
        if(contents.getMarket3() == null) {
            errorMessage += "�s��R�[�h���擾�ł��܂���ł����B";
        }

        if(contents.getMarket() == null) {
            errorMessage += "�s����擾�ł��܂���ł����B";
        }

        if(contents.getCampanyUrl() == null) {
            errorMessage += "URL���擾�ł��܂���ł����B";
        }

        if(contents.getGyosyu() == null) {
            errorMessage += "�Ǝ���擾�ł��܂���ł����B";
        }

        if(contents.getKessanMonth() == 0) {
            errorMessage += "���Z�����擾�ł��܂���ł����B";
        }

        if(contents.getTokusyoku() == null) {
            errorMessage += "���F���擾�ł��܂���ł����B";
        }

        if(contents.getJigyo() == null) {
            errorMessage += "���Ƃ��擾�ł��܂���ł����B";
        }

        if(contents.getMemo() == null) {
            errorMessage += "�������擾�ł��܂���ł����B";
        }

        if(contents.getMemo2() == null) {
            errorMessage += "����2���擾�ł��܂���ł����B";
        }

        if(contents.getSource() == null) {
            errorMessage += "���񋟌����擾�ł��܂���ł����B";
        }

        // �G���[���b�Z�[�W����l�ɏ�������
        if(errorMessage != null) {
            contents.setRemark(errorMessage);
        }

        return true;
    }

    /**
     * �擾������Ɠ��e���m�F����
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
     * ��Ўl�G��́u��ƊT�v�v�� imp_campany �Ɏ�荞��SQL��Ԃ�
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

