public class FirstFailingVersion {

    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {
        long first = 0;
        long last = n;

        // Like binary search, but different.
        while (true) {
            long i = (first + last)/2;
            if (isBadVersion.isFailingVersion(i) && !isBadVersion.isFailingVersion(i - 1)) {
                return i;
            }
            else if (isBadVersion.isFailingVersion(i)) {
                last = i - 1;
            }
            else if (!isBadVersion.isFailingVersion(i)) {
                first = i + 1;
            }
}


    }
}
