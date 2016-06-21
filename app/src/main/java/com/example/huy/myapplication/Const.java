package com.example.huy.myapplication;

/**
 * Created by huy on 1/16/16.
 */
public class Const {
    public static String ConverCan(int Can) {
        String Str = "";
        switch (Can) {
            case 0:
                Str = "Canh";
                break;
            case 1:
                Str = "Tân";
                break;
            case 2:
                Str = "Nhâm";
                break;
            case 3:
                Str = "Quý";
                break;
            case 4:
                Str = "Giáp";
                break;
            case 5:
                Str = "Ất";
                break;
            case 6:
                Str = "Bính";
                break;
            case 7:
                Str = "Đinh";
                break;
            case 8:
                Str = "Mậu";
                break;
            case 9:
                Str = "Kỷ";
                break;
        }
        return Str;
    }

    public static String ConvertChi(int Chi) {
        String Str = "";
        switch (Chi) {
            case 0:
                Str = "Thân";
                break;
            case 1:
                Str = "Dậu";
                break;
            case 2:
                Str = "Tuất";
                break;
            case 3:
                Str = "Hợi";
                break;
            case 4:
                Str = "Tý";
                break;
            case 5:
                Str = "Sửu";
                break;
            case 6:
                Str = "Dần";
                break;
            case 7:
                Str = "Mão";
                break;
            case 8:
                Str = "Thìn";
                break;
            case 9:
                Str = "Tỵ";
                break;
            case 10:
                Str = "Ngọ";
                break;
            case 11:
                Str = "Mùi";
                break;
        }
        return Str;
    }

    // Chuyen doi ngay
    // Lay Ng\E0y Julius
    public static long getJulius(int dd, int mm, int yy) {
        int a, y, m, jd;
        a = (14 - mm) / 12;
        y = yy + 4800 - a;
        m = mm + 12 * a - 3;
        jd = dd + (153 * m + 2) / 5 + 365 * y + y / 4 - y / 100 + y / 400 - 32045;
        if (jd < 2299161) {
            jd = dd + (153 * m + 2) / 5 + 365 * y + y / 4 - 32083;
        }
        return jd;
    }

    // Tinh ngay Soc
    public static int getNewMoonDay(int k, double timeZone) {
        double T, T2, T3, dr, Jd1, M, Mpr, F, C1, deltat, JdNew;
        T = k / 1236.85; // Time in Julian centuries from 1900 January 0.5
        T2 = T * T;
        T3 = T2 * T;
        dr = 3.14159 / 180;
        Jd1 = 2415020.75933 + 29.53058868 * k + 0.0001178 * T2 - 0.000000155 * T3;
        Jd1 = Jd1 + 0.00033 * Math.sin((166.56 + 132.87 * T - 0.009173 * T2) * dr); // Mean new moon
        M = 359.2242 + 29.10535608 * k - 0.0000333 * T2 - 0.00000347 * T3; // Sun's mean anomaly
        Mpr = 306.0253 + 385.81691806 * k + 0.0107306 * T2 + 0.00001236 * T3; // Moon's mean anomaly
        F = 21.2964 + 390.67050646 * k - 0.0016528 * T2 - 0.00000239 * T3; // Moon's argument of latitude
        C1 = (0.1734 - 0.000393 * T) * Math.sin(M * dr) + 0.0021 * Math.sin(2 * dr * M);
        C1 = C1 - 0.4068 * Math.sin(Mpr * dr) + 0.0161 * Math.sin(dr * 2 * Mpr);
        C1 = C1 - 0.0004 * Math.sin(dr * 3 * Mpr);
        C1 = C1 + 0.0104 * Math.sin(dr * 2 * F) - 0.0051 * Math.sin(dr * (M + Mpr));
        C1 = C1 - 0.0074 * Math.sin(dr * (M - Mpr)) + 0.0004 * Math.sin(dr * (2 * F + M));
        C1 = C1 - 0.0004 * Math.sin(dr * (2 * F - M)) - 0.0006 * Math.sin(dr * (2 * F + Mpr));
        C1 = C1 + 0.0010 * Math.sin(dr * (2 * F - Mpr)) + 0.0005 * Math.sin(dr * (2 * Mpr + M));
        if (T < -11) {
            deltat = 0.001 + 0.000839 * T + 0.0002261 * T2 - 0.00000845 * T3 - 0.000000081 * T * T3;
        } else {
            deltat = -0.000278 + 0.000265 * T + 0.000262 * T2;
        }
        JdNew = Jd1 + C1 - deltat;
        return (int) (JdNew + 0.5 + timeZone / 24);
    }

    // T\EDnh toa do mat troi
    public static int getSunLongitude(int jdn, double timeZone) {
        double T, T2, dr, M, L0, DL, L, PI = 3.14159265;
        T = (jdn - 2451545.5 - timeZone / 24) / 36525; // Time in Julian centuries from 2000-01-01 12:00:00 GMT
        T2 = T * T;
        dr = PI / 180; // degree to radian
        M = 357.52910 + 35999.05030 * T - 0.0001559 * T2 - 0.00000048 * T * T2; // mean anomaly, degree
        L0 = 280.46645 + 36000.76983 * T + 0.0003032 * T2; // mean longitude, degree
        DL = (1.914600 - 0.004817 * T - 0.000014 * T2) * Math.sin(dr * M);
        DL = DL + (0.019993 - 0.000101 * T) * Math.sin(dr * 2 * M) + 0.000290 * Math.sin(dr * 3 * M);
        L = L0 + DL; // true longitude, degree
        L = L * dr;
        L = L - PI * 2 * (L / (PI * 2)); // Normalize to (0, 2*PI)
        return (int) (L / (PI * 6));
    }

    // T\ECm ng\E0y bat dau th\E1ng 11 am lich
    public static long getLunarMonthll(int yy, double timeZone) {
        int k, off, nm, sunLong;
        off = (int) (getJulius(31, 12, yy) - 2415021);
        k = (int) (off / 29.530588853);
        nm = (int) getNewMoonDay(k, timeZone);
        sunLong = (int) getSunLongitude(nm, timeZone); // sun longitude at local midnight
        if (sunLong >= 9) {
            nm = (int) getNewMoonDay(k - 1, timeZone);
        }
        return nm;
    }

    // X\E1c dinh thang nhuan
    public static int getLeapMonthOffset(double a11, double timeZone) {
        int k, i, arc, last;
        k = (int) ((a11 - 2415021.076998695) / 29.530588853 + 0.5);
        last = 0;
        i = 1; // We start with the month following lunar month 11
        arc = (int) getSunLongitude(getNewMoonDay(k + i, timeZone), timeZone);
        do {
            last = arc;
            i++;
            arc = (int) getSunLongitude(getNewMoonDay(k + i, timeZone), timeZone);
        }
        while ((arc != last) && (i < 14));
        return i - 1;
    }
}
