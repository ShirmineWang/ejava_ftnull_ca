/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.javaee.epod.web;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import sg.edu.nus.iss.javaee.epod.business.PodBean;
import sg.edu.nus.iss.javaee.epod.model.Pod;

/**
 *
 * @author wangxuemin
 */
@RequestScoped
@Named
public class showPodView {
    private List<Pod> pods = new ArrayList<>();

    @EJB
    private PodBean podBean;
    
    @PostConstruct
    private void init() {
        getAllPods();
    }

    public void getAllPods() {        
        pods = podBean.getAllPod();        
    }

    public List<Pod> getPods() {
        return pods;
    }

    public void setPods(List<Pod> pods) {
        this.pods = pods;
    }
    
    
}
