public class Main {
    public static void main(String[] args) {
        Garage garage = new Garage(10);

        Vehicle car1 = new Car("C1", "Toyota", 190, 11500, 4);
        Vehicle car2 = new Car("C2", "BMW", 220, 12000, 2);
        Vehicle moto1 = new Motorcycle("M1", "Yamaha", 160, 7000, false);
        Vehicle moto2 = new Motorcycle("M2", "Harley", 140, 3000, true);
        Vehicle truck1 = new Truck("T1", "Volvo", 120, 16000, 5000);

        garage.add(car1);
        garage.add(car2);
        garage.add(moto1);
        garage.add(moto2);
        garage.add(truck1);

        Vehicle[] vehicles = { car1, car2, moto1, moto2, truck1 };

        for (Vehicle v : vehicles) {
            v.move();
        }

        garage.rentById("C1");
        garage.rentById("M2");

        garage.printAvailable();
        garage.printNeedsService();

        garage.printRentalEstimate("C1", 3);

        Car anotherCar = new Car("C1", "Toyota", 180, 10000, 4);
        System.out.println(car1.equals(anotherCar));
    }
}