import com.cxzgwing.utils.TimeConsume;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        TimeConsume tc = new TimeConsume();
        tc.addStartTime();

        // Simulate code runtime
        Thread.sleep(500);

        tc.addEndTime();
        tc.print();

    }
}
