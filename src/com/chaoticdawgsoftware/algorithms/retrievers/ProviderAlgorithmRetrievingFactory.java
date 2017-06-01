package com.chaoticdawgsoftware.algorithms.retrievers;

import java.security.Provider;
import java.security.Security;
import java.util.*;

/**
 * ProviderAlgorithmRetrievingFactory.java
 * Created by ChaoticDawg on 1/20/17.
 */

class ProviderAlgorithmRetrievingFactory {
    private static Provider[] providers = Security.getProviders();

    static ArrayList<String> getAlgorithmList(String algorithm) {
        return getSortedAlgorithmList(buildAlgorithmList(algorithm));
    }

    private static ArrayList<String> buildAlgorithmList(String algorithm) {
        Set<String> keyNameSet = new HashSet<>();
        for (Provider provider : providers)
            getKeyList(algorithm, keyNameSet, provider);

        return new ArrayList<>(keyNameSet);
    }

    private static void getKeyList(String algorithm, Set<String> keyNameSet, Provider provider) {
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

    private static ArrayList<String> getSortedAlgorithmList(ArrayList<String> keyList) {
        Collections.sort(keyList);
        return keyList;
    }
}
