package com.chaoticdawgsoftware.algorithms.providerutils;

import java.security.Provider;
import java.util.Enumeration;
import java.util.Set;

/**
 * ProviderKeyListCreator.java
 * Created by ChaoticDawg on 6/1/17.
 */

class ProviderKeyListCreator {

    static void getKeyList(String algorithm, Set<String> keyNameSet, Provider provider) {
        Enumeration<Object> providerKeys = provider.keys();
        while (providerKeys.hasMoreElements())
            addKeyNameToSet(getKeyName(algorithm, providerKeys.nextElement()), keyNameSet);
    }

    private static String getKeyName(String algorithm, Object key) {
        String keyName = checkKeyBeginning(algorithm, key);

        return (keyName.split(" "))[0];
    }

    private static String checkKeyBeginning(String algorithm, Object key) {
        String keyName = checkKeyStartsWithAlgorithmName(algorithm, key);
        keyName = checkKeyStartsWithAlias(algorithm, key, keyName);
        return keyName;
    }

    private static String checkKeyStartsWithAlgorithmName(String algorithm, Object key) {
        if (key.toString().startsWith(algorithm + "."))
            return key.toString().substring((algorithm + ".").length());
        return "";
    }

    private static String checkKeyStartsWithAlias(String algorithm, Object key, String keyName) {
        if (key.toString().startsWith("Alg.Alias." + algorithm + "."))
            keyName = key.toString().substring(("Alg.Alias." + algorithm + ".").length());
        return keyName;
    }

    private static void addKeyNameToSet(String keyName, Set<String> keyNameSet) {
        if (!keyName.equals(""))
            keyNameSet.add(keyName);
    }
}
