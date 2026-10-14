> Versi: v1.0.0 | Status: disetujui | Menggantikan: -

# 60-BLAST-RADIUS — titeny-app-kmp

Mengacu: Titeny-TownHall v1.0.0 (`https://github.com/Coding-Skuy/Titeny-TownHall/tree/main/platform/versions/v1.0.0`), [`townhall-roadmap.md` bagian 2](https://github.com/Coding-Skuy/ChefGenie-TownHall/blob/main/townhall-roadmap.md) (baris Titeny: point of concern "11 event; MAPE H+1 <= 8%"; batas context "12 komoditas x 8 pasar; read-only ke DB sumber") dan [`KONTEKS-LINTAS-DIVISI.md` bagian 8-9](https://github.com/Coding-Skuy/ChefGenie-TownHall/blob/main/KONTEKS-LINTAS-DIVISI.md).

## Pernyataan read-only

Repo ini tidak menulis ke DB sumber. Titeny hanya membaca dari Lumbung, TitipO, dan Pasaree (lewat event/konektor SELECT); satu-satunya basis tulis adalah DB `titeny`.

## Yang BOLEH disentuh repo ini

Modul `analis-desktop` dan `baca-mobile`; skema SQLDelight cache lokal; pemanggilan API Titeny (`/api/v1/analis/validasi`, `/api/v1/analis/tahan`, `/api/v1/rekomendasi/{id}/status`, endpoint baca); token analis di Credential Manager.

## Yang DILARANG

- Menambah koneksi ke DB apa pun (klien tidak punya jalur tulis langsung ke DB).
- Menghitung ulang harga, grade, atau demand di klien; semua angka dari API.
- Menambah layar di luar 7 layar terkunci tanpa PRD/FSD versi baru di TownHall.
- Menyimpan token di berkas teks atau penyimpanan web.

## Kontrak

- **Dikonsumsi**: API Titeny `titeny-backend-service` (baca prediksi/antrean, kontrak `FSD/30-kontrak.md`); token JWT `aud=titeny`, `iss=chefgenie-auth`; token desain dari `titeny-design`.
- **Diproduksi**: Aksi tulis idempoten `aksi_id` ke `POST /api/v1/analis/validasi` dan `POST /api/v1/analis/tahan`; ubah status rekomendasi; berkas ekspor beraudit (hash SHA-256 dicatat di server). Tidak ada event ke registry 11.
- Modul bersama `:shared:pantry-resep` adalah milik Pawonee dan tidak dipakai atau diubah oleh Titeny. `pedaree.stok.v1` adalah kontrak bilateral Pedaree-TitipO dan bukan bagian registry Titeny (11 event terkunci).

## Radius Dampak per Perubahan

| Perubahan | Dampak | Batas penahan |
|---|---|---|
| Layar antrean/tahan | Kurasi analis ikut berubah | Kunci `aksi_id` idempoten, alasan 10–140 wajib |
| Ktor timeout/retry | Risiko antre tertunda | Retry GET 2×, POST idempoten terkirim saat daring |
| Skema SQLite lokal | Cache dan antre ikut berubah | Batas 500 MB, pangkas FIFO 90 hari, VACUUM Minggu |
| Token analis | Salah simpan membocorkan akses | Wajib Windows Credential Manager, kedaluwarsa 8 jam + OTP |

## Rollback

Setiap rilis menyimpan MSI sebelumnya. Rollback berarti memasang ulang MSI lama dan memverifikasi Masuk → AntreanValidasi plus satu aksi `tahan` duplikat tetap duplikat.

## Batasan

Dokumen ini hanya mengatur radius repo `titeny-app-kmp`. Dampak repo lain ada di dokumen blast-radius repo tersebut. Insiden DB sumber diteruskan ke pemilik sumber.
