package com.workshop22.workshop22.repo;

public class Queries {
    public static final String SQL_SELECT_ALL_RSVP = "SELECT * FROM rsvp;";
    public static final String SQL_SELECT_RSVP_BY_NAME = "SELECT * FROM rsvp WHERE full_name LIKE CONCAT('%', ?, '%');";
    public static final String SQL_INSERT_INTO_RSVP = "INSERT INTO rsvp (full_name, email, phone, confirmation_date, comments)  VALUES (?, ?, ?, ?, ?)";
    public static final String SQL_UPDATE_RSVP_BY_ID = "UPDATE rsvp SET full_name = ?, email = ?, phone = ?, confirmation_date = ?, comments = ? WHERE id = ?";
    public static final String SQL_UPDATE_RSVP_BY_EMAIL = "UPDATE rsvp SET full_name = ?, phone = ?, confirmation_date = ?, comments = ? WHERE email = ?";
    public static final String SQL_RSVP_COUNT = "SELECT COUNT(*) AS noOfRSVP FROM rsvp;";
    public static final String SQL_RSVP_DELETE_BY_ID = "DELETE FROM rsvp WHERE id = ?;";
}
