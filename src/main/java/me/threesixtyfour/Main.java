package me.threesixtyfour;


import me.threesixtyfour.aggregate.AggregateMapper;
import me.threesixtyfour.aggregate.AggregateReducer;
import me.threesixtyfour.aggregate.DatedReview;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration aggregateJob = new Configuration();
        Job reviewAggregateJob = Job.getInstance(aggregateJob, "review aggregate");
        reviewAggregateJob.setJarByClass(Main.class);
        reviewAggregateJob.setMapperClass(AggregateMapper.class);
        reviewAggregateJob.setCombinerClass(AggregateReducer.class);
        reviewAggregateJob.setReducerClass(AggregateReducer.class);
        reviewAggregateJob.setOutputKeyClass(DatedReview.class);
        reviewAggregateJob.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(reviewAggregateJob, new Path(args[0]));
        FileOutputFormat.setOutputPath(reviewAggregateJob, new Path(args[1]));
        System.exit(reviewAggregateJob.waitForCompletion(true) ? 0 : 1);

    }
}