package com.personal.webscrapping.service;

import com.personal.webscrapping.domain.DataBet;

import java.util.List;

public interface WebScrapping{
    List<DataBet> getBets(String url);
}
