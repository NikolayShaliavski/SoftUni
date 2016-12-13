package photography.services.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import photography.domain.dtos.importDtos.json.LensImportDto;
import photography.domain.entities.lenses.Lens;
import photography.io.parsers.modelParsers.ModelParser;
import photography.repositories.LensRepository;
import photography.services.LensService;

@Service
@Transactional
public class LensServiceImpl implements LensService {

    @Autowired
    private LensRepository lensRepository;

    @Autowired
    private ModelParser modelParser;

    @Override
    public void save(LensImportDto lensDto) {
        Lens lens = this.convertToEntity(lensDto);
        this.lensRepository.save(lens);
    }

    private Lens convertToEntity(LensImportDto lensDto) {
        Lens lens = this.modelParser.convert(lensDto, Lens.class);
        return lens;
    }
}