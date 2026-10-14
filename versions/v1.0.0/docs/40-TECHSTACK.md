> Versi: v1.0.0 | Status: disetujui | Menggantikan: -

# 40-TECHSTACK — titeny-app-kmp

Mengacu: Titeny-TownHall v1.0.0 (`https://github.com/Coding-Skuy/Titeny-TownHall/tree/main/platform/versions/v1.0.0`). Stack pengikat divisi: [`FSD/30-kontrak.md`](https://github.com/Coding-Skuy/Titeny-TownHall/blob/main/platform/versions/v1.0.0/FSD/30-kontrak.md).

## Konteks TownHall

Stack divisi dikunci di TownHall v1.0.0: web Bun + Svelte 5 + SvelteKit 2 + TypeScript; analis-desktop KMP Windows (Kotlin, Compose Multiplatform, Navigation3); backend Rust Axum 0.8; model dan pipeline Python 3.12. Repo ini: Klien desktop Windows untuk analis (modul `analis-desktop`) plus stub `baca-mobile`; tempat analis memvalidasi flag, menahan prediksi, membandingkan model, mengelola rekomendasi, dan mengekspor.

## Stack Repo Ini

| Lapisan | Pilihan | Versi target | STATUS |
|---|---|---|---|
| Bahasa | Kotlin | 2.1.x (kode kini 2.0.20) | defined |
| UI | Compose Multiplatform | 1.7.x | defined |
| Navigasi | Navigation3 runtime + ui | 1.0.0 | defined |
| HTTP | Ktor-client CIO | 3.1.x (kode kini 3.0.0) | defined |
| Lokal | SQLDelight SQLite, `%LOCALAPPDATA%/Titeny/analis.db` | 2.0.x, maks 500 MB, pangkas FIFO > 90 hari, VACUUM tiap Minggu | defined |
| DI | Koin | 4.x | defined |
| Rahasia klien | Windows Credential Manager | token analis; bukan berkas teks | defined |
| Target | Desktop JVM 17, MSI via WiX 3.14 | Windows 10 21H2+ / Windows 11, 64-bit | defined |
| Modul | `analis-desktop` (kurasi tulis) + `baca-mobile` (stub baca saja) | - | defined |

## Perintah

```powershell
./gradlew :analis-desktop:run
./gradlew :analis-desktop:test
./gradlew :analis-desktop:packageMsi
```

Artefak rilis: `Titeny-Analis-1.0.0.msi` + `SHA256SUMS.txt`.

## Matriks Fitur per Platform

| Fitur | Windows desktop (`analis-desktop`) | Android / iOS (`baca-mobile`) | Sumber |
|---|---|---|---|
| Masuk (token + OTP) | Ya | Tidak (stub) | `produk/22-kontrak-desktop-windows.md` bagian 5 |
| Antrean Validasi + DetailFlag | Ya (setuju / koreksi_nilai / tolak, alasan 10-140) | Tidak | PRD US-004 |
| Tahan Prediksi (maks 7 hari) | Ya | Tidak | `produk/22` bagian 3.2 |
| Bandingkan Model (MAPE vs kandidat 90 hari) | Ya | Tidak | `produk/22` bagian 3.3 |
| Kelola Rekomendasi | Ya | Tidak | `produk/22` bagian 3.4 |
| Ekspor CSV/XLSX (maks 50.000 baris/berkas) | Ya, hash SHA-256 diaudit | Tidak | `produk/22` bagian 3.5 |
| Luring (cache + antrean `aksi_id`) | Ya | Tidak | FSD/10 butir 4 |
| Baca papan insight | Tidak (analis memakai antrean) | Baca via web responsif 360 px, bukan app khusus | PRD non-goals |

## Graf Navigasi (Navigation3, terkunci, back stack maks 20)

```text
Masuk -> AntreanValidasi -> DetailFlag(:eventId evt_YYYYMMDD_NNNNNN)
                         -> TahanPrediksi
                         -> BandingModel
                         -> KelolaRekomendasi
                         -> Ekspor
Deep link: titeny://analis/flag/<eventId>  ->  DetailFlag
```

Jendela min 1120x720; tabel padat zebra; status berwarna plus teks; shortcut Ctrl+R segarkan, Ctrl+E ekspor, Esc kembali. State via `rememberSaveable` + `SavedStateHandle`; tanpa animasi kustom.

## Batasan

Dokumen ini hanya mengatur stack repo `titeny-app-kmp`. Perubahan versi pustaka atau stack dicatat sebagai ADR baru di `docs/decisions/` dan dilaporkan ke TownHall. Titeny read-only terhadap DB sumber; satu-satunya basis tulis adalah DB `titeny`.
