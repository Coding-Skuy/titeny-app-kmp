> Versi: v1.0.0 | Status: disetujui | Menggantikan: -

# 60-BLAST-RADIUS — titeny-app-kmp

Mengacu: Titeny-TownHall v1.0.0 (https://github.com/Coding-Skuy/Titeny-TownHall/tree/main/versions/v1.0.0).

## Pernyataan read-only

Repo ini tidak menulis ke DB sumber. Aplikasi analis-desktop tidak pernah membuka koneksi ke DB Lumbung, TitipO, atau Pasaree; satu-satunya tulis adalah aksi kurasi (`validasi`, `tahan`, status rekomendasi) ke API Titeny yang menyimpan ke DB `titeny`, plus cache SQLite lokal analis. Modul `baca-mobile` tidak menulis apa pun.

## Radius Dampak per Perubahan

| Perubahan | Dampak | Batas penahan |
|---|---|---|
| Layar antrean/tahan | Kurasi analis ikut berubah | Kunci `aksi_id` idempoten, alasan 10–140 wajib |
| Ktor timeout/retry | Risiko antre tertunda | Retry GET 2×, POST idempoten terkirim saat daring |
| Skema SQLite lokal | Cache dan antre ikut berubah | Batas 500 MB, pangkas FIFO 90 hari, VACUUM Minggu |
| Token analis | Salah simpan membocorkan akses | Wajib Windows Credential Manager, kedaluwarsa 8 jam + OTP |

## Larangan Eksplisit

- Dilarang menambah koneksi ke DB sumber.
- Dilarang menghitung ulang harga, grade, atau fee di klien; semua angka memakai respons server.
- Dilarang menyimpan token di berkas teks atau `localStorage`.

## Rollback

Setiap rilis menyimpan MSI sebelumnya. Rollback berarti memasang ulang MSI lama dan memverifikasi Masuk → AntreanValidasi plus satu aksi `tahan` duplikat tetap duplikat.

## Batasan

Batasan dokumen ini: hanya radius repo analis-desktop. Dampak API ada di `titeny-backend-service`, dampak model di `titeny-ai-models`. Insiden DB sumber diteruskan ke pemilik sumber.
