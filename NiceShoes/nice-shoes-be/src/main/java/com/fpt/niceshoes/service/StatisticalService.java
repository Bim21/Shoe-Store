package com.fpt.niceshoes.service;

import com.fpt.niceshoes.dto.response.statistic.StatisticalDayResponse;
import com.fpt.niceshoes.dto.response.statistic.StatisticalMonthlyResponse;

import java.util.List;

public interface StatisticalService {

    List<StatisticalDayResponse> getAllStatisticalDay();
    List<StatisticalMonthlyResponse> getAllStatisticalMonth();
}
