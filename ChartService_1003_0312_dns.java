// 代码生成时间: 2025-10-03 03:12:23
package com.example.micronaut.chart;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.MediaType;
import io.micronaut.http.exceptions.HttpStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Controller("/chart")
@Singleton
public class ChartService {

    private static final Logger logger = LoggerFactory.getLogger(ChartService.class);

    private final ChartLibrary chartLibrary;

    // Constructor injecting ChartLibrary
    public ChartService(ChartLibrary chartLibrary) {
        this.chartLibrary = chartLibrary;
    }

    // GET endpoint to retrieve a chart
    @Get(value = "/{chartId}", produces = MediaType.APPLICATION_JSON)
    public Map<String, Object> getChart(String chartId) {
        try {
            // Fetch the chart from the library using chartId
            Chart chart = chartLibrary.getChart(chartId);
            if (chart == null) {
                // Log and throw an exception if the chart is not found
                logger.warn("Chart with ID {} not found", chartId);
                throw new HttpStatusException(404, "Chart not found");
            }
            // Return the chart as a JSON Map
            return convertChartToMap(chart);
        } catch (Exception e) {
            // Log and handle any unexpected exceptions
            logger.error("Error retrieving chart with ID {}: {}", chartId, e.getMessage());
            throw new HttpStatusException(500, "Internal Server Error");
        }
    }

    // Utility method to convert Chart to a Map
    private Map<String, Object> convertChartToMap(Chart chart) {
        Map<String, Object> chartMap = new HashMap<>();
        chartMap.put("id", chart.getId());
        chartMap.put("title", chart.getTitle());
        chartMap.put("data", chart.getData());
        chartMap.put("options", chart.getOptions());
        chartMap.put("type", chart.getType());
        return chartMap;
    }
}

// Class representing a Chart
class Chart {
    private String id;
    private String title;
    private Object data;
    private Map<String, Object> options;
    private String type;

    // Constructor, getters, and setters omitted for brevity
}

// Interface for ChartLibrary
interface ChartLibrary {
    Chart getChart(String chartId);
}
