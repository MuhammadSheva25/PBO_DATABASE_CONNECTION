
package projekarryo;

import java.util.Date;

public class Transaksi {
    private int id;
    private int idPelanggan;
    private int idKendaraan;
    private Date tanggalSewa;
    private Date tanggalKembali;
    private float totalBayar;

    public Transaksi(int id, int idPelanggan, int idKendaraan, Date tanggalSewa, Date tanggalKembali, float totalBayar) {
        this.id = id;
        this.idPelanggan = idPelanggan;
        this.idKendaraan = idKendaraan;
        this.tanggalSewa = tanggalSewa;
        this.tanggalKembali = tanggalKembali;
        this.totalBayar = totalBayar;
    }

    public int getId() {
        return id;
    }

    public int getIdPelanggan() {
        return idPelanggan;
    }

    public int getIdKendaraan() {
        return idKendaraan;
    }

    public Date getTanggalSewa() {
        return tanggalSewa;
    }

    public Date getTanggalKembali() {
        return tanggalKembali;
    }

    public float getTotalBayar() {
        return totalBayar;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdPelanggan(int idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public void setIdKendaraan(int idKendaraan) {
        this.idKendaraan = idKendaraan;
    }

    public void setTanggalSewa(Date tanggalSewa) {
        this.tanggalSewa = tanggalSewa;
    }

    public void setTanggalKembali(Date tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    public void setTotalBayar(float totalBayar) {
        this.totalBayar = totalBayar;
    }
}
