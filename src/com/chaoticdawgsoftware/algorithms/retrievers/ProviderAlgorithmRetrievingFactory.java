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
    private static Set<String> keyNameSet = new HashSet<>();

    static ArrayList<String> getAlgorithmList(String algorithm) {
        String keyName = "";
        for (Provider provider : providers) {
            Enumeration<Object> providerKeys = provider.keys();
            while (providerKeys.hasMoreElements()) {
                Object key = providerKeys.nextElement();
                if (key.toString().startsWith(algorithm + ".")) {
                    keyName = key.toString().substring((algorithm + ".").length());
                }
                if (key.toString().startsWith("Alg.Alias." + algorithm + ".")) {
                    keyName = key.toString().substring(("Alg.Alias." + algorithm + ".").length());
                }
                String[] strings = keyName.split(" ");
                keyName = strings[0];
                if (!keyName.equals("")) {
                    keyNameSet.add(keyName);
                }
            }
        }

        return getSortedAlgorithmList();
    }

    private static ArrayList<String> getSortedAlgorithmList() {
        ArrayList<String> keyList = new ArrayList<>(keyNameSet);
        Collections.sort(keyList);
        return keyList;
    }
}
