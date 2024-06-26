/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekarryo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println("Menu Utama:");
                System.out.println("1. Tambah Data Pelanggan");
                System.out.println("2. Tambah Data Kendaraan");
                System.out.println("3. Transaksi Penyewaan");
                System.out.println("4. Lihat Data");
                System.out.println("5. Keluar");
                System.out.print("Pilih menu: ");
                int pilihan = scanner.nextInt();
                scanner.nextLine(); // Mengkonsumsi newline
                switch (pilihan) {
                    case 1:
                        tambahDataPelanggan();
                        break;
                    case 2:
                        tambahDataKendaraan();
                        break;
                    case 3:
                        transaksiPenyewaan();
                        break;
                    case 4:
                        lihatData();
                        break;
                    case 5:
                        System.exit(0);
                    default:
                        System.out.println("Pilihan tidak valid! Silakan pilih antara 1-5.");
                }
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
                scanner.nextLine(); // Clear the buffer
            }
        }
    }

    private static void tambahDataPelanggan() {
        try (Connection conn = Database.getConnection()) {
            System.out.print("Nama: ");
            String nama = scanner.nextLine();
            System.out.print("Alamat: ");
            String alamat = scanner.nextLine();
            System.out.print("No Telepon: ");
            String noTelepon = scanner.nextLine();

            String sql = "INSERT INTO pelanggan (nama, alamat, no_telepon) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nama);
            stmt.setString(2, alamat);
            stmt.setString(3, noTelepon);
            stmt.executeUpdate();

            System.out.println("Data pelanggan berhasil ditambahkan.");
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan saat menambahkan data pelanggan: " + e.getMessage());
        }
    }

    private static void tambahDataKendaraan() {
        try (Connection conn = Database.getConnection()) {
            System.out.print("Jenis: ");
            String jenis = scanner.nextLine();
            System.out.print("Merk: ");
            String merk = scanner.nextLine();
            System.out.print("No Polisi: ");
            String noPolisi = scanner.nextLine();
            System.out.print("Harga Sewa Per Hari: ");
            float hargaSewaPerHari = scanner.nextFloat();

            String sql = "INSERT INTO kendaraan (jenis, merk, no_polisi, harga_sewa_per_hari) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, jenis);
            stmt.setString(2, merk);
            stmt.setString(3, noPolisi);
            stmt.setFloat(4, hargaSewaPerHari);
            stmt.executeUpdate();

            System.out.println("Data kendaraan berhasil ditambahkan.");
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan saat menambahkan data kendaraan: " + e.getMessage());
        }
    }

    private static void transaksiPenyewaan() {
        try (Connection conn = Database.getConnection()) {
            System.out.print("ID Pelanggan: ");
            int idPelanggan = scanner.nextInt();
            System.out.print("ID Kendaraan: ");
            int idKendaraan = scanner.nextInt();
            scanner.nextLine(); // Mengkonsumsi newline
            System.out.print("Tanggal Sewa (yyyy-mm-dd): ");
            String tanggalSewaStr = scanner.nextLine();
            System.out.print("Tanggal Kembali (yyyy-mm-dd): ");
            String tanggalKembaliStr = scanner.nextLine();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date tanggalSewa = sdf.parse(tanggalSewaStr);
            Date tanggalKembali = sdf.parse(tanggalKembaliStr);

            long diffInMillies = Math.abs(tanggalKembali.getTime() - tanggalSewa.getTime());
            long diff = diffInMillies / (1000 * 60 * 60 * 24);

            String sqlHarga = "SELECT harga_sewa_per_hari FROM kendaraan WHERE id_kendaraan = ?";
            PreparedStatement stmtHarga = conn.prepareStatement(sqlHarga);
            stmtHarga.setInt(1, idKendaraan);
            ResultSet rsHarga = stmtHarga.executeQuery();
            float hargaSewaPerHari = 0;
            if (rsHarga.next()) {
                hargaSewaPerHari = rsHarga.getFloat("harga_sewa_per_hari");
            }

            float totalBayar = diff * hargaSewaPerHari;

            String sql = "INSERT INTO transaksi (id_pelanggan, id_kendaraan, tanggal_sewa, tanggal_kembali, total_bayar) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPelanggan);
            stmt.setInt(2, idKendaraan);
            stmt.setDate(3, new java.sql.Date(tanggalSewa.getTime()));
            stmt.setDate(4, new java.sql.Date(tanggalKembali.getTime()));
            stmt.setFloat(5, totalBayar);
            stmt.executeUpdate();

            System.out.println("Transaksi penyewaan berhasil dicatat.");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat mencatat transaksi penyewaan: " + e.getMessage());
        }
    }

    private static void lihatData() {
        try (Connection conn = Database.getConnection()) {
            System.out.println("1. Lihat Data Pelanggan");
            System.out.println("2. Lihat Data Kendaraan");
            System.out.println("3. Lihat Data Transaksi");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Mengkonsumsi newline

            String sql = "";
            String tableName = "";
            switch (pilihan) {
                case 1:
                    sql = "SELECT * FROM pelanggan";
                    tableName = "pelanggan";
                    break;
                case 2:
                    sql = "SELECT * FROM kendaraan";
                    tableName = "kendaraan";
                    break;
                case 3:
                    sql = "SELECT * FROM transaksi";
                    tableName = "transaksi";
                    break;
                default:
                    System.out.println("Pilihan tidak valid! Silakan pilih antara 1-3.");
                    return;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("Data kosong.");
                return;
            }

            while (rs.next()) {
                if (tableName.equals("pelanggan")) {
                    System.out.println("ID: " + rs.getInt("id_pelanggan") + ", Nama: " + rs.getString("nama") + ", Alamat: " + rs.getString("alamat") + ", No Telepon: " + rs.getString("no_telepon"));
                } else if (tableName.equals("kendaraan")) {
                    System.out.println("ID: " + rs.getInt("id_kendaraan") + ", Jenis: " + rs.getString("jenis") + ", Merk: " + rs.getString("merk") + ", No Polisi: " + rs.getString("no_polisi") + ", Harga Sewa Per Hari: " + rs.getFloat("harga_sewa_per_hari"));
                } else {
                    System.out.println("ID Transaksi: " + rs.getInt("id_transaksi") + ", ID Pelanggan: " + rs.getInt("id_pelanggan") + ", ID Kendaraan: " + rs.getInt("id_kendaraan") + ", Tanggal Sewa: " + rs.getDate("tanggal_sewa") + ", Tanggal Kembali: " + rs.getDate("tanggal_kembali") + ", Total Bayar: " + rs.getFloat("total_bayar"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan saat melihat data: " + e.getMessage());
        }
    }
}
