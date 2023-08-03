package com.cqupt.software_9.service.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RuntimeBusServiceResponseSingleHeart {

    private List<String> res;
//private List<Object> res;
//
//    public String getProbability() {
//        if (res != null && res.size() > 0) {
//            return (String) res.get(0);
//        }
//        return null;
//    }
//
//    public void setProbability(String probability) {
//        if (res == null) {
//            res = new ArrayList<>();
//        }
//        if (res.size() == 0) {
//            res.add(probability);
//        } else {
//            res.set(0, probability);
//        }
//    }
//
//    public Map<String, String> getContributions() {
//        if (res != null && res.size() > 1) {
//            return (Map<String, String>) res.get(1);
//        }
//        return null;
//    }
//
//    public void setContributions(Map<String, String> contributions) {
//        if (res == null) {
//            res = new ArrayList<>();
//        }
//        if (res.size() <= 1) {
//            res.add(contributions);
//        } else {
//            res.set(1, contributions);
//        }
//    }
//
//    public List<Object> getRes() {
//        return res;
//    }
//
//    public void setRes(List<Object> res) {
//        this.res = res;
//    }

}