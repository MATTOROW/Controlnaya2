import java.util.Arrays;

public class BroadcastsTime implements Comparable<BroadcastsTime> {
    private byte hour, minutes;

    public BroadcastsTime(byte hour, byte minutes) {
        this.hour = hour;
        this.minutes = minutes;
    }

    public BroadcastsTime(String time) {
        int[] intData = Arrays.stream(time.split(":")).mapToInt((a) -> Integer.parseInt(a)).toArray();
        hour = (byte) intData[0];
        minutes = (byte) intData[1];
    }

    public boolean after(BroadcastsTime t) {
        return this.hour > t.hour || (this.hour == t.hour && this.minutes >= t.minutes);
    }

    public boolean before(BroadcastsTime t) {
        return this.hour < t.hour || (this.hour == t.hour && this.minutes < t.minutes);
    }

    public boolean between(BroadcastsTime t1, BroadcastsTime t2) {
        return this.after(t1) && this.before(t2);
    }

    public int compareTo(BroadcastsTime t) {
        if (this.hour == t.hour) {
            return this.minutes - t.minutes;
        }
        return this.hour - t.hour;
    }

    public byte getHour() {
        return hour;
    }

    public void setHour(byte hour) {
        this.hour = hour;
    }

    public byte getMinutes() {
        return minutes;
    }

    public void setMinutes(byte minutes) {
        this.minutes = minutes;
    }

    @Override
    public String toString() {
        return hour + ":" + minutes;
    }
}

