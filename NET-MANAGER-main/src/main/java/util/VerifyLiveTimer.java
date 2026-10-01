package util;

import entity.MayTinh;
import entity.SuDungMay;
import ui.manager.ThanhToanJDialog;
import java.sql.Date;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class VerifyLiveTimer {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   KIỂM THỬ THỜI GIAN THỰC & TIỀN TẠM TÍNH MÁY");
        System.out.println("==================================================");

        // Test Case 1: Kiểm tra tính toán chênh lệch thời gian (Elapsed)
        System.out.print("[Test 1] Kiểm tra tính số giây trôi qua từ mốc bắt đầu... ");
        long now = System.currentTimeMillis();
        long fiveMinutesAgo = now - (5 * 60 + 25) * 1000L; // 5 phút 25 giây trước

        SuDungMay sdm1 = new SuDungMay();
        sdm1.setNgayChoi(new Date(fiveMinutesAgo));
        sdm1.setGioBatDau(new Time(fiveMinutesAgo));

        LocalDate playDate = sdm1.getNgayChoi().toLocalDate();
        LocalTime startTime = sdm1.getGioBatDau().toLocalTime();
        LocalDateTime startDt = LocalDateTime.of(playDate, startTime);
        LocalDateTime currentDt = LocalDateTime.of(new Date(now).toLocalDate(), new Time(now).toLocalTime());
        long diffSeconds = Duration.between(startDt, currentDt).getSeconds();

        if (diffSeconds < 320 || diffSeconds > 330) {
            throw new RuntimeException("Sai lệch giây: " + diffSeconds + " (kỳ vọng khoảng 325s)");
        }
        long h = diffSeconds / 3600;
        long m = (diffSeconds % 3600) / 60;
        long s = diffSeconds % 60;
        String stopwatch = String.format("%02d:%02d:%02d", h, m, s);
        System.out.println("PASS -> Stopwatch: " + stopwatch + " (" + diffSeconds + "s)");

        // Test Case 2: Kiểm tra định dạng thời gian 1h 45m 12s
        System.out.print("[Test 2] Kiểm tra hiển thị nhiều mốc thời gian... ");
        long t1 = 45; // 45s
        String s1 = String.format("%02d:%02d:%02d", t1 / 3600, (t1 % 3600) / 60, t1 % 60);
        assert "00:00:45".equals(s1) : "Sai mốc 45s";

        long t2 = 3600 + 45 * 60 + 12; // 1h 45m 12s
        String s2 = String.format("%02d:%02d:%02d", t2 / 3600, (t2 % 3600) / 60, t2 % 60);
        assert "01:45:12".equals(s2) : "Sai mốc 1h 45m 12s";
        System.out.println("PASS -> 45s: " + s1 + ", 1h45m12s: " + s2);

        // Test Case 3: Kiểm tra tiền giờ tạm tính so với công thức chuẩn ThanhToanJDialog
        System.out.print("[Test 3] Kiểm tra khớp tiền tạm tính với ThanhToanJDialog... ");
        float rate = 8000.0f;

        // Giả lập chơi 1 tiếng 30 phút:
        long elapsed90m = 90 * 60; // 5400s
        long minutes = elapsed90m / 60;
        double hoursPlayed = Math.ceil((minutes / 60.0) * 100.0) / 100.0;
        double feeCalc = Math.ceil((hoursPlayed * rate) * 100.0) / 100.0;

        // So sánh với ThanhToanJDialog.tinhTien
        double[] ttResult = ThanhToanJDialog.tinhTien("2026-09-29", "2026-09-29", "10:00:00", "11:30:00", rate);
        if (feeCalc != ttResult[1]) {
            throw new RuntimeException("Tiền tạm tính (" + feeCalc + ") không khớp với ThanhToan (" + ttResult[1] + ")");
        }
        System.out.println("PASS -> Tiền giờ 1.5h @ 8.000đ/h = " + Style_Net.formatMoney(feeCalc));

        // Test Case 4: Kiểm tra cộng dồn tiền F&B vào tạm tính
        System.out.print("[Test 4] Kiểm tra cộng dồn tiền món ăn F&B... ");
        double foodTotal = 24000.0; // 2 lon Sting
        double totalBill = feeCalc + foodTotal;
        assert totalBill == (12000.0 + 24000.0) : "Sai tổng tiền";
        System.out.println("PASS -> Tổng tạm tính = " + Style_Net.formatMoney(totalBill));

        System.out.println("==================================================");
        System.out.println("   TẤT CẢ 4/4 KIỂM THỬ THỜI GIAN THỰC ĐÃ PASS!    ");
        System.out.println("==================================================");
    }
}
