package projekarryo;

public class Kendaraan {
    private int id;
    private String jenis;
    private String merk;
    private String noPolisi;
    private float hargaSewaPerHari;

    public Kendaraan(int id, String jenis, String merk, String noPolisi, float hargaSewaPerHari) {
        this.id = id;
        this.jenis = jenis;
        this.merk = merk;
        this.noPolisi = noPolisi;
        this.hargaSewaPerHari = hargaSewaPerHari;
    }

    public int getId() {
        return id;
    }

    public String getJenis() {
        return jenis;
    }

    public String getMerk() {
        return merk;
    }

    public String getNoPolisi() {
        return noPolisi;
    }

    public float getHargaSewaPerHari() {
        return hargaSewaPerHari;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public void setNoPolisi(String noPolisi) {
        this.noPolisi = noPolisi;
    }

    public void setHargaSewaPerHari(float hargaSewaPerHari) {
        this.hargaSewaPerHari = hargaSewaPerHari;
    }
}