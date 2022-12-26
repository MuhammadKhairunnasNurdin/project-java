import java.util.Random;

import java.util.Scanner;

public class Inventori_anas {

    static Random acak = new Random();
    static Scanner input = new Scanner(System.in);
    static String[] namaStok = {"tomat", "roti", "ayam", "keju", "sapi"};
    static String[] hurufKolom = {"awal", "tambah", "ambil", "total", "layak", "busuk"};
    static String[] hurufKapital = {"awal", "tambah", "ambil", "total", "layak", "busuk", "+busuk", "Vbusuk", "GradeA", "+GradeA", "VgradeA", "GradeB", "+GradeB", "VgradeB", "Money" , "Capital_change", "Volatility (V)"};

    
    static int[][] tambahGudang(int[][] gudang) {
        char pilih = ' ';
        int cek = gudang[0][0], a = 0, banyak = 0;
        for (int i = 0; i < gudang.length; i++) {
            for (int j = 0; j < gudang[0].length; j++) {
                if  (cek == 0) {
                    if (j == 0) {
                        gudang[i][j] = acak.nextInt(50) + 10;
                    }
                    else if (j == 3) {
                        gudang[i][j] = gudang[i][0];
                    }
                    else if (j == 4) {
                        gudang[i][j] = gudang[i][0] - acak.nextInt(gudang[i][0] / 2);
                    }
                    else if (j == 5) {
                        gudang[i][j] = gudang[i][0] - gudang[i][4]; 
                    }     
                }
                else {
                    if ((i == 0) && (j == 0)) {
                        System.out.print("Pilih cara Tambah (manual / otomatis) : ");
                        pilih = input.next().charAt(0);
                        input.nextLine();
                        System.out.println();
                        
                        if (pilih == 'm' || pilih == 'M') {
                            System.out.println("Berikut referensi data stok\n");
                            printNilai(namaStok, hurufKolom, gudang);
                            System.out.println();
                        }
                    }
                    if (pilih == 'm' || pilih == 'M') {
                        if (a == i) {
                            System.out.print(namaStok[a] +  " : ");
                            banyak = input.nextInt();
                            a++;
                        }         
                    }
                    if (pilih == 'o' || pilih == 'O') {
                        if (a == i) {
                            banyak = acak.nextInt(40);
                            System.out.println(namaStok[a] + " bertambah : " + banyak);
                            a++;
                        } 
                    }
                    if (j == 1)  {
                        gudang[i][j] += banyak;
                    }
                    else if (j == 3) {
                        gudang[i][j] += banyak;
                    }
                    else if (j == 4) {
                        if ((banyak == 0) || (banyak == 1)) {
                            if (banyak == 1) {
                                gudang[i][j] += banyak;
                            }
                            gudang[i][j] += 0;
                        }
                        else {
                            gudang[i][j] += (banyak - acak.nextInt(banyak / 2));
                        }
                    }
                    else if (j == 5) {
                        gudang[i][j] = gudang[i][3] - gudang[i][4];
                    } 
                }
            }
        }
        
        return gudang;
    }
    static void printNilai(String[] hurufBaris, String[] hurufKolom, int[][] nilaiArray) {
        for (int i = 0; i < nilaiArray.length; i++) {
            if (i == 0) {
                for (int j = 0; j < nilaiArray[0].length; j++) {
                    if (j == 0) {
                        System.out.print("\t" + hurufKolom[j] + "\t");
                    }
                    else if (j == 6 || j == 8 || j == 11) {
                        System.out.print("|" + hurufKolom[j]);
                    }
                    else if ( j == 7 || j == 9 || j == 12) {
                        System.out.print(" " + hurufKolom[j] + "\t");
                    }
                    else if (j == 14) {
                        System.out.printf("|%9s", hurufKolom[j]);
                    }
                    else if (j == 15) {
                        System.out.printf("   %9s\t", hurufKolom[j]);
                    }
                    else if (j == 16) {
                        System.out.printf("%9s", hurufKolom[j]);
                    }
                    else {
                        System.out.print(hurufKolom[j] + "\t");
                    }
                }
                System.out.println();
            }
            System.out.print(hurufBaris[i] + "\t");
            for (int j = 0; j < nilaiArray[0].length; j++) {
                if (j == 6 || j == 8 || j == 11) {
                    System.out.print("|" + nilaiArray[i][j] + "\t");
                }
                else if (j == 14) {
                    System.out.printf("|%9s", nilaiArray[i][j]);
                }
                else if ( j == 15) {
                    System.out.printf("   %9s\t      ", nilaiArray[i][j]);
                }
                else if (j == 16) {
                    System.out.printf("%9s", nilaiArray[i][j]);
                }
                else {
                    System.out.print(nilaiArray[i][j] + "\t");
                }  
            }
            System.out.println();
        } 
    }
    static int[][] ambilGudang(int[][] gudang) { 
        boolean salahInput = true;
        char pilih;
        do {
            System.out.print("Pilih cara ambil (manual / otomatis) : ");
            pilih = input.next().charAt(0);
            if (pilih == 'm' || pilih == 'M' || pilih == 'o' || pilih == 'O') {
                input.nextLine();
                System.out.println();
                salahInput = false;
            }
            else {
                System.out.println("Input yang benar !");
            }

        }while(salahInput == true);
        

        if (pilih == 'm' || pilih == 'M') {
            System.out.println("Berikut referensi data stok\n");
            printNilai(namaStok, hurufKolom, gudang);
            System.out.println();
        }
        
        for (int i = 0; i < gudang.length; i++) {
            int selisih;
            int ambil = 0;
            for (int j = 0; j < gudang[0].length; j++) {
                if (j == 2) {
                    boolean cek = true;
                    if (pilih == 'm' || pilih == 'M') {
                        do {
                            System.out.printf("ambil %s berapa  : ", namaStok[i]);
                            ambil = input.nextInt();
                            if (ambil > gudang[i][3]) {
                                System.out.println("inputan anda melebihi total stok! => input ulang");
                            }
                            else {
                                char paksa = ' ';
                                if (ambil > gudang[i][4]) {
                                    System.out.printf("Stok layak %s anda tidak mencukupi! , tetap ambil total stok semuanya (busuk juga) ? (y / t) : ", namaStok[i]);
                                    paksa = input.next().charAt(0);
                                    System.out.println();
                                    input.nextLine();
                                    if (paksa == 'y' || paksa == 'Y') {
                                        System.out.println("Itu pilihan anda, maka busuk akan diambil!\n");
                                        gudang[i][j] += ambil;
                                        cek = false;
                                    }
                                    else {
                                        System.out.println("\nSilakan nanti menambah stok pada fitur tambah pada homepage fitur !\n");
                                        System.out.printf("Sementara ulangi input untuk mengambil %s \n\n" , namaStok[i]);
                                    }
                                }
                                else {
                                    gudang[i][j] += ambil;
                                    cek = false;
                                }
                                
                            }

                        }while (cek == true);
                    }
                    else if (pilih == 'o' || pilih == 'O') {
                        if (gudang[i][3] == 0) {
                            gudang[i][j] += 0;
                        }
                        else {
                            boolean otomatis = false;
                            do {
                                ambil = acak.nextInt((gudang[i][3]));
                                if (ambil > gudang[i][4]) {
                                    otomatis = true;
                                }
                                else {
                                    otomatis = false;
                                }
                            }while(otomatis == true);
                            
                            System.out.printf(namaStok[i] + " yang terambil : " + ambil + "\n");
                            gudang[i][j] += ambil;
                        }
                    }
                }
                else if (j == 3) {
                    gudang[i][j] -= ambil;
                }
                else if (j == 4) {
                    if (gudang[i][3] == 0) {
                        gudang[i][j] = 0;
                    }
                    else {
                        selisih = (gudang[i][j]- ambil);
                        if (selisih < 0) {
                            gudang[i][j] = 0;
                        }
                        else if (selisih == 0) {
                            gudang[i][j] -= ambil;
                            ambil = 0;
                        }
                        else {
                            gudang[i][j] -= ambil;
                        }
                    }  
                }
                else if (j == 5) {
                    if ((gudang[i][4] == 0) && (gudang[i][j] != 0) && (ambil != 0)) { 
                        System.out.printf("\nStok layak %s habis , maka dari %s busuk akan terambil sebanyak %s\n\n", namaStok[i], gudang[i][5], (gudang[i][5] - gudang[i][3]));
                        gudang[i][j] = gudang[i][3];
                    }   
                   
                }
            }
        }
        return gudang;
    }
    static void cariGudang(int[][] gudang, int[][] kapital) {
        char cari = ' ';
        kapitalisasi(gudang, kapital);
        boolean cariLagi = true;

        do {
            System.out.println("\nBerikut referensi data cari\n");
            for (int j = 0; j < namaStok.length; j++) {
                System.out.printf("|%s|   ", namaStok[j]);
            }
            System.out.println();

            System.out.print("\nmau mencari data apa (nama data): ");
            String nama = input.next();
            input.nextLine();
            
            nama = nama.toLowerCase();
            cari = (nama).charAt(0);
            System.out.println("\n");
                
            for (int i = 0; i < gudang.length + 1; i++) {
                if (i < gudang.length) {
                    if (cari == (namaStok[i]).charAt(0)) {
                        for (int j = 0; j < gudang[0].length; j++) {
                            if (j == 0) {
                                System.out.print("\t" + hurufKolom[j] + "\t");
                            }
                            else {
                                System.out.print(hurufKolom[j] + "\t");
                            }
                        }
                        System.out.println();
                        System.out.print(namaStok[i] + "\t");
                        for(int j = 0; j < gudang[0].length; j++) {
                            System.out.print(gudang[i][j] + "\t");
                        }
                        System.out.println();
                        System.out.printf("\nCari kapitalisasi spesifik dari %s ? (y / t) : ", namaStok[i]);
                        char cek = input.next().charAt(0);
                        
                        if (cek == 'y' || cek == 'Y') {
                            for (int j = 0; j < kapital[0].length; j++) {
                                if (j == 0) {
                                    System.out.print("\t" + hurufKapital[j] + "\t");
                                }
                                else if (j == 6 || j == 8 || j == 11) {
                                    System.out.print("|" + hurufKapital[j]);
                                }
                                else if ( j == 7 || j == 9 || j == 12) {
                                    System.out.print(" " + hurufKapital[j] + "\t");
                                }
                                else if (j == 14) {
                                    System.out.printf("|%9s", hurufKapital[j]);
                                }
                                else if (j == 15) {
                                    System.out.printf("   %9s\t", hurufKapital[j]);
                                }
                                else if (j == 16) {
                                    System.out.printf("%9s", hurufKapital[j]);
                                }
                                else {
                                    System.out.print(hurufKapital[j] + "\t");
                                }
                            }
                            System.out.println();
                            System.out.print(namaStok[i] + "\t");
                            for(int j = 0; j < kapital[0].length; j++) {
                                if (j == 6 || j == 8 || j == 11) {
                                    System.out.print("|" + kapital[i][j] + "\t");
                                }
                                else if (j == 14) {
                                    System.out.printf("|%9s", kapital[i][j]);
                                }
                                else if ( j == 15) {
                                    System.out.printf("   %9s\t      ", kapital[i][j]);
                                }
                                else if (j == 16) {
                                    System.out.printf("%9s", kapital[i][j]);
                                }
                                else {
                                    System.out.print(kapital[i][j] + "\t");
                                }
                            }
                        }
                        break;
                    }
                }
                else {
                    System.out.println("Data tidak ada !");
                }
            }
            System.out.print("\n\nMau mencari data lain? (y / t) : ");
            char lagi = input.next().charAt(0);
            input.nextLine();
            if ( lagi == 'y' || lagi == 'Y') {
                cariLagi = true;
            }
            else {
                cariLagi = false;
            }

        }while(cariLagi == true);
        
    }
    static int[][] kapitalisasi(int[][] gudang, int[][] kapital) {
        int[] hargaA = {6000, 4000, 14000, 8000, 25000};
        int[] hargaB = {4000, 2000, 11000, 6000, 21000};
        int hargaBusuk = 1000;

        boolean cek = false;
        if (kapital[0][0] == 0) {
            cek = true;
        }
        int selisih = 0, baru = 0, rubah = 0, akhir, busuk = 0, gradeA = 0, gradeB = 0;
         
        for (int i = 0; i < kapital.length; i++) {
            for (int j = 0; j < kapital[0].length; j++) {
                if (j < gudang[0].length) {
                    if (cek == false) {
                        if ( (j != 4) && (j != 5)) {
                            kapital[i][j] = gudang[i][j];
                        }
                    }
                    else {
                        kapital[i][j] = gudang[i][j];
                    }
                }
                if (cek == true) {
                    if (j == 8) {
                        kapital[i][j] = acak.nextInt(kapital[i][4]);
                    }
                    else if (j == 11) {
                        kapital[i][j] = kapital[i][4] - kapital[i][8];
                    }
                    else if (j == 14) {
                        kapital[i][j] = (kapital[i][5] * hargaBusuk) + (kapital[i][8] * hargaA[i]) + (kapital[i][11] * hargaB[i]);
                    }
                }
                else {
                    if (j == 4) {
                        selisih = gudang[i][j] - kapital[i][j];
                        kapital[i][j] = gudang[i][j];
                    }
                    else if (j == 5) {
                        busuk = gudang[i][j]- kapital[i][j];
                        kapital[i][j] = gudang[i][j];
                    }
                    else if (j == 6) {
                        kapital[i][j] += busuk;
                    }
                    else if (j == 7) {
                        kapital[i][j] = busuk;
                    }
                    else if (j == 8) {
                            if (selisih == 0) {
                                baru = 0;
                                kapital[i][j] += 0;
                                gradeA = 0;
                            }
                            else if (selisih < 0){
                                int cekNegatif = kapital[i][j] + selisih;
                                if (cekNegatif < 0) {
                                    baru = cekNegatif;
                                    gradeA = (0 - kapital[i][j]);
                                    kapital[i][j] = 0;
                                }
                                else {
                                    kapital[i][j] += selisih;
                                    gradeA = selisih;
                                    baru = 0;
                                }
                            }
                            else {
                                baru = acak.nextInt(selisih);
                                gradeA = baru;
                                kapital[i][j] += baru;
                            }
                    }
                    else if (j == 9) {
                        kapital[i][j] += gradeA;
                    }
                    else if (j == 10) {
                        kapital[i][j] = gradeA;
                    }
                    else if (j == 11) {
                        if (baru > 0) {
                            kapital[i][j] += (selisih - baru);
                            gradeB = (selisih - baru);
                        }
                        else if (baru < 0){
                            kapital[i][j] += baru;
                            gradeB = baru;
                        }
                        else {
                            kapital[i][j] = kapital[i][4] - kapital[i][8];
                            if (selisih > 0) {
                                gradeB = selisih;
                            }
                            else {
                                gradeB = 0;
                            }
                        }
                    }
                    else if (j == 12) {
                        kapital[i][j] += gradeB;
                    }
                    else if (j == 13) {
                        kapital[i][j] = gradeB;
                    }
                    else if (j == 14) {
                        akhir = (kapital[i][5] * hargaBusuk) + (kapital[i][8] * hargaA[i]) + (kapital[i][11] * hargaB[i]);
                        rubah = (akhir - kapital[i][j]);
                        kapital[i][j] = akhir;
                    }
                    else if (j == 15) {
                        kapital[i][j] += rubah;
                    } 
                    else if (j == 16) {
                        kapital[i][j] = rubah;
                    }
                }    
            }
        }
        return kapital;
    }
   
