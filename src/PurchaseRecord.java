
import java.text.SimpleDateFormat;
import java.util.Date;

public class PurchaseRecord extends Item {

    Date time;

    public PurchaseRecord(Date time, String name, int amount, Float totalCost) {
        super(name, totalCost,  amount);
        this.time = time;
    }

    public String toString() {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(this.time);
        String purchase = " was purchased " + "@ " + time ;
        return purchase;
    }

}

