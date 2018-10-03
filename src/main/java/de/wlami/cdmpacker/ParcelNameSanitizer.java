package de.wlami.cdmpacker;

import org.apache.maven.plugin.logging.Log;

public class ParcelNameSanitizer {

    public String sanitzieParcelName(String name, Log log) {
        String sanitizedName = name.replaceAll("-", "_").toUpperCase();
        if (log != null && !name.equals(sanitizedName)) {
            log.info("Sanitizing parcel name. Original value [" + name + "] new value [" + sanitizedName +
                    "]");
        }
        return sanitizedName;
    }
}
