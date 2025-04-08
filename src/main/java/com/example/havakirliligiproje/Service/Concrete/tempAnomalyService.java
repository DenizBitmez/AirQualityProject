package com.example.havakirliligiproje.Service.Concrete;

import com.example.havakirliligiproje.Entity.Quality;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class tempAnomalyService {
    public boolean isTemporalAnomaly(Quality currentData, List<Quality> last24HoursData){
        double validValue=currentData.getPm25();
        double avg24hour=last24HoursData.stream()
                .mapToDouble(Quality::getPm25)
                .average()
                .orElse(0);

        return validValue > (avg24hour * 1.5);
    }
}
