package kz.galymbay.coursesite.service;

import kz.galymbay.coursesite.dto.Mentor;
import kz.galymbay.coursesite.repository.MentorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MentorService {
    private final MentorRepository mentorRepository;

    public List<Mentor> getAll() {
        return mentorRepository.findAll();
    }

    public Mentor saveMentor(Mentor mentor) {
        return mentorRepository.save(mentor);
    }

    public Mentor findById(Long id) {
        return mentorRepository.findById(id).get();
    }

    public Mentor updateMentor(Mentor mentor, Long id) {
        Mentor updatedMentor = findById(id);

        updatedMentor.setUsername(mentor.getUsername());
        updatedMentor.setEmail(mentor.getEmail());
        updatedMentor.setName(mentor.getName());
        updatedMentor.setSurname(mentor.getSurname());
        updatedMentor.setPassword(mentor.getPassword());
        updatedMentor.setActive(mentor.isActive());
        updatedMentor.setBlock(mentor.isBlock());

        mentorRepository.save(updatedMentor);
        return updatedMentor;
    }

    public Mentor deleteById(Long id) {
        Mentor mentor = findById(id);
        mentorRepository.deleteById(id);

        return mentor;
    }
}
