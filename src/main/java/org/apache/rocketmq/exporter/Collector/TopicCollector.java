package org.apache.rocketmq.exporter.Collector;

import io.prometheus.client.Collector;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.GaugeMetricFamily;
import io.prometheus.client.exporter.common.TextFormat;

import java.io.StringWriter;
import java.io.Writer;
import java.util.*;
import java.util.stream.Collectors;

public class TopicCollector extends Collector {
    private static final Map<String, MetricFamilySamples> topicToMetrics = new HashMap<String, MetricFamilySamples>();

    public List<MetricFamilySamples> collect() {
//        List<MetricFamilySamples> mfs = new ArrayList<MetricFamilySamples>();
//        // With no labels.
//        mfs.add(new GaugeMetricFamily("my_gauge", "help", 42));
//        // With labels
//        GaugeMetricFamily labeledGauge = new GaugeMetricFamily("my_other_gauge", "help", Arrays.asList("labelname"));
//        labeledGauge.addMetric(Arrays.asList("foo"), 4);
//        labeledGauge.addMetric(Arrays.asList("bar"), 5);
//        mfs.add(labeledGauge);
        System.out.println("topicMetrics length is " + topicToMetrics.values().size());
        return  new ArrayList<>(topicToMetrics.values());
    }
    public void AddMetric(String topic, double value)
    {
        GaugeMetricFamily labeledGauge = new GaugeMetricFamily("producer.ts", "InTps", Arrays.asList("topic"));
        labeledGauge.addMetric(Arrays.asList(topic), value);
        topicToMetrics.put(topic, labeledGauge);

    }


}
