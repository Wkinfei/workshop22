package com.workshop22.workshop22.repo;
import static com.workshop22.workshop22.repo.Queries.*;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.workshop22.workshop22.models.RSVP;


@Repository
public class RsvpRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<RSVP> getAllRsvp(){
        List<RSVP> rsvps = new LinkedList<>();

        SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_SELECT_ALL_RSVP);

        while(rs.next()){
            rsvps.add(RSVP.fromSQL(rs));
        }
        return rsvps;
    }

    public List<RSVP> getRsvpByName(String name){
        List<RSVP> rsvps = new LinkedList<>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_SELECT_RSVP_BY_NAME,name);

        while(rs.next()){
            rsvps.add(RSVP.fromSQL(rs));
        }
        return rsvps;
    }

    public Integer insertRSVP(RSVP rsvp){
        Integer inserted = jdbcTemplate.update(
            SQL_INSERT_INTO_RSVP,
            rsvp.getFullName(), rsvp.getEmail(), rsvp.getPhone(), rsvp.getConfirmationDate(), rsvp.getComments() );

            System.out.println("inserted --> " + inserted);

            return inserted;
        }
    
    public Integer updateRSVP(RSVP rsvp){
        Integer updated = jdbcTemplate.update(
            SQL_UPDATE_RSVP_BY_ID,
            rsvp.getFullName(), rsvp.getEmail(), rsvp.getPhone(), rsvp.getConfirmationDate(), rsvp.getComments(),rsvp.getId() );
    
            System.out.println("updated --> " + updated);
    
            return updated;
        }  
        
    public Integer updateRsvpByEmail(RSVP rsvp,String email){
        Integer updated = jdbcTemplate.update(
            SQL_UPDATE_RSVP_BY_EMAIL,
            rsvp.getFullName(), rsvp.getPhone(), rsvp.getConfirmationDate(), rsvp.getComments(),email);
    
            System.out.println("updatedByEmail --> " + updated);
    
            return updated;
    }
    

    public Integer getRsvpCount(){
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_RSVP_COUNT);
        rs.next();
        Integer count = rs.getInt(1);
        
        return count;
    }

    public Integer deleteRsvp(Integer id){
        Integer deleted = jdbcTemplate.update(SQL_RSVP_DELETE_BY_ID,id);
        System.out.println("DeletedById --> " + deleted);
        return deleted;
    }
}
