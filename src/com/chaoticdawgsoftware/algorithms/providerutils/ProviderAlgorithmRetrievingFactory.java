package com.chaoticdawgsoftware.algorithms.providerutils;

import java.security.Provider;
import java.security.Security;
import java.util.*;

/**
 * ProviderAlgorithmRetrievingFactory.java
 * Created by ChaoticDawg on 1/20/17.
 */

public class ProviderAlgorithmRetrievingFactory {
    private static Provider[] providers = Security.getProviders();

    public static ArrayList<String> getAlgorithmList(String algorithm) {
        return getSortedAlgorithmList(buildAlgorithmList(algorithm));
    }

    private static ArrayList<String> buildAlgorithmList(String algorithm) {
        Set<String> keyNameSet = new HashSet<>();
        for (Provider provider : providers)
            ProviderKeyListCreator.getKeyList(algorithm, keyNameSet, provider);

        return new ArrayList<>(keyNameSet);
    }

    private static ArrayList<String> getSortedAlgorithmList(ArrayList<String> keyList) {
        Collections.sort(keyList);
        return keyList;
    }
}
