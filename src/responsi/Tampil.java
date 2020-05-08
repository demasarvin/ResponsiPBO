package responsi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Tampil {
    String id;
    Scanner input = new Scanner(System.in);
    Koneksi koneksi = new Koneksi();
    public Tampil(String vid){
        id = vid;
    }
    
    public void tampilData() {
        try {
            Statement statement = koneksi.getConnection().createStatement();
            String query = "SELECT * FROM data d INNER JOIN negara n ON d.id_negara=n.id_negara WHERE d.id_negara ='" + id + "'";
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("\nRIWAYAT DATA COVID-19 :");
            while (resultSet.next()) {
                System.out.println("\nPada Tanggal "+resultSet.getString("tanggal"));
                System.out.println("ID Negara\t: " + resultSet.getString("id_negara"));
                System.out.println("Negara\t\t: " + resultSet.getString("nama"));
                System.out.println("Positif\t\t: " +resultSet.getString("positif"));
                System.out.println("Sembuh\t\t: " +resultSet.getString("sembuh"));
                System.out.println("Meninggal\t: " +resultSet.getString("meninggal"));
            }
            statement.close();
        } catch (SQLException sqlError) {
            System.out.println("Data Gagal Ditampilkan " + sqlError);
        } catch (ClassNotFoundException classError) {
            System.out.println("Driver tidak ditemukan !!");
        }
    }
}
