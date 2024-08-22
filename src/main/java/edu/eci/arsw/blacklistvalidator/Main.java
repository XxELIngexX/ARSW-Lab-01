package edu.eci.arsw.blacklistvalidator;

import java.util.List;

/**
 * Main class to run the HostBlackListsValidator.
 */
public class Main {

    public static void main(String a[]) throws InterruptedException {
        HostBlackListsValidator hblv = new HostBlackListsValidator();

        List<Integer> blackListOcurrences = hblv.checkHost("202.24.34.55", 100);
        System.out.println("The host was found in the following blacklists:" + blackListOcurrences);
    }
}