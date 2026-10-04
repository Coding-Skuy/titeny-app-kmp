> Versi: v1.0.0 | Status: disetujui | Menggantikan: -

# 40-TECHSTACK — titeny-app-kmp

Mengacu: Titeny-TownHall v1.0.0 (https://github.com/Coding-Skuy/Titeny-TownHall/tree/main/versions/v1.0.0).

## Konteks TownHall

Stack divisi dikunci di TownHall v1.0.0: web Bun latest + Svelte 5 + SvelteKit Kit 2 + TypeScript latest; analis-desktop KMP Windows; backend Rust axum 0.8.4; model dan pipeline Python 3.12. Repo ini adalah bagian analis-desktop KMP Windows.

## Stack Repo Ini

| Lapisan | Pilihan | Versi target | STATUS |
|---|---|---|---|
| Bahasa | Kotlin | 2.1.x (repo kini 2.0.20 → target 2.1.x) | defined |
| UI | Compose Multiplatform | 1.7.x | defined |
| Navigasi | Navigation3 runtime + ui | 1.0.0 | defined |
| HTTP | Ktor-client CIO | 3.1.x (repo kini 3.0.0 → target 3.1.x) | defined |
| Lokal | SQLDelight SQLite | 2.0.x, `%LOCALAPPDATA%/Titeny/analis.db` maks 500 MB | defined |
| DI | Koin | 4.x | defined |
| Target | desktop JVM 17, kemas MSI via WiX 3.14 | Windows 10 21H2+ / 11 64-bit | defined |
| Modul | analis-desktop (kurasi tulis) + baca-mobile (stub baca saja, tidak dikirim V1) | — | defined |

Modul `baca-mobile` hanya stub; mobile V1 hanya baca via web responsif 360 px sesuai TownHall.

## Perintah

```powershell
./gradlew :composeApp:createDistributable
./gradlew :composeApp:packageMsi
```

Artefak: `Titeny-Analis-1.0.0.msi` + `SHA256SUMS.txt`.

## Batasan

Batasan dokumen ini: hanya stack analis-desktop KMP Windows. Angka prediksi tidak dihitung di repo ini; semua angka berasal dari API web. Tidak ada target macOS/Linux di v1.0.0. Perubahan versi pustaka wajib dicatat di TownHall berikutnya.
