public class Programme {
    private String channelName, programmeName;
    private BroadcastsTime time;

    public Programme(String channelName, String programmeName, BroadcastsTime time) {
        this.channelName = channelName;
        this.programmeName = programmeName;
        this.time = time;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getProgrammeName() {
        return programmeName;
    }

    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }

    public BroadcastsTime getTime() {
        return time;
    }

    public void setTime(BroadcastsTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return channelName + "; " + time + "; " + programmeName;
    }
}
