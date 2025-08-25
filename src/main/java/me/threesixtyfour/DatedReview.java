package me.threesixtyfour;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.MapTask;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DatedReview implements WritableComparable<DatedReview> {
    private short year;
    private byte month;
    private int stars;

    public DatedReview() {

    }
    public DatedReview(short year, byte month, int stars) {
        this.year = year;
        this.month = month;
        this.stars = stars;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeShort(this.year);
        dataOutput.write(month);
        dataOutput.writeInt(stars);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.year = dataInput.readShort();
        this.month = dataInput.readByte();
        this.stars = dataInput.readInt();
    }

    @Override
    public String toString() {
        return "DatedReview [year=" + year + ", month=" + month + ", stars=" + stars + "]";
    }

    @Override
    public int compareTo(DatedReview o) {
        if (this.year != o.year) {
            return this.year - o.year;
        }
        if (this.month != o.month) {
            return this.month - o.month;
        }
        if (this.stars != o.stars) {
            return this.stars - o.stars;
        }
        return 0;
    }
}
