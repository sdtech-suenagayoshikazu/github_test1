public class ImportCampany {

    public static void main(String[] args) {

        // ���O
        Log log = new Log();    // ���O���������ރN���X�i��q�j
        log.setStartTime();
        System.out.println("�J�n�����F " + log.getStartTime());
        System.out.println("------------------------------------------------------------");

        SBI_Client sbi = new SBI_Client();  // ����܂ŏ����Ă����R�[�h�́C
                                            // ���͂����������O�̃N���X�ł���

        try {
            sbi.login();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("���O�C���Ɏ��s���܂����B");
            return;
        }

        String minCd = "1001";
        String maxCd = "9999";
        int count = sbi.importCampany(minCd, maxCd);

        if (count == 0) {
            System.out.println("��ƊT�v�f�[�^�̎捞�Ɏ��s���܂����B");
            return;
        }

        System.out.println("------------------------------------------------------------");
        System.out.println(count + " ���̊�ƊT�v�f�[�^���i�[���܂����B");
        log.setEndTime();
        System.out.println("�I�������F " + log.getEndTime());
        System.out.println("�������ԁF " + log.getDiffTime());

        // ���O����������
        log.setWork("��ƊT�v");
        log.setCount(count);
        log.setRemark("�ŏ��R�[�h�F " + minCd + ", �ő�R�[�h�F " + maxCd + ", HttpClient - Java");
        log.writeLog();
    }
}