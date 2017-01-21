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
    private static Set<String> itemSet = new HashSet<>();

    static ArrayList<String> getAlgorithmList(String algorithm) {
        ArrayList<String> itemList;
        String item = "";
        for (Provider provider : providers) {
            Enumeration<Object> providerObj = provider.keys();
            while (providerObj.hasMoreElements()) {
                Object obj = providerObj.nextElement();
                if (obj.toString().startsWith(algorithm + ".")) {
                    item = obj.toString().substring((algorithm + ".").length());
                }
                if (obj.toString().startsWith("Alg.Alias." + algorithm + ".")) {
                    item = obj.toString().substring(("Alg.Alias." + algorithm + ".").length());
                }
                String[] strings = item.split(" ");
                item = strings[0];
                if (!item.equals("")) {
                    itemSet.add(item);
                }
            }
        }

        itemList = new ArrayList<>(itemSet);
        Collections.sort(itemList);
        return itemList;
    }
}
