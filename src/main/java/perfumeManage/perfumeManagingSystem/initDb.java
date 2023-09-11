package perfumeManage.perfumeManagingSystem;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import perfumeManage.perfumeManagingSystem.domain.*;

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
            em.persist(customer);

            for (int i = 0; i < 9; i++) {
                Diffuser diffuser = new Diffuser();
                diffuser.setName("holly day");
                diffuser.setRecipe("Gucci 30%, Prada 25%, Hermes 15%, Le labo 20%, Blanche 10%");
                diffuser.setImage("https://www.creedfragrances.co.uk/tco-images/unsafe/513x342/filters:upscale():fill(white):quality(75)/https://www.creedfragrances.co.uk/static/uploads/2023/01/feature-2.jpg");
                em.persist(diffuser);

                DiffuserProductRequest diffuserProductRequest = DiffuserProductRequest.createDiffuserProductRequest(customer, diffuser, 3500,  new Deadline(2025, 11, 4));
                em.persist(diffuserProductRequest);

                customer.addDiffuserProductRequest(diffuserProductRequest);

                if (i >= 3 && i < 6) {
                    diffuserProductRequest.setStatus(ProductionStatus.PROCESSING);
                } else if (i >= 6 && i < 9) {
                    diffuserProductRequest.setStatus(ProductionStatus.COMPLETE);
                }
            }
//
//
        }

        public void dbInit2() {
            Customer customer = createMember("감성욱","1105", Gender.Female, "Korea", "Seoul", "GwangHwaMoon", "11dong 103ho", "12322");
            em.persist(customer);

            for (int i = 0; i < 9; i++) {
                Diffuser diffuser = new Diffuser();
                diffuser.setName("holly day");
                diffuser.setRecipe("Gucci 30%, Prada 25%, Hermes 15%, Le labo 20%, Blanche 10%");
                diffuser.setImage("https://www.creedfragrances.co.uk/tco-images/unsafe/513x342/filters:upscale():fill(white):quality(75)/https://www.creedfragrances.co.uk/static/uploads/2023/01/feature-2.jpg");
                em.persist(diffuser);

                DiffuserProductRequest diffuserProductRequest = DiffuserProductRequest.createDiffuserProductRequest(customer, diffuser, 3500,  new Deadline(2025, 11, 4));
                em.persist(diffuserProductRequest);

                customer.addDiffuserProductRequest(diffuserProductRequest);

                if (i >= 3 && i < 6) {
                    diffuserProductRequest.setStatus(ProductionStatus.PROCESSING);
                } else if (i >= 6 && i < 9) {
                    diffuserProductRequest.setStatus(ProductionStatus.COMPLETE);
                }
            }

            for (int i = 0; i < 7; i++) {
                createDiffuser("홍차티", "Lanvin 30%, Versace 25%, Creed 45%", "사진");
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
