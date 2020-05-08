package responsi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    Scanner input = new Scanner(System.in);
    Statement statement = null;
    ResultSet resultSet = null;
    List<Data>daftar = new ArrayList<>();

    public Main(){
        while(true) {
            System.out.println("\nPROGRAM DATA COVID-19\n");
            System.out.println("MENU");
            System.out.println("1. Tambah Negara Baru");
            System.out.println("2. Negara Terkonfirmasi");
            System.out.print("Pilih : ");
            int menu =  input.nextInt();
            switch (menu) {
                case 1:
                    Tambah tambah = new Tambah();
                    tambah.tambahNegara();
                    break;
                case 2:
                    int menu2 = menuNegara();
                    int index = menu2-1;
                    Tampil show = new Tampil(daftar.get(index).getId());
                    Update update = new Update(daftar.get(index).getId());
                    System.out.println("\n\nMENU DATA COVID-19");
                    System.out.println("1. Update Data COVID-19");
                    System.out.println("2. Riwayat Data COVID-19");
                    System.out.print("Pilihan : ");
                    int menu3 = input.nextInt();
                    switch (menu3){
                        case 1:
                            update.updateData();
                            daftar.clear();
                            break;
                        case 2:
                            show.tampilData();
                            daftar.clear();
                            break;
                        default:
                            System.out.println("Menu Tidak Ada!");
                    }
                    break;
                default:
                    System.out.println("Menu Tidak Ada!");
            }
        }
    }

    public int menuNegara() {
        Koneksi koneksi = new Koneksi();
        try {
            statement = koneksi.getConnection().createStatement();
            String sql = "SELECT * FROM negara";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Data negara = new Data();
                negara.setId(resultSet.getString("id_negara"));
                negara.setNama(resultSet.getString("nama"));
                daftar.add(negara);
            }
            statement.close();
        } catch (SQLException sqlError) {
            System.out.println("Data Gagal Ditampilkan " + sqlError);
        } catch (ClassNotFoundException classError) {
            System.out.println("Driver tidak ditemukan !!");
        }
        System.out.println("\n\nDAFTAR NEGARA");
        for(int j= 0; j<daftar.size();j++){
            System.out.println((j+1)+". "+daftar.get(j).getNama());
        }
        System.out.print("Pilih Negara : ");
        return input.nextInt();
    }

    public static void main(String args[]){
        new Main();
    }
}
