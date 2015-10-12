public class ImportCampany {

    public static void main(String[] args) {

        // ログ
        Log log = new Log();    // ログを書き込むクラス（後述）
        log.setStartTime();
        System.out.println("開始時刻： " + log.getStartTime());
        System.out.println("------------------------------------------------------------");

        SBI_Client sbi = new SBI_Client();  // これまで書いてきたコードは，
                                            // 実はこういう名前のクラスでした

        try {
            sbi.login();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ログインに失敗しました。");
            return;
        }

        String minCd = "1001";
        String maxCd = "9999";
        int count = sbi.importCampany(minCd, maxCd);

        if (count == 0) {
            System.out.println("企業概要データの取込に失敗しました。");
            return;
        }

        System.out.println("------------------------------------------------------------");
        System.out.println(count + " 件の企業概要データを格納しました。");
        log.setEndTime();
        System.out.println("終了時刻： " + log.getEndTime());
        System.out.println("処理時間： " + log.getDiffTime());

        // ログを書き込む
        log.setWork("企業概要");
        log.setCount(count);
        log.setRemark("最小コード： " + minCd + ", 最大コード： " + maxCd + ", HttpClient - Java");
        log.writeLog();
    }
}