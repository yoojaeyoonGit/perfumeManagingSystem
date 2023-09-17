package perfumeManage.perfumeManagingSystem;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import perfumeManage.perfumeManagingSystem.domain.*;
import perfumeManage.perfumeManagingSystem.domain.product.Diffuser;
import perfumeManage.perfumeManagingSystem.domain.productionRequest.DiffuserProductRequest;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class initDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }



    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            Customer customer = createMember("홍숙","1105", Gender.Female, "Korea", "Seoul", "GwangHwaMoon", "11dong 103ho", "12322");
            customer.setAuth(Auth.Manager);
            System.out.println("멤버 권한 변경");
            em.persist(customer);

            for (int i = 0; i < 9; i++) {
                Diffuser diffuser = createDiffuser("holly day", "Lanvin 30%, Versace 25%, Creed 45%", "사진");
                em.persist(diffuser);

                Order order = new Order();
                order.setCustomer(customer);
                em.persist(order);

                DiffuserProductRequest diffuserProductRequest = DiffuserProductRequest.createDiffuserProductRequest(order, diffuser, 3500,  new Deadline(2025, 11, 4));
                em.persist(diffuserProductRequest);

//                customer.addDiffuserProductRequest(diffuserProductRequest);

                if (i >= 3 && i < 6) {
                    diffuserProductRequest.setStatus(ProductionStatus.PROCESSING);
                } else if (i >= 6 && i < 9) {
                    diffuserProductRequest.setStatus(ProductionStatus.COMPLETE);
                }
            }
        }

        public void dbInit2() {
            Customer customer = createMember("감성욱","1105", Gender.Female, "Korea", "Seoul", "GwangHwaMoon", "11dong 103ho", "12322");
            em.persist(customer);

            for (int i = 0; i < 9; i++) {
                Diffuser diffuser = createDiffuser("holly day", "Lanvin 30%, Versace 25%, Creed 45%", "사진");
                em.persist(diffuser);

                Order order = new Order();
                order.setCustomer(customer);
                em.persist(order);

                DiffuserProductRequest diffuserProductRequest = DiffuserProductRequest.createDiffuserProductRequest(order, diffuser, 3500,  new Deadline(2025, 11, 4));
                em.persist(diffuserProductRequest);

                customer.addDiffuserProductRequest(diffuserProductRequest);


                if (i >= 3 && i < 6) {
                    diffuserProductRequest.setStatus(ProductionStatus.PROCESSING);
                } else if (i >= 6 && i < 9) {
                    diffuserProductRequest.setStatus(ProductionStatus.COMPLETE);
                }
            }

            for (int i = 0; i < 7; i++) {

            }
        }


        private static Customer createMember(String name, String password, Gender gender, String country, String city, String StreetAddress, String detailedAddress, String zipcode) {
            Customer customer = new Customer();
            customer.setName(name);
            customer.setPassword(password);
            customer.setAuth(Auth.General);
            customer.setGender(gender);
            customer.setAddress(new Address(country, city, StreetAddress, detailedAddress, zipcode));
            return customer;
        }

        private static Diffuser createDiffuser(String name, String recipe, String image) {
            Diffuser diffuser = new Diffuser();
            diffuser.setName(name);
            diffuser.setRecipe(recipe); //"Lanvin 30%, Versace 25%, Creed 45%"
            diffuser.setImage(image);
            return diffuser;
        }

    }
}
