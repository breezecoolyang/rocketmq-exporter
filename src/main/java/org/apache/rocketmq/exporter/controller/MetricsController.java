package org.apache.rocketmq.exporter.controller;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;
import org.apache.rocketmq.exporter.Collector.TopicCollector;
import org.apache.rocketmq.exporter.task.DashboardCollectTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
@RestController
@EnableAutoConfiguration
public class MetricsController {
    private static CollectorRegistry registry = new CollectorRegistry();
    private final static Logger log = LoggerFactory.getLogger(MetricsController.class);

    @RequestMapping("/metrics")
    @ResponseBody
    private void metrics(HttpServletResponse response) throws IOException {
        StringWriter writer = new StringWriter();
        try {
            registry.clear();
            new TopicCollector().register(registry);
            TextFormat.write004(writer, registry.metricFamilySamples());
        } catch (Exception e) {
            log.info(e.getMessage());
        }
//        System.out.println("************************");
//        System.out.println(writer.toString());
//        System.out.println("************************");
        response.setHeader("Content-Type", "text/plain; version=0.0.4; charset=utf-8");
        response.getOutputStream().print(writer.toString()) ;
//        return "hello!";
    }
}
