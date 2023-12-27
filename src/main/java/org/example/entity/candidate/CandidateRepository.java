package org.example.entity.candidate;

import org.example.entity.DBConnection;

import java.sql.*;
import java.util.List;

public class CandidateRepository implements CandidateCallbacks {
    private Connection connection;

    @Override
    public boolean createCandidateTable() {
        connection = DBConnection.makeConnection();
        boolean status = false;
        try {
            if (connection.isClosed())
                connection = DBConnection.makeConnection();
            String query = "CREATE TABLE IF NOT EXISTS candidates " +
                    "(id int(11) NOT NULL AUTO_INCREMENT, " +
                    "user_id int(11) NOT NULL, " +
                    "firstname VARCHAR(255) NOT NULL, " +
                    "lastname VARCHAR(255) NOT NULL, " +
                    "middlename VARCHAR(255) NOT NULL, " +
                    "birthdate DATE NOT NULL, " +
                    "address VARCHAR(255), " +
                    "phone VARCHAR(255), " +
                    "job_place VARCHAR(255), " +
                    "occupation VARCHAR(255) NOT NULL, " +
                    "education_place VARCHAR(255), " +
                    "education VARCHAR(255), " +
                    "relative VARCHAR(255), " +
                    "position VARCHAR(255), " +
                    "passport VARCHAR(255), " +
                    "end_date DATE, " +
                    "result VARCHAR(255), " +
                    "created_date TIMESTAMP default CURRENT_TIMESTAMP NOT NULL, " +
                    "PRIMARY KEY(id))";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            System.out.println("candidate table created");
            status = true;
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return status;
    }

    @Override
    public Candidate insertCandidate(Candidate candidate) {
        Candidate insertedCandidate = null;
        connection = DBConnection.makeConnection();
        try {
            if (connection.isClosed())
                connection = DBConnection.makeConnection();
            String query = "INSERT INTO candidates(user_id,firstname,lastname,middlename,birthdate,address,phone,job_place," +
                    "occupation,education_place,education,relative,position,passport,end_date,result) " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, candidate.getUserId());
            preparedStatement.setString(2, candidate.getFirstname());
            preparedStatement.setString(3, candidate.getLastname());
            preparedStatement.setString(4, candidate.getMiddleName());
            preparedStatement.setDate(5, Date.valueOf(candidate.getBirthDate()));
            preparedStatement.setString(6, candidate.getAddress());
            preparedStatement.setString(7, candidate.getPhone());
            preparedStatement.setString(8, candidate.getJobPlace());
            preparedStatement.setString(9, candidate.getOccupation());
            preparedStatement.setString(10, candidate.getEducationPlace());
            preparedStatement.setString(11, candidate.getEducation());
            preparedStatement.setString(12, candidate.getRelative());
            preparedStatement.setString(13, candidate.getPosition());
            preparedStatement.setString(14, candidate.getPassport());
            preparedStatement.setDate(15, Date.valueOf(candidate.getEndDate()));
            preparedStatement.setString(16, candidate.getResult());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                query = "SELECT * FROM candidates WHERE id='" + id + "'";
                preparedStatement = connection.prepareStatement(query);
                resultSet = preparedStatement.executeQuery();
                insertedCandidate = new Candidate();
                while (resultSet.next()) {
                    insertedCandidate.setId(resultSet.getInt(1));
                    insertedCandidate.setUserId(resultSet.getInt(2));
                    insertedCandidate.setFirstname(resultSet.getString(3));
                    insertedCandidate.setLastname(resultSet.getString(4));
                    insertedCandidate.setMiddleName(resultSet.getString(5));
                    insertedCandidate.setBirthDate(resultSet.getDate(6).toLocalDate());
                    insertedCandidate.setAddress(resultSet.getString(7));
                    insertedCandidate.setPhone(resultSet.getString(8));
                    insertedCandidate.setJobPlace(resultSet.getString(9));
                    insertedCandidate.setOccupation(resultSet.getString(10));
                    insertedCandidate.setEducationPlace(resultSet.getString(11));
                    insertedCandidate.setEducation(resultSet.getString(12));
                    insertedCandidate.setRelative(resultSet.getString(13));
                    insertedCandidate.setPosition(resultSet.getString(14));
                    insertedCandidate.setPassport(resultSet.getString(15));
                    insertedCandidate.setEndDate(resultSet.getDate(16).toLocalDate());
                    insertedCandidate.setResult(resultSet.getString(17));
                    insertedCandidate.setCreatedDate(resultSet.getTimestamp(18).toLocalDateTime());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return insertedCandidate;
    }

    @Override
    public Candidate updateCandidate(Candidate candidate) {
        return null;
    }

    @Override
    public Candidate getCandidateById(Integer id) {
        return null;
    }

    @Override
    public List<Candidate> getAllCandidates() {
        return null;
    }

    @Override
    public boolean deleteCandidateById(Integer id) {
        return false;
    }
}
