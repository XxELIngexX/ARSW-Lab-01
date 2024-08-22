package edu.eci.arsw.blacklistvalidator;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * HostBlackListsValidator class to validate hosts against blacklists.
 */
public class HostBlackListsValidator {

    private static final int BLACK_LIST_ALARM_COUNT = 5;
    private static final Logger LOG = Logger.getLogger(HostBlackListsValidator.class.getName());
    private HostBlacklistsDataSourceFacade dataSource;

    public HostBlackListsValidator() {
        this.dataSource = HostBlacklistsDataSourceFacade.getInstance();
    }

    /**
     * Check the given host's IP address in the blacklists.
     *
     * @param ipaddress the IP address to check
     * @param numThreads the number of threads to use
     * @return the list of blacklists where the IP address was found
     */
    public List<Integer> checkHost(String ipaddress, int numThreads) {
        List<Integer> blacklists = new LinkedList<>();
        int occurrences = 0;

        CheckSegment[] threads = threadGenerator(numThreads, ipaddress);

        for (CheckSegment thread : threads) {
            try {
                thread.join();
                blacklists.addAll(thread.getBlacklists());
                occurrences += thread.getOccurrences();
            } catch (InterruptedException e) {
                LOG.log(Level.SEVERE, "Thread interrupted", e);
            }
        }

        if (occurrences >= BLACK_LIST_ALARM_COUNT) {
            LOG.log(Level.INFO, "The host was found in {0} blacklists", occurrences);
        } else {
            LOG.log(Level.INFO, "The host was found in less than {0} blacklists", BLACK_LIST_ALARM_COUNT);
        }

        return blacklists;
    }

    private CheckSegment[] threadGenerator(int numThreads, String ipaddress) {
        int serverCount = dataSource.getRegisteredServersCount();
        int segmentSize = serverCount / numThreads;
        CheckSegment[] threads = new CheckSegment[numThreads];

        for (int i = 0; i < numThreads; i++) {
            int startIndex = i * segmentSize;
            int endIndex = (i == numThreads - 1) ? serverCount : startIndex + segmentSize;
            threads[i] = new CheckSegment(dataSource, startIndex, endIndex, ipaddress);
            threads[i].start();
        }

        return threads;
    }
}