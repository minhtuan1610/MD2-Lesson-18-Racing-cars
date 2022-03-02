import java.util.Random;

public class Car implements Runnable {
    //  Khai báo tên xe đua
    private String name;

    public Car(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        //  Khởi tạo điểm start (km ban đầu)
        int runDistance = 0;
        //   Khởi tạo thời gian bắt đầu cuộc đua
        long startTime = System.currentTimeMillis();
        //  Kiểm tra chừng nào xe chưa kết thúc quãng đường thì xe vẫn tiếp tục chạy
        while (runDistance < Main.DISTANCE) {
            try {
                //  Random Speed km/h
                int speed = (new Random()).nextInt(20);
                //  Calculate traveled distance
                runDistance += speed;
                //  Build result graphic
                String log = "|"; //    Vị trí xuất phát/đích
                int percentTravel = (runDistance * 100) / Main.DISTANCE;
                for (int i = 0; i < Main.DISTANCE; i += Main.STEP) {
                    if (percentTravel >= i + Main.STEP) {
                        log += "="; //  Đánh dấu quãng đường xe đã đi qua
                    } else if (percentTravel >= i && percentTravel < i + Main.STEP) {
                        log += "o"; //  Đánh dấu vị trí của xe
                    } else {
                        log += "-"; //  Đánh dấu quãng đường còn lại trước khi về đích
                    }
                }
                log += "|";
                System.out.println("Car" + this.name + ": " + log + " " + Math.min(Main.DISTANCE, runDistance) + "KM");

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Car" + this.name + " broken...");
                break;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Car" + this.name + " Finish in " + (endTime - startTime) / 1000 + "s");
    }
}
