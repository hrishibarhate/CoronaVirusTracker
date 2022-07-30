package com.coronavirus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.coronavirus.model.LocationStats;
import com.coronavirus.service.CoronavirusTrackerService;

@Controller
public class CoronavirusController {
	
	@Autowired
    CoronavirusTrackerService coronavirusTrackerService;

    @GetMapping("/")
    public String home(Model model) {
	List<LocationStats> allStats = coronavirusTrackerService.getAllStats();
	int totalReportedCases = allStats.stream().mapToInt(stats -> stats.getLatestTotalCases()).sum();
	int totalNewCases = allStats.stream().mapToInt(stats -> stats.getDiffFromPrevDay()).sum();
	
	model.addAttribute("locationStats", allStats);
    model.addAttribute("totalReportedCases", totalReportedCases);
    model.addAttribute("totalNewCases", totalNewCases);
	
    return "home"; 
	
    }

}
