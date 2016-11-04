/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.javaee.noticeboard.web;

import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import sg.edu.nus.iss.javaee.noticeboard.model.Note;

/**
 *
 * @author linby
 */
@ViewScoped
@Named
public class PostedNotesView {
    private List<Note> items = new ArrayList<>();
}
