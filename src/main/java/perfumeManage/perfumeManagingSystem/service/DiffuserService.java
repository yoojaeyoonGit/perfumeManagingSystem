package perfumeManage.perfumeManagingSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perfumeManage.perfumeManagingSystem.domain.product.Diffuser;
import perfumeManage.perfumeManagingSystem.repository.DiffuserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DiffuserService {
    private final DiffuserRepository diffuserRepository;

    @Transactional
    public Long save(Diffuser diffuser) {
        diffuserRepository.save(diffuser);
        return diffuser.getId();
    }
    public Diffuser findById(Long id) {
        return diffuserRepository.findById(id);
    }

    public List<Diffuser> findAll() {
        return diffuserRepository.findAllDiffuser();
    }
}
