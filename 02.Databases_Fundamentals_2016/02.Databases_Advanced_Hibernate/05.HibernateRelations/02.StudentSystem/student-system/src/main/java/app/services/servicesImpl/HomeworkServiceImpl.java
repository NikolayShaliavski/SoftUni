package app.services.servicesImpl;

import app.domain.Homework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import app.repositories.HomeworkRepository;
import app.services.HomeworkService;

@Service
@Transactional
public class HomeworkServiceImpl implements HomeworkService {

    @Autowired
    private HomeworkRepository homeworkRepository;

    @Override
    public void create(Homework homework) {
        this.homeworkRepository.saveAndFlush(homework);
    }
}