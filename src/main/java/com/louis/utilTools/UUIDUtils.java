package com.louis.utilTools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UUIDUtils {

    private UUIDUtils() {
    }

    /**
     * @return a UUID
     */
    public static String getUUID() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");

        return format.format(new Date())+MathUtils.getSixNum();

    }

    @Deprecated
    public static String getGeneratID() {
        String uuid = UUID.randomUUID().toString();

        StringBuilder res = new StringBuilder();

        res.append(uuid.substring(0, 8)).append(uuid.substring(9, 13))
                .append(uuid.substring(14, 18)).append(uuid.substring(19, 23))
                .append(uuid.substring(24));
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(getUUID());
    }
}
