public class Vehicle {

    private String model;
    private int year;
    private boolean isRented;


    public Vehicle(String model, int year) {
        this.model = model;
        this.year = year;
        this.isRented = false; // Default to not rented
    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public void rent() {
        if (!isRented) {
            isRented = true;
            System.out.println(model + " rented successfully.");
        } else {
            System.out.println(model + " is already rented.");
        }
    }

    public void returnVehicle() {
        if (isRented) {
            isRented = false;
            System.out.println(model + " returned successfully.");
        } else {
            System.out.println(model + " is not rented.");
        }
    }


}
