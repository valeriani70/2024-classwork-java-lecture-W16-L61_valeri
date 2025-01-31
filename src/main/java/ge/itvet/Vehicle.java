package ge.itvet;

import ge.itvet.csvreaderlibrary.Csv;
import ge.itvet.csvreaderlibrary.IndexInLine;

@Csv(filePath = "vehicle.csv")

public class Vehicle {
    @IndexInLine(0)
    private String vin;
    @IndexInLine(1)
    private String brand;
    @IndexInLine(2)
    private String year;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vin='" + vin + '\'' +
                ", brand='" + brand + '\'' +
                ", year=" + year +
                '}';
    }
}
