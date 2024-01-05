package org.example.entity.candidate;

import java.util.List;

public interface CandidateCallbacks {
    boolean createCandidateTable();
    Candidate insertCandidate(Candidate candidate);
    Candidate updateCandidate(Candidate candidate);
    Candidate getCandidateById(Integer id);
    List<Candidate> getAllCandidates();
    boolean deleteCandidateById(Integer id);
    List<Candidate> searchByFirstFirstnameOrLastnameOrMiddlename(String suffix);
    List<Candidate> searchByPhoneOrPassport(String suffix);
    List<Candidate> searchByJobPlaceOrOccupationOrEducationOrPosition(String suffix);
    List<Candidate> searchByBirthDate(String suffix);
}
