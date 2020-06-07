package it.polito.tdp.crimes.db;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.crimes.model.Crime;
import it.polito.tdp.crimes.model.District;
import it.polito.tdp.crimes.model.Event;



public class EventsDao {
	
	public List<Event> listAllEvents(){
		String sql = "SELECT * FROM events" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Event> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Event(res.getLong("incident_id"),
							res.getInt("offense_code"),
							res.getInt("offense_code_extension"), 
							res.getString("offense_type_id"), 
							res.getString("offense_category_id"),
							res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"),
							res.getDouble("geo_lon"),
							res.getDouble("geo_lat"),
							res.getInt("district_id"),
							res.getInt("precinct_id"), 
							res.getString("neighborhood_id"),
							res.getInt("is_crime"),
							res.getInt("is_traffic")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Integer> listYears(){
		String sql = "SELECT YEAR(e.reported_date) as year " + 
				"FROM `events` e " + 
				"GROUP BY YEAR(e.reported_date) ";
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Integer> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(res.getInt("year"));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	

	
	
	public List<District> listVertex(int year){
		String sql = "SELECT e.district_id as id, AVG(e.geo_lon) AS lon, AVG(e.geo_lat) AS lat " + 
				"	FROM `events` e " + 
				"	WHERE YEAR(e.reported_date)=? " + 
				"	GROUP BY e.district_id ";
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<District> list = new ArrayList<>() ;
			st.setInt(1, year);
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new District(res.getInt("id"), res.getDouble("lat"), res.getDouble("lon")));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Crime> getCrimesByDate(LocalDate ld){
		
		String sql = " SELECT e.district_id AS id, e.offense_category_id AS cat " + 
				"FROM `events` e " + 
				"WHERE DATE(e.reported_date)=? " + 
				"ORDER BY e.reported_date ASC ";
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Crime> list = new ArrayList<>() ;
			st.setDate(1, Date.valueOf(ld));
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					LocalDateTime ltd = LocalDateTime.ofInstant(res.getDate("date").toInstant(), ZoneId.systemDefault());
					list.add(new Crime(new District(res.getInt("id")), res.getString("cat"), ltd));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		
		
		
	}

	public int getDistrettoPartenza(int year) {
	
		

		String sql = " SELECT e.district_id AS id, e.offense_category_id AS cat " + 
				"FROM `events` e " + 
				"WHERE YEAR(e.reported_date)=? " + 
				"GROUP BY e.district_id " + 
				"ORDER BY cnt asc ";
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			
			st.setInt(1,year);
			ResultSet res = st.executeQuery() ;
			
			if(res.next()) {
				
				int id=  res.getInt("id");
				conn.close();
				return id;
			}else {
				conn.close();
				return -1;
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1 ;
		}
	}
}
