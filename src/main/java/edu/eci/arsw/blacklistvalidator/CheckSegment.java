package edu.eci.arsw.blacklistvalidator;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;

import java.util.LinkedList;
import java.util.List;

/**
 * CheckSegment class to check a segment of blacklist servers.
 */
public class CheckSegment extends Thread {

    private HostBlacklistsDataSourceFacade dataSource;
    private int startIndex;
    private int endIndex;
    private String ipaddress;
    private List<Integer> blacklists;
    private int occurrences;

    public CheckSegment(HostBlacklistsDataSourceFacade dataSource, int startIndex, int endIndex, String ipaddress) {
        this.dataSource = dataSource;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.ipaddress = ipaddress;
        this.blacklists = new LinkedList<>();
        this.occurrences = 0;
    }

    @Override
    public void run() {
        for (int i = startIndex; i < endIndex; i++) {
            if (dataSource.isInBlackListServer(i, ipaddress)) {
                blacklists.add(i);
                occurrences++;
            }
        }
    }

    public List<Integer> getBlacklists() {
        return blacklists;
    }

    public int getOccurrences() {
        return occurrences;
    }
}