import java.sql.Time;
import java.time.LocalTime;
import java.util.HashMap;

public class InfoServer {
    int CACHE_MAX_SIZE = 8;
    int cacheHit = 0;
    int partsHit = 0;
    private String[] parts = new String[] {"string0", "string1", "string2", "string3", "string4",
            "string5", "string6", "string7", "string8", "string9"};
    private HashMap<String, LocalTime> cache = new HashMap<>();

    public String getInfo(String key) throws InterruptedException {
        String result = "";

        // Try to get from cache
        if (cache.containsKey(key)) {
            result = key + " retrieved from cache: " + getFromCache(key);
        } else {
            result = key + " retrieved from parts: " + getFromParts(key);
        }
        System.out.println("Cache hit: " + cacheHit + " Parts hit: " + partsHit);
        return result;
    }

    private boolean getFromParts(String key) throws InterruptedException {
        partsHit++;
        // Get from parts
        boolean result = false;
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equals(key)) {
                result = true;
            }
        }
        // Update cache with cleanup
        if (cache.size() > CACHE_MAX_SIZE) {
            String oldestKey = "";
            LocalTime oldestTime = LocalTime.now();
            for (String cacheKey : cache.keySet()) {
                if (cache.get(cacheKey).isBefore(oldestTime)) {
                    oldestTime = cache.get(cacheKey);
                    oldestKey = cacheKey;
                }
            }
            System.out.println("Removed oldest: " + oldestKey + " " + cache.get(oldestKey).toString());
            cache.remove(oldestKey);
            System.out.println("Cache keys: " + cache.keySet().toString());
        }
        cache.put(key, LocalTime.now());
        System.out.println("Updated cache: " + key + " " + cache.get(key));
        result = true;
        // Return
        Thread.sleep(2000);
        return result;
    }

    private boolean getFromCache(String key) {
        cacheHit++;
        if (cache.containsKey(key)) {
            // Update cache use time for the item
            cache.put(key, LocalTime.now());
            return true;
        }
        return false;
    }
}
