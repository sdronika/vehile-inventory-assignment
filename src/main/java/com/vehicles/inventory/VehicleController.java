package com.vehicles.inventory;



import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;


@RestController
public class VehicleController {

    private List<Vehicle> allVehicles = new ArrayList<>();


    @RequestMapping(value = "/addVehicles", method= RequestMethod.POST)
    
    public void addVehicles(@RequestBody ArrayList<Vehicle> vehicles) {
        this.allVehicles = vehicles;
    }

    @RequestMapping(value = "/print", method= RequestMethod.POST)
    public List<Vehicle> print(@RequestParam(value = "sort", defaultValue = "asc") String sortDirection) {

        if ("desc".equals(sortDirection)) {
            Collections.sort(allVehicles ,Collections.reverseOrder());
        } else {
            Collections.sort(allVehicles);
        }
        return allVehicles;
    }

    @RequestMapping(value = "/averageCostBy", method= RequestMethod.POST)
    public List<AverageCostByTypeResponse> averageCost(@RequestParam(name = "type", defaultValue = "carType") String averageCostBy) {
        List<AverageCostByTypeResponse> averageCostByTypeResponses = new ArrayList<>();
        Map<String, BigDecimal> costByTypeMap = new HashMap<>();
        Map<String, Integer> countByTypeMap = new HashMap<>();

        switch (averageCostBy) {
            case "carType" : averageCostByCarType(costByTypeMap, countByTypeMap); break;
            case "color" : averageCostByColor(costByTypeMap, countByTypeMap);break;
            case "brand" : averageCostByBrand(costByTypeMap, countByTypeMap);break;
            case "engine" : averageCostByEngine(costByTypeMap, countByTypeMap);break;
            default: averageCostByCarType(costByTypeMap, countByTypeMap);break;
        }

        for (Map.Entry<String, BigDecimal> eachEntry : costByTypeMap.entrySet()) {
            AverageCostByTypeResponse costPerCarTypeResponse = new AverageCostByTypeResponse();
            BigDecimal averageCost = eachEntry.getValue();
            if (countByTypeMap.get(eachEntry.getKey()) != null) {
                averageCost = averageCost.divide(new BigDecimal(countByTypeMap.get(eachEntry.getKey())), RoundingMode.CEILING);
            }
            costPerCarTypeResponse.setAverageCost(averageCost);
            costPerCarTypeResponse.setType(eachEntry.getKey());

            averageCostByTypeResponses.add(costPerCarTypeResponse);
        }

        Collections.sort(averageCostByTypeResponses);

        return averageCostByTypeResponses;
    }

    private void averageCostByEngine(Map<String, BigDecimal> costByTypeMap, Map<String, Integer> countByTypeMap) {
        for (Vehicle eachVehicle : allVehicles) {
            if (costByTypeMap.containsKey(eachVehicle.getEngineType())) {
                BigDecimal currentCost = costByTypeMap.get(eachVehicle.getEngineType());
                BigDecimal newCost = currentCost.add(new BigDecimal(eachVehicle.getPrice()));
                costByTypeMap.put(eachVehicle.getEngineType(), newCost);
            } else {
                costByTypeMap.put(eachVehicle.getEngineType(), new BigDecimal(eachVehicle.getPrice()));
            }

            if (countByTypeMap.containsKey(eachVehicle.getEngineType())) {
                int currentCount = countByTypeMap.get(eachVehicle.getEngineType());
                int newCount = currentCount + 1;
                countByTypeMap.put(eachVehicle.getEngineType(), newCount);
            } else {
                countByTypeMap.put(eachVehicle.getEngineType(), 1);
            }
        }
    }

    private void averageCostByBrand(Map<String, BigDecimal> costByTypeMap, Map<String, Integer> countByTypeMap) {
        for (Vehicle eachVehicle : allVehicles) {
            if (costByTypeMap.containsKey(eachVehicle.getBrand())) {
                BigDecimal currentCost = costByTypeMap.get(eachVehicle.getBrand());
                BigDecimal newCost = currentCost.add(new BigDecimal(eachVehicle.getPrice()));
                costByTypeMap.put(eachVehicle.getBrand(), newCost);
            } else {
                costByTypeMap.put(eachVehicle.getBrand(), new BigDecimal(eachVehicle.getPrice()));
            }

            if (countByTypeMap.containsKey(eachVehicle.getBrand())) {
                int currentCount = countByTypeMap.get(eachVehicle.getBrand());
                int newCount = currentCount + 1;
                countByTypeMap.put(eachVehicle.getBrand(), newCount);
            } else {
                countByTypeMap.put(eachVehicle.getBrand(), 1);
            }
        }
    }

    private void averageCostByColor(Map<String, BigDecimal> costByTypeMap, Map<String, Integer> countByTypeMap) {
        for (Vehicle eachVehicle : allVehicles) {
            if (costByTypeMap.containsKey(eachVehicle.getColor())) {
                BigDecimal currentCost = costByTypeMap.get(eachVehicle.getColor());
                BigDecimal newCost = currentCost.add(new BigDecimal(eachVehicle.getPrice()));
                costByTypeMap.put(eachVehicle.getColor(), newCost);
            } else {
                costByTypeMap.put(eachVehicle.getColor(), new BigDecimal(eachVehicle.getPrice()));
            }

            if (countByTypeMap.containsKey(eachVehicle.getColor())) {
                int currentCount = countByTypeMap.get(eachVehicle.getColor());
                int newCount = currentCount + 1;
                countByTypeMap.put(eachVehicle.getColor(), newCount);
            } else {
                countByTypeMap.put(eachVehicle.getColor(), 1);
            }
        }
    }

    private void averageCostByCarType(Map<String, BigDecimal> costByTypeMap, Map<String, Integer> countByTypeMap) {
        for (Vehicle eachVehicle : allVehicles) {
            if (costByTypeMap.containsKey(eachVehicle.getType())) {
                BigDecimal currentCost = costByTypeMap.get(eachVehicle.getType());
                BigDecimal newCost = currentCost.add(new BigDecimal(eachVehicle.getPrice()));
                costByTypeMap.put(eachVehicle.getType(), newCost);
            } else {
                costByTypeMap.put(eachVehicle.getType(), new BigDecimal(eachVehicle.getPrice()));
            }

            if (countByTypeMap.containsKey(eachVehicle.getType())) {
                int currentCount = countByTypeMap.get(eachVehicle.getType());
                int newCount = currentCount + 1;
                countByTypeMap.put(eachVehicle.getType(), newCount);
            } else {
                countByTypeMap.put(eachVehicle.getType(), 1);
            }
        }
    }
}
