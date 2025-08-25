package me.threesixtyfour.aggregate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Scanner;

public class AggregateMapper extends Mapper<Object, Text, DatedReview, IntWritable> {
    private final static IntWritable one = new IntWritable(1);

    private final static Gson GSON = new Gson();

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        Scanner reader = new Scanner(value.toString());

        while(reader.hasNextLine()) {
            JsonObject obj = GSON.fromJson(reader.nextLine(), JsonObject.class);
            String date = obj.get("date").getAsString();
            double stars =  obj.get("stars").getAsDouble();

            int year = Integer.parseInt(date.substring(0,4));
            int month = Integer.parseInt(date.substring(5,7));

            DatedReview review = new DatedReview((short) year,(byte) month, (int) stars);
            context.write(review, one);
        }
    }
}
