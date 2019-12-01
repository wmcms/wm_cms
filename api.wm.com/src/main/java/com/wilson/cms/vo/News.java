package com.wilson.cms.vo;

import java.util.List;

import com.wilson.cms.po.NewsPo;
import com.wilson.cms.po.NewsReportPo;
import com.wilson.cms.po.VotePo;

public class News {

    private List<VotePo> votes;
    private NewsPo news;

    private NewsReportPo newsReportPo;

    public NewsReportPo getNewsReportPo() {
        return newsReportPo;
    }

    public void setNewsReportPo(NewsReportPo newsReportPo) {
        this.newsReportPo = newsReportPo;
    }

    public NewsPo getNews() {
        return news;
    }

    public void setNews(NewsPo news) {
        this.news = news;
    }

    public List<VotePo> getVotes() {
        return votes;
    }

    public void setVotes(List<VotePo> votes) {
        this.votes = votes;
    }
}