    public static void main(String[] args) {
        int[][] gudang = new int[namaStok.length][hurufKolom.length];
        gudang = tambahGudang(gudang);

        int [][] kapital = new int[namaStok.length][hurufKapital.length];
        kapital = kapitalisasi(gudang, kapital);

        int[][] kapitalAwal = new int[namaStok.length][hurufKapital.length];
        for (int i = 0; i < kapitalAwal.length; i++) {
            for (int j = 0; j < kapitalAwal[0].length; j++) {
                kapitalAwal[i][j] = kapital[i][j];
            }
        }

        System.out.println("\n\n````````````````````Selamat Datang Di Sistem Gudang Restoran Anas```````````````````\n\n");

        String[] dataKunci = {"anas", "123", "jono", "456", "tono", "789"};
        boolean login = true;
        System.out.println("Login terlebih dahulu\n");

        boolean akhir = true;
        do {
            do {
                System.out.print("\nMasukan username : ");
                String username = input.next();
                System.out.print("\nMasukan password : ");
                String password = input.next();
    
                for (int i = 0; i < dataKunci.length; i += 2) {
                    if ( (username.equals(dataKunci[i])) && (password.equals(dataKunci[i + 1])) ) {
                        login = false;
                    }
                }
                if (login == true) {
                    System.out.println("\nUsername dan password salah");
                }
                else {
                    System.out.println("\nLogin berhasil");
                }

            }while (login == true);
            input.nextLine();
    
            boolean captcha = true;
            do {
                
                String diacak = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz01234567890";
                String [] hasil = new String [5];
                String jadi = "";
                for (int i = 0; i < hasil.length; i++) {
                    char random = (diacak).charAt(acak.nextInt(62));
                    hasil[i] = Character.toString(random);
                    jadi += hasil[i];
                }
        
                System.out.print("\n``````````Human captcha```````````\n\n");
                System.out.printf("Masukan input sesuai ini :  %s  =  ", jadi);
                String user = input.nextLine();
        
                if (user.equals(jadi)) {
                    System.out.println("\n`````````````Success`````````````");
                    System.out.println("\n`````````Anda bukan bot`````````\n\n");
                    captcha = false;
                }
                else {
                    System.out.println("\n```````````Input salah!```````````\n");
                    System.out.println("Ulangi Human captcha!\n");
                }
    
            }while(captcha == true);
        
            char syarat = ' ';
            do {
                System.out.println("`````````````````````````````````Home page fitur````````````````````````````````````\n");
                System.out.println("Berikut beberapa fitur yang ada: \n");
                String[] fitur = {"tambah", "ambil", "cari", "laporan", "kapitalisasi"};
                for (int i = 0; i < fitur.length; i++) {
                    if (i == 1) {
                        System.out.print("|" + fitur[i] + "|  ");
                    }
                    else {
                        System.out.print("|" + fitur[i] + "|   ");
                    }
                }
    
                System.out.print("\n\n pilih fitur (nama fitur): ");
                String pilih = input.next();
                input.nextLine();
            
                pilih = pilih.toLowerCase();
                char pilihan = (pilih).charAt(0);
                boolean menu = true;
                for(int i = 0; i < fitur.length; i++) {
                    char cek = (fitur[i]).charAt(0);
                    if (pilihan == cek) {
                        if (i == 0) {
                            tambahGudang(gudang);
                        }
                        else if (i == 1) {
                            ambilGudang(gudang);
                        }
                        else if (i == 2) {
                            cariGudang(gudang, kapital);
                        }
                        else if (i == 3) {
                            printNilai(namaStok, hurufKolom, gudang);
                        }
                        else {
                            boolean print = false;
                            int buang = 0;
                            for (int k = 0; k < gudang.length; k++) {
                                buang = buang + gudang[k][5];
                            }
                            if (buang != 0) {
                                print = true;
                            }
                            if (print == true) {
                                boolean inputan = true;
                                System.out.println("Agar Kapitalisasi jelas\n");
                                do {
                                    System.out.print("Pilih perlakuan stok busuk? (buang / tidak) : ");
                                    String masuk = input.next();
                                    input.nextLine();
                                    System.out.println();

                                    masuk = masuk.toLowerCase();
                                    char busuk = (masuk).charAt(0);
                                    if (busuk == 'b') {
                                        for (int j = 0; j < gudang.length; j++) {
                                            gudang[j][3] -= gudang[j][5];
                                            gudang[j][2] += gudang[j][5];
                                            System.out.printf("%s busuk terbuang : %s\n", namaStok[j], gudang[j][5]);
                                            gudang[j][5] = 0;
                                            inputan = false;
                                        }
                                    }
                                    else if (busuk == 't') {
                                        inputan = false;
                                    }
                                    else {
                                        System.out.println("input yang benar!");
                                    }
                                    
                                    
                                }while(inputan == true);
                                
                            }
                            kapitalisasi(gudang, kapital);
                            printNilai(namaStok, hurufKapital, kapital);
                            int totalKapital = 0, totalChange = 0, volatil = 0; 
                            for (int j = 0; j < kapital.length; j++) {
                                totalKapital += kapital[j][14];
                                totalChange += kapital[j][15];
                                volatil += kapital[j][16];
                            }
                            System.out.println("\nMaka total kapitalisasi : " + totalKapital);
                            System.out.println("Maka total capital change : " + totalChange);
                            System.out.println("total volatil : " + volatil);
                            char tanya; 
                            boolean cekTanya = true;
                            do {
                                System.out.print("\nagar kapitalisasi mudah dibaca , ingin tampilan kapitalisasi stok awal (y / t) : ");
                                tanya = input.next().charAt(0);
                                if (tanya == 'y' || tanya == 'Y') {
                                    printNilai(namaStok, hurufKapital, kapitalAwal);
                                    int totalAwal = 0;
                                    for (int j = 0; j < kapitalAwal.length; j++) {
                                        totalAwal += kapitalAwal[j][14];
                                    }
                                    System.out.println("Total kapitalisasi awal : " + totalAwal);
                                    cekTanya = false;
                                }
                                else if (tanya == 't' || tanya == 'T') {
                                    cekTanya = false;
                                }
                                else {
                                    System.out.println("input yang benar!\n");
                                }
                            }while(cekTanya == true);
                        }
                    }
                }
                do {
                    System.out.print("Apakah anda mau kembali ke homepage fitur? (y/t) : ");
                    syarat = input.next().charAt(0);
                    input.nextLine();
                    System.out.println();
                    if (syarat == 'y' || syarat == 'Y' || syarat == 't' || syarat == 'T') {
                        menu = false;
                    }
                    else {
                        System.out.println("Input yang benar ! \n");
                    }
                    
                }while (menu == true);
                
                
            }while (syarat == 'y' || syarat == 'Y');
            
            boolean logout = true;
            System.out.print("Apakah anda ingin logout ? (y / t) : ");
            syarat = input.next().charAt(0);
            char lagi;
            do {
                if (syarat == 'y' || syarat == 'Y') {
                    System.out.print("\nApakah anda ingin login lagi ? (y / t) : ");
                    lagi = input.next().charAt(0);
    
                    if (lagi == 't' || lagi == 'T') {
                        akhir = false;
                        logout = false;
                    }
                    else {
                        logout = false;
                    }
                }
                else{
                    System.out.println("\nAnda harus logout demi keamanan sistem!");
                    System.out.println("\nSistem akan logout otomatis!");
                    syarat = 'y';
                }
            }while(logout == true);
            
        }while( akhir == true);
        
        System.out.println("\n`````````````````Terima kasih telah mengunjugi sistem kami``````````````````");
    }
}
