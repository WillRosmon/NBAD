/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package music.tags;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.*;
/**
 *
 * @author wrosmon
 */
public class IfEmptyMarkTag extends TagSupport {
    private String field;
    
    public void setField(String field) {
        this.field = field;
    }
    
    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            if(field == null || field.length() == 0) {
                out.print("*");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return SKIP_BODY;
    }
}
