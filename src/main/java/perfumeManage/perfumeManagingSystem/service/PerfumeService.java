package perfumeManage.perfumeManagingSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perfumeManage.perfumeManagingSystem.domain.product.Perfume;
import perfumeManage.perfumeManagingSystem.repository.PerfumeRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PerfumeService {
    private final PerfumeRepository perfumeRepository;

    @Transactional
    public Long save(Perfume perfume) {
        perfumeRepository.save(perfume);
        return perfume.getId();
    }
    public Perfume findById(Long id) {
        return perfumeRepository.findById(id);
    }

    public List<Perfume> findAll() {
        return perfumeRepository.findAllPerfume();
    }
}
