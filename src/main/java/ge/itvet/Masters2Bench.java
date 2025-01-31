package ge.itvet;

import ge.itvet.csvreaderlibrary.Csv;
import ge.itvet.csvreaderlibrary.IndexInLine;

@Csv(filePath = "Masters2Bench.csv")

public class Masters2Bench {
    @IndexInLine(0)
    private String weightCategory;
    @IndexInLine(1)
    private String name;
    @IndexInLine(2)
    private String nation;
    @IndexInLine(3)
    private String result;
    @IndexInLine(4)
    private String date;


    public String getweightCategory() {
        return weightCategory;
    }

    public void setweightCategory(String vin) {
        this.weightCategory = weightCategory;
    }

    @Override
    public String toString() {
        return "Masters2Bench{" +
                "weightCategory='" + weightCategory + '\'' +
                ", name='" + name + '\'' +
                ", nation='" + nation + '\'' +
                ", result='" + result + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
