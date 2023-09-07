package perfumeManage.perfumeManagingSystem.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import perfumeManage.perfumeManagingSystem.domain.Diffuser;
import perfumeManage.perfumeManagingSystem.service.DiffuserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class DiffuserApiController {
    private final DiffuserService diffuserService;

    @GetMapping("/api/diffuser/{id}")
    public OneResult findOneDiffuser(@PathVariable("id") Long id) {
        Diffuser diffuser = diffuserService.findById(id);
        FindDiffuserDto diffuserDto = new FindDiffuserDto(id, diffuser.getName());
        return new OneResult(diffuserDto);
    }

    @GetMapping("/api/diffusers")
    public Result diffusers(){
        List<Diffuser> findAllDiffusers = diffuserService.findAll();
        List<FindDiffuserDto> diffs = findAllDiffusers.stream()
                .map(o -> new FindDiffuserDto(o.getId(), o.getName()))
                .collect(Collectors.toList());
        return new Result(findAllDiffusers.size(), diffs);
    }

    @PostMapping("/api/new/diffuser")
    public OneResult diffusers(@RequestBody @Valid CreateDiffuserRequest createDiffuserRequest){
        Diffuser diffuser = new Diffuser();
        diffuser.setName(createDiffuserRequest.getName());
        diffuser.setImage(createDiffuserRequest.getImage());
        diffuser.setRecipe(createDiffuserRequest.getRecipe());
        diffuserService.save(diffuser);

        CreateDiffuserResponse createDiffuserResponse = new CreateDiffuserResponse(diffuser.getName(), diffuser.getRecipe());

        return new OneResult(createDiffuserResponse);
    }


    @Data
    @AllArgsConstructor
    static class FindDiffuserDto {
        private Long id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T diffuser;
    }


    @Data
    @AllArgsConstructor
    static class OneResult<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class CreateDiffuserRequest {
        private String name;

        private String recipe;

        private String image;
    }

    @Data
    @AllArgsConstructor
    static class CreateDiffuserResponse {
        private String name;
        private String recipe;

    }
}
