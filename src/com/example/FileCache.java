package com.example;

import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by anastasia on 04/03/15.
 */
public class FileCache {
    public static final ConcurrentHashMap<String, InputStream> cache = new ConcurrentHashMap<String, InputStream>();
}
