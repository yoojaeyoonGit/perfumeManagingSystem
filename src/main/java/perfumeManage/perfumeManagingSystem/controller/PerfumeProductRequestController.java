package perfumeManage.perfumeManagingSystem.controller;

        import lombok.RequiredArgsConstructor;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;
        import perfumeManage.perfumeManagingSystem.SessionConst;
        import perfumeManage.perfumeManagingSystem.domain.Customer;
        import perfumeManage.perfumeManagingSystem.domain.productionRequest.PerfumeProductRequest;
        import perfumeManage.perfumeManagingSystem.domain.ProcessingRequest;
        import perfumeManage.perfumeManagingSystem.domain.ProductionStatus;
        import perfumeManage.perfumeManagingSystem.dto.PerfumeRequestDto;
        import perfumeManage.perfumeManagingSystem.service.CustomerService;
        import perfumeManage.perfumeManagingSystem.service.PerfumeProductRequestService;

        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpSession;
        import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class PerfumeProductRequestController {

    private final PerfumeProductRequestService perfumeProductRequestService;
    private final CustomerService customerService;


    @GetMapping("/{id}/perfumeProductRequest")
    public String PerfumeProductRequest(@PathVariable ("id")Long id, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if(session == null) {
            return "redirect:/members/new";
        }

        Customer loginmCustomer = (Customer) session.getAttribute(SessionConst.LOGIN_CUSTOMER);
        model.addAttribute("loginCustomer", loginmCustomer);
        model.addAttribute("perfumeRequest", new PerfumeRequestDto());
        return "request/perfumeRequest";
    }

//    @PostMapping("{id}/perfumeProductRequest")
//    public String PerfumeProductRequest(@PathVariable("id") Long id, PerfumeRequestDto perfumeRequestDto) {
//        Customer customer = customerService.findCustomerById(id);
//
//
////
////        PerfumeProductRequest.createPerfumeProductRequest()
////        per
////
////        LocalDate deadline = LocalDate.of(perfumeRequestDto.getYear(), perfumeRequestDto.getMonth(), perfumeRequestDto.getDate());
////        perfumeProductRequest.setCustomer(customer);
////        perfumeProductRequest.setName(perfumeRequestDto.getName());
////        perfumeProductRequest.setRecipe(perfumeRequestDto.getRecipe());
////        perfumeProductRequest.setAmount(perfumeRequestDto.getAmount());
////        perfumeProductRequest.setDeadline(deadline);
////        perfumeProductRequest.setImage(perfumeRequestDto.getImage());
////        perfumeProductRequest.setStatus(ProductionStatus.REQUEST);
////
////        customer.addPerfumeProductRequest(perfumeProductRequest);
//
//        perfumeProductRequestService.savePerfumeProductRequest(perfumeProductRequest);
//        return "redirect:/";
//    }



    @PostMapping ("{customerId}/{itemId}/perfumeProductionProcessing")
    public String ChangePerfumeRequestStatusToProcessing(@PathVariable("itemId") Long id, @PathVariable("customerId") Long customerId) {
        PerfumeProductRequest perfumeProductRequest = perfumeProductRequestService.find(id);
        perfumeProductRequest.setStatus(ProductionStatus.PROCESSING);

        perfumeProductRequestService.ChangePerfumeProductRequestStatusToProcessing(perfumeProductRequest);
        return "redirect:/{customerId}/checkAll/perfume";
    }

    @PostMapping("{customerId}/{id}/perfumeProductionComplete")
    public String ChangPerfumeRequestStatusToComplete(@PathVariable("id") Long id, @PathVariable("customerId") Long customerId, HttpServletRequest httpServletRequest) {
        try {

            PerfumeProductRequest perfumeProductRequest = perfumeProductRequestService.find(id);
            perfumeProductRequest.setStatus(ProductionStatus.COMPLETE);

            ProcessingRequest processingRequest = perfumeProductRequest.getProcessingRequest();
            Customer customer = processingRequest.getCustomer();
            perfumeProductRequestService.changePerfumeProductRequestStatusToComplete (processingRequest, perfumeProductRequest);


            return "redirect:/{customerId}/checkAll/perfume";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/";
        }
    }
}
