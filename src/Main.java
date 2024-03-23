import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        List<String> list;
        try {
            list = Files.readAllLines(new File("schedule.txt").toPath(), Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 4 пункт
        Map<String, List<Programme>> mapA = new HashMap<>();
        String channel = "";
        for (int i = 0; i < list.size(); ++i) {
            String elem = list.get(i);
            if (elem.charAt(0) == '#') {
                channel = elem.substring(1);
                mapA.put(channel, new ArrayList<>());
                continue;
            }
            BroadcastsTime time = new BroadcastsTime(elem);
            String programmeName = list.get(i + 1);
            mapA.get(channel).add(new Programme(channel, programmeName, time));
            ++i;
        }
        mapA.forEach((key, value) -> Collections.sort(value, (a, b) ->
                a.getTime().compareTo(b.getTime())));
        mapA.forEach((key, value) -> System.out.println(key + "\n" + Arrays.toString(value.toArray())));
        System.out.println("_______________________________________________________________________________________");
        // 5 пункт
        List<Programme> allProgrammes = new ArrayList<>();
        for (List<Programme> elem: mapA.values()) {
            allProgrammes.addAll(elem);
        }
        // 6 пункт
        Collections.sort(allProgrammes, (a, b) ->
            a.getTime().compareTo(b.getTime()));
        System.out.println(Arrays.toString(allProgrammes.toArray()));
        System.out.println("___________________________________________________________________________________________");
        // 7 пункт
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        BroadcastsTime time = new BroadcastsTime(LocalDateTime.now().format(formatter));
        List<Programme> now = new ArrayList<>();
        for (var elem: mapA.values()) {
            for (int i = 0; i < elem.size(); ++i) {
                Programme tmp = elem.get(i);
                if (tmp.getTime().before(time)) {
                    if (i != elem.size() - 1 || elem.get(i + 1).getTime().after(time)) {
                        now.add(tmp);
                    }
                }
                break;
            }
        }
        System.out.println(Arrays.toString(now.toArray()));
        System.out.println("___________________________________________________________________________________________");
        // 8 пункт
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        System.out.println(Arrays.toString(allProgrammes.stream().filter((a) -> a.getProgrammeName().indexOf(name) != -1).toArray()));
        System.out.println("___________________________________________________________________________________________");
        // 9 пункт
        System.out.println("___________________________________________________________________________________________");
        // 10 пункт
        String channelName = sc.nextLine();
        BroadcastsTime t1 = new BroadcastsTime(sc.nextLine());
        BroadcastsTime t2 = new BroadcastsTime(sc.nextLine());
        System.out.println(
                Arrays.toString(allProgrammes.stream().filter((a) -> a.getChannelName().equals(channelName) &&
                        a.getTime().between(t1, t2)).toArray()));
    }
}