package by.it.seledtsova.jd02_02;

public class Cashier implements Runnable{   // интерфейс Runnnable с методом run ()

    private String name; // имя нашего кассира

    public Cashier(int number) {
        name="\tCashier №" + number+" ";
    }

    @Override
    public void run() { //
        System.out.println(this+"opened"); // кассир открыл кассу
        while (!Manager.marketIsClosed()) { // пока магаз октрыт , кассиры работают
            Buyer buyer = QueueBuyers.poll();
            if (buyer != null) {
                synchronized (buyer.getMONITOR()) {
                    System.out.println(this + "started service " + buyer);
                    int timeout = Util.getRandom(2000, 5000);
                    Util.sleep(timeout);
                    System.out.println(this + "finished service " + buyer);
                    buyer.setWaiting(false);
                    buyer.notify();
                }
            } else {
                //need waiting
                Util.sleep(1);
            }
        }
        System.out.println(this+"closed"); // кассир закрыл кассу
    }

    @Override
    public String toString() { // печатаем имя
        return name;
    }
}
