# titeny-app-kmp

Aplikasi Kotlin Multiplatform divisi **Titeny (AI Insight)**, org **Coding-Skuy**.

Modul utama:

- `:analis-desktop` — aplikasi analis desktop **Windows** (Compose Multiplatform Desktop, target Windows x64). Wajib ada dan menjadi fokus utama.
- `:baca-mobile` — stub baca di ponsel (Android/iOS, `commonMain` saja, belum ada logika penuh).

Ekosistem: [Titeny-TownHall](https://github.com/Coding-Skuy/Titeny-TownHall).

## Prasyarat

- JDK 17 (Temurin 17.0.11)
- Gradle 8.10 (wrapper)
- Kotlin 2.0.20
- Compose Multiplatform 1.7.0
- Android Gradle Plugin 8.5.2 (untuk stub ponsel)
- Windows 10/11 x64 untuk menjalankan analis-desktop

## Cara jalan (Windows)

```powershell
.\gradlew.bat :analis-desktop:run
```

## Cara jalan (stub ponsel)

```sh
./gradlew :baca-mobile:assembleDebug
```

Stub `baca-mobile` hanya menampilkan daftar insight dari API backend. Logika penuh menyusul di iterasi berikut sesuai peta jalan di Titeny-TownHall.

## Struktur

```text
analis-desktop/   # aplikasi desktop analis (Windows)
baca-mobile/      # stub baca ponsel (commonMain)
gradle/libs.versions.toml  # pin versi standar
```

## Kaitan backend

API insight: `titeny-backend-service` (Rust, basis data `titeny`). Alamat bawaan `http://localhost:8080`.
