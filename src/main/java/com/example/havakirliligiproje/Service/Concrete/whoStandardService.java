package com.example.havakirliligiproje.Service.Concrete;

import com.example.havakirliligiproje.Entity.Quality;
import org.springframework.stereotype.Service;

@Service
public class whoStandardService {
    public boolean dangerousLevel(Quality data){
        return data.getPm25() > 25 ||
                data.getPm10() > 50 ||
                data.getNo2() > 200 ||
                data.getO3() > 100 ||
                data.getSo2() > 40;
    }
}
