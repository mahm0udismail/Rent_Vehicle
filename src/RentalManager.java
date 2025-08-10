import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RentalManager {
    List<Vehicle> vehicles = new ArrayList<>(
            List.of(
                    new Vehicle("Honda Accord", 2019),
                    new Vehicle("Toyota Camry", 2020),
                    new Vehicle("Ford Focus", 2018)
            )
    );

    private final Scanner scanner = new Scanner(System.in);
    boolean hasStart = true;

    public void start() {
        while (hasStart){
            displayMenu();
            int choice = scanner.nextInt();
            System.out.println("You selected option: " + choice);

            switch (choice){
                case 1-> listVehicles();
                case 2-> addVehicles();
                case 3-> updateVehicles();
                case 4-> deleteVehicles();
                case 5-> rantVehicle();
                case 6-> returnVehicle();
                case 7-> listRentedVehicles();
                case 8-> listTruckVehicles();
                case 9-> listCarVehicles();
                case 10-> {
                    System.out.println("Exiting the system. Goodbye!");
                    hasStart = false;
                }
            }

        }
    }

    private void listCarVehicles() {
        System.out.println("Car Vehicles:");
        vehicles.stream()
                .filter(v -> v instanceof Car)
                .map(v -> v.getModel() + " (" + v.getYear() + ")")
                .forEach(System.out::println);
        if (vehicles.stream().noneMatch(v -> v instanceof Car)) {
            System.out.println("No car vehicles available.");
        }
    }

    private void listTruckVehicles() {
        System.out.println("Truck Vehicles:");
        vehicles.stream()
                .filter(v -> v instanceof Truck)
                .map(v -> v.getModel() + " (" + v.getYear() + ")")
                .forEach(System.out::println);
        if (vehicles.stream().noneMatch(v -> v instanceof Truck)) {
            System.out.println("No truck vehicles available.");
        }
    }

    private void listRentedVehicles() {
        System.out.println("Rented Vehicles:");
        vehicles.stream()
                .filter(Vehicle::isRented)
                .map(v -> v.getModel() + " (" + v.getYear() + ")")
                .forEach(System.out::println);
        if (vehicles.stream().noneMatch(Vehicle::isRented)) {
            System.out.println("No vehicles are currently rented.");
        }
    }

    private void returnVehicle() {
        System.out.print("Enter the index of the vehicle to return (1 to " + vehicles.size() + "): ");
        int index = scanner.nextInt() - 1;
        if (index >= 0 && index < vehicles.size()) {
            Vehicle vehicle = vehicles.get(index);
            vehicle.returnVehicle();
        } else {
            System.out.println("Invalid index. Please try again.");
        }
    }

    private void rantVehicle() {
        listVehicles();
        System.out.print("Enter the index of the vehicle to rent (1 to " + vehicles.size() + "): ");
        int index = scanner.nextInt() - 1;
        if (index >= 0 && index < vehicles.size()) {
            Vehicle vehicle = vehicles.get(index);
            vehicle.rent();
        }
        else {
            System.out.println("Invalid index. Please try again.");
        }
    }

    private void listVehicles() {
        vehicles.stream().map(v -> (vehicles.indexOf(v) + 1) +
                ". Model: " +
                v.getModel() +
                ", Year: " +
                v.getYear() +
                ", Rented: " +
                (v.isRented() ? "Rented" : "Available"))
                .forEach(System.out::println);
    }

    private void addVehicles() {
        System.out.println("Enter vehicle type (Car/Truck) : ");
        String type = scanner.next();

        System.out.print("Enter vehicle model: ");
        String model = scanner.next();

        System.out.print("Enter vehicle year: ");
        int year = scanner.nextInt();

        switch (type.toLowerCase()) {
            case "truck" -> vehicles.add(new Truck(model, year));
            case "car" -> vehicles.add(new Car(model, year));
            default -> System.out.println("Invalid vehicle type. Please enter 'Car' or 'Truck'.");
        }

    }

    private void deleteVehicles() {
        System.out.print("Enter the index of the vehicle to delete (1 to " + vehicles.size() + "): ");
        int index = scanner.nextInt() - 1;

        if (index < 0 || index >= vehicles.size()) {
            System.out.println("Invalid index. Please try again.");
            return;
        }

        vehicles.remove(index);
        System.out.println("Vehicle deleted successfully!");
    }

    private void updateVehicles() {
        System.out.print("Enter the index of the vehicle to update (1 to " + vehicles.size() + "): ");
        int index = scanner.nextInt() - 1;

        if (index < 0 || index >= vehicles.size()) {
            System.out.println("Invalid index. Please try again.");
            return;
        }

        Vehicle vehicle = vehicles.get(index);
        System.out.print("Enter new model (current: " + vehicle.getModel() + "): ");
        vehicle.setModel(scanner.next());
        System.out.print("Enter new year (current: " + vehicle.getYear() + "): ");
        vehicle.setYear(scanner.nextInt());
        System.out.print("Is the vehicle rented? (true/false): ");
        vehicle.setRented(scanner.nextBoolean());

        System.out.println("Vehicle updated successfully!");
    }

    private void displayMenu() {
        System.out.println("\n--------------------------------");
        System.out.println("Welcome to the Rental Management System");
        System.out.println("--------------------------------");
        System.out.println("1. View all Vehicles");
        System.out.println("2. Add a new Vehicles");
        System.out.println("3. update Vehicles");
        System.out.println("4. Delete a Vehicles");
        System.out.println("5. Rent a Vehicle");
        System.out.println("6. Return a Vehicle");
        System.out.println("7. List Rented Vehicles");
        System.out.println("8. List Truck Vehicles");
        System.out.println("9. List Car Vehicles");
        System.out.println("10. Exit");
        System.out.print("Please enter your choice: ");
    }
}
