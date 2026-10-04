package id.skuy.titeny.baca

data class InsightRingkas(val judul: String, val nilai: String)

object BacaRepository {
    const val BASE_URL = "http://localhost:8080"

    fun daftarStub(): List<InsightRingkas> = listOf(
        InsightRingkas("Harga cabai 7 hari", "Naik 4,2%"),
        InsightRingkas("Panen padi", "Siap 12 hari"),
        InsightRingkas("Stok lumbung", "Aman"),
    )
}
