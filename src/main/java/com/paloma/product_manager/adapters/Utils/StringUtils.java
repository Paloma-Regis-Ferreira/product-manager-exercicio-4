package com.paloma.product_manager.adapters.Utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtils {

    public static String treatmentString(String productName) {
        String normalized = Normalizer.normalize(productName, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String treatedName = pattern.matcher(normalized).replaceAll("");
        treatedName = treatedName.replaceAll("[^a-zA-Z0-9]", " ");
        treatedName = treatedName.replaceAll("\\s+", " ");
        treatedName = treatedName.trim();
        return treatedName;
    }
}
