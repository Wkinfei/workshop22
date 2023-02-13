package com.workshop22.workshop22.models;




import java.sql.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class RSVP {
    private Integer id;
    private String fullName;
    private String email;
    private String phone;
    private Date confirmationDate;
    private String comments;

    public static RSVP fromSQL(SqlRowSet rs){
        RSVP rsvp = new RSVP();
        rsvp.setId(rs.getInt("id"));
        rsvp.setFullName(rs.getString("full_name"));
        rsvp.setEmail(rs.getString("email"));
        rsvp.setPhone(rs.getString("phone"));
        rsvp.setConfirmationDate(rs.getDate("confirmation_date"));
        rsvp.setComments(rs.getString("comments"));
        return rsvp;
    }

    public JsonObject toJsonObject(){
        return Json.createObjectBuilder()
                .add("id", this.getId())
                .add("full_name",this.getFullName())
                .add("email",this.getEmail())
                .add("phone",this.getPhone())
                .add("confirmation_date", getConfirmationDate() != null ? getConfirmationDate().toString() : "")
                .add("comments",this.getComments())
                .build();
    }

    

    @Override
    public String toString() {
        return "RSVP [id=" + id + ", fullName=" + fullName + ", email=" + email + ", phone=" + phone
                + ", confirmationDate=" + confirmationDate + ", comments=" + comments + "]";
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
   
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(Date confirmationDate) {
        this.confirmationDate = confirmationDate;
    }
    
    
}
