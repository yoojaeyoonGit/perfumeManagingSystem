package perfumeManage.perfumeManagingSystem.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import perfumeManage.perfumeManagingSystem.domain.product.Perfume;
import perfumeManage.perfumeManagingSystem.service.PerfumeService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PerfumeApiController {
    private final PerfumeService perfumeService;

    @GetMapping("/api/perfume/{id}")
    public DiffuserApiController.OneResult findOneDiffuser(@PathVariable("id") Long id) {
        Perfume perfume = perfumeService.findById(id);
        FindPerfumeDto perfumeDto = new PerfumeApiController.FindPerfumeDto(id, perfume.getName());
        return new DiffuserApiController.OneResult<>(perfumeDto);
    }

    @GetMapping("/api/perfumes")
    public Result diffusers(){
        List<Perfume> findAllPerfumes = perfumeService.findAll();
        List<FindPerfumeDto> perfumes = findAllPerfumes.stream()
                .map(o -> new FindPerfumeDto(o.getId(), o.getName()))
                .collect(Collectors.toList());
        return new Result<>(perfumes.size(), perfumes);
    }

    @PostMapping("/api/new/perfume")
    public OneResult diffusers(@RequestBody @Valid CreatePerfumeRequest createPerfumeRequest){
            Perfume perfume = new Perfume();
            perfume.setName(createPerfumeRequest.getName());
            perfume.setImage(createPerfumeRequest.getImage());
            perfume.setRecipe(createPerfumeRequest.getRecipe());
            perfumeService.save(perfume);

            CreatePerfumeResponse createPerfumeResponse = new CreatePerfumeResponse(perfume.getName(), perfume.getRecipe());
            return new OneResult<>(createPerfumeResponse);
    }


    // 중첩 데이터 저장 코드 (이런 데이터) 아래 코드는 그 코드
    /**
     * [
     *     {
     *         "name" : "groomy Day",
     *         "recipe" : "Lanvin 30%, Versace 25%, Creed 45%"
     *     },

     *     {
     *         "name" : "groomy Day",
     *         "recipe" : "Lanvin 30%, Versace 25%, Creed 45%"
     *     },

     *     {
     *         "name" : "groomy Day",
     *         "recipe" : "Lanvin 30%, Versace 25%, Creed 45%"
     *     }
     * ]
     **/
    @PostMapping("/api/new/perfume/many")
    public OneResult diffusers(@RequestBody @Valid List<CreatePerfumeRequest> createPerfumeRequest){

        ArrayList<CreatePerfumeResponse> list = new ArrayList<>();
        for (CreatePerfumeRequest perfumeRequest : createPerfumeRequest) {
            Perfume perfume = new Perfume();
            perfume.setName(perfumeRequest.getName());
            perfume.setImage(perfumeRequest.getImage());
            perfume.setRecipe(perfumeRequest.getRecipe());
            perfumeService.save(perfume);
            CreatePerfumeResponse createPerfumeResponse = new CreatePerfumeResponse(perfume.getName(), perfume.getRecipe());
            list.add(createPerfumeResponse);
        }

        return new OneResult<>(list);
    }

    @Data
    @AllArgsConstructor
    static class FindPerfumeDto {
        private Long id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T perfume;
    }


    @Data
    @AllArgsConstructor
    static class OneResult<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class CreatePerfumeRequest {
        private String name;

        private String recipe;

        private String image;
    }

    @Data
    @AllArgsConstructor
    static class CreatePerfumeResponse {
        private String name;
        private String recipe;

    }
}
