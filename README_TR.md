# NEXUS Client — Android başlangıç projesi

Bu ZIP **APK değil, kaynak proje dosyalarıdır**.

## Bu sürümde bulunanlar
- NEXUS menü arayüzü.
- Minecraft'ın `com.mojang.minecraftpe` paketini açmayı deneyen düğme.
- HUD / PvP / Görsel / Dünya kategorilerinde demo satırları.

## Henüz çalışmayanlar
FPS/CPS/WASD/LMB/RMB oyun içi HUD'u, gerçek mini harita, waypoint ve Crystal PvP işlevleri yalnızca arayüz örneğidir. Minecraft oyun sürecine bağlanmış değiller. Minecraft 1.26.52 ile gerçek oyun içi modül uyumluluğu bu projede uygulanmadı.

## APK oluşturma
1. ZIP'i çıkar.
2. Android Studio veya uyumlu bir Android IDE'de proje klasörünü aç.
3. Gradle senkronizasyonunu tamamla.
4. `Build > Build APK(s)` seç.
5. Debug APK genellikle `app/build/outputs/apk/debug/app-debug.apk` içinde oluşur.

Tablet üzerinde derleme aracının Android 16 ile uyumlu olduğundan emin ol. Bu proje burada derlenip cihazda test edilmedi.
