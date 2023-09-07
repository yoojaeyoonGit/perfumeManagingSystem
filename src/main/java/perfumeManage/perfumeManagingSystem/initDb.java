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
            Customer customer1 = createMember("홍숙","1105", Gender.Female, "Korea", "Seoul", "GwangHwaMoon", "11dong 103ho", "12322");
            em.persist(customer1);


            Diffuser diffuser1 = new Diffuser();
            diffuser1.setName("holly day");
            diffuser1.setRecipe("Gucci 30%, Prada 25%, Hermes 15%, Le labo 20%, Blanche 10%");
            diffuser1.setImage("https://www.creedfragrances.co.uk/tco-images/unsafe/513x342/filters:upscale():fill(white):quality(75)/https://www.creedfragrances.co.uk/static/uploads/2023/01/feature-2.jpg");
            em.persist(diffuser1);

            DiffuserProductRequest diffuserProductRequest1 = DiffuserProductRequest.createDiffuserProductRequest(customer1, diffuser1, 3500,  new Deadline(2025, 11, 4));
            DiffuserProductRequest diffuserProductRequest2 = DiffuserProductRequest.createDiffuserProductRequest(customer1, diffuser1, 3500,  new Deadline(2025, 11, 4));
            DiffuserProductRequest diffuserProductRequest3 = DiffuserProductRequest.createDiffuserProductRequest(customer1, diffuser1, 3500,  new Deadline(2025, 11, 4));

            em.persist(diffuserProductRequest1);
            em.persist(diffuserProductRequest2);
            em.persist(diffuserProductRequest3);

            customer1.addDiffuserProductRequest(diffuserProductRequest1);
            customer1.addDiffuserProductRequest(diffuserProductRequest2);
            customer1.addDiffuserProductRequest(diffuserProductRequest3);

            DiffuserProductRequest diffuserProductRequest4 = DiffuserProductRequest.createDiffuserProductRequest(customer1, diffuser1, 3500,  new Deadline(2025, 11, 4));
            DiffuserProductRequest diffuserProductRequest5 = DiffuserProductRequest.createDiffuserProductRequest(customer1, diffuser1, 3500,  new Deadline(2025, 11, 4));
            DiffuserProductRequest diffuserProductRequest6 = DiffuserProductRequest.createDiffuserProductRequest(customer1, diffuser1, 3500,  new Deadline(2025, 11, 4));

            em.persist(diffuserProductRequest4);
            em.persist(diffuserProductRequest5);
            em.persist(diffuserProductRequest6);

            customer1.addDiffuserProductRequest(diffuserProductRequest4);
            customer1.addDiffuserProductRequest(diffuserProductRequest5);
            customer1.addDiffuserProductRequest(diffuserProductRequest6);

            diffuserProductRequest4.setStatus(ProductionStatus.PROCESSING);
            diffuserProductRequest5.setStatus(ProductionStatus.PROCESSING);
            diffuserProductRequest6.setStatus(ProductionStatus.PROCESSING);

            DiffuserProductRequest diffuserProductRequest7 = DiffuserProductRequest.createDiffuserProductRequest(customer1, diffuser1, 3500,  new Deadline(2025, 11, 4));
            DiffuserProductRequest diffuserProductRequest8 = DiffuserProductRequest.createDiffuserProductRequest(customer1, diffuser1, 3500,  new Deadline(2025, 11, 4));
            DiffuserProductRequest diffuserProductRequest9 = DiffuserProductRequest.createDiffuserProductRequest(customer1, diffuser1, 3500,  new Deadline(2025, 11, 4));

            em.persist(diffuserProductRequest7);
            em.persist(diffuserProductRequest8);
            em.persist(diffuserProductRequest9);

            customer1.addDiffuserProductRequest(diffuserProductRequest7);
            customer1.addDiffuserProductRequest(diffuserProductRequest8);
            customer1.addDiffuserProductRequest(diffuserProductRequest9);

            diffuserProductRequest7.setStatus(ProductionStatus.COMPLETE);
            diffuserProductRequest8.setStatus(ProductionStatus.COMPLETE);
            diffuserProductRequest9.setStatus(ProductionStatus.COMPLETE);

        }

        public void dbInit2() {
            Customer customer1 = createMember("감성욱","1105", Gender.Male,"Korea", "Seoul", "GwangHwaMoon daero", "11dong 103ho", "12322");
            em.persist(customer1);


            Diffuser diffuser2 = new Diffuser();
            diffuser2.setName("holly day");
            diffuser2.setRecipe("Gucci 30%, Prada 25%, Hermes 15%, Le labo 20%, Blanche 10%");
            diffuser2.setImage("https://www.creedfragrances.co.uk/tco-images/unsafe/513x342/filters:upscale():fill(white):quality(75)/https://www.creedfragrances.co.uk/static/uploads/2023/01/feature-2.jpg");
            em.persist(diffuser2);

            DiffuserProductRequest diffuserProductRequest1 = DiffuserProductRequest.createDiffuserProductRequest(customer1, diffuser2, 3500,  new Deadline(2025, 11, 4));
            DiffuserProductRequest diffuserProductRequest2 = DiffuserProductRequest.createDiffuserProductRequest(customer1, diffuser2, 3500,  new Deadline(2025, 11, 4));
            DiffuserProductRequest diffuserProductRequest3 = DiffuserProductRequest.createDiffuserProductRequest(customer1, diffuser2, 3500,  new Deadline(2025, 11, 4));

            em.persist(diffuserProductRequest1);
            em.persist(diffuserProductRequest2);
            em.persist(diffuserProductRequest3);

            customer1.addDiffuserProductRequest(diffuserProductRequest1);
            customer1.addDiffuserProductRequest(diffuserProductRequest2);
            customer1.addDiffuserProductRequest(diffuserProductRequest3);

            DiffuserProductRequest diffuserProductRequest4 = DiffuserProductRequest.createDiffuserProductRequest(customer1, diffuser2, 3500,  new Deadline(2025, 11, 4));
            DiffuserProductRequest diffuserProductRequest5 = DiffuserProductRequest.createDiffuserProductRequest(customer1, diffuser2, 3500,  new Deadline(2025, 11, 4));
            DiffuserProductRequest diffuserProductRequest6 = DiffuserProductRequest.createDiffuserProductRequest(customer1, diffuser2, 3500,  new Deadline(2025, 11, 4));

            em.persist(diffuserProductRequest4);
            em.persist(diffuserProductRequest5);
            em.persist(diffuserProductRequest6);

            customer1.addDiffuserProductRequest(diffuserProductRequest4);
            customer1.addDiffuserProductRequest(diffuserProductRequest5);
            customer1.addDiffuserProductRequest(diffuserProductRequest6);

            diffuserProductRequest4.setStatus(ProductionStatus.PROCESSING);
            diffuserProductRequest5.setStatus(ProductionStatus.PROCESSING);
            diffuserProductRequest6.setStatus(ProductionStatus.PROCESSING);

            DiffuserProductRequest diffuserProductRequest7 = DiffuserProductRequest.createDiffuserProductRequest(customer1, diffuser2, 3500,  new Deadline(2025, 11, 4));
            DiffuserProductRequest diffuserProductRequest8 = DiffuserProductRequest.createDiffuserProductRequest(customer1, diffuser2, 3500,  new Deadline(2025, 11, 4));
            DiffuserProductRequest diffuserProductRequest9 = DiffuserProductRequest.createDiffuserProductRequest(customer1, diffuser2, 3500,  new Deadline(2025, 11, 4));

            em.persist(diffuserProductRequest7);
            em.persist(diffuserProductRequest8);
            em.persist(diffuserProductRequest9);

            customer1.addDiffuserProductRequest(diffuserProductRequest7);
            customer1.addDiffuserProductRequest(diffuserProductRequest8);
            customer1.addDiffuserProductRequest(diffuserProductRequest9);

            diffuserProductRequest7.setStatus(ProductionStatus.COMPLETE);
            diffuserProductRequest8.setStatus(ProductionStatus.COMPLETE);
            diffuserProductRequest9.setStatus(ProductionStatus.COMPLETE);

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

    }
}